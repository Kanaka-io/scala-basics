package support

import org.scalatest._
import org.scalatest.matchers.Matcher
import org.scalatest.events._
import org.scalatest.exceptions.TestPendingException
import recorder._

import language.experimental.macros
import scala.Some

class ReportToTheStopper(other: Reporter) extends Reporter {
  var failed = false

  def info(a:Any) = println(a)

  def headerFail =    "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n               TEST FAILED                 \n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"
  def footerFail =    "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"
  def headerPending = "*******************************************\n               TEST PENDING                \n*******************************************"
  def footerPending = "*******************************************"

  def sendInfo(header: String, suite: String, test: String, location: Option[String], message: Option[String], context: Option[String], footer: String) {
    header.split("\n").foreach(info(_))

    info( "Suite    : " + suite.replace("\n","") )

    info( "Exercise : " + test.replace("\n","") )

    location.collect({ case f =>
      info( "File     : " + f.replace("\n","") )
    })
    message.collect({ case m =>
      info("")
      m.split("\n").foreach( info(_) )
    })
    context.collect({ case c =>
      info("")
      c.split("\n").foreach( info(_) )
    })
    info("")
    footer.split("\n").foreach(info(_))
    HandsOnSuite.cancelRemaining = true


  }

  def sendFail(e:MyException, suite:String, test:String) = {
    sendInfo(headerFail
      , suite
      , test
      , e.fileNameAndLineNumber
      , Option(e.getMessage)
      , e.context
      , footerFail
    )
  }

  def sendPending(e:MyException, suite:String, test:String, mess:Option[String]) = {
    sendInfo(headerPending
      , suite
      , test
      , e.fileNameAndLineNumber
      , mess
      , e.context
      , footerPending
    )
  }

  def apply(event: Event) {
    event match {
      case e: TestFailed => {
        e.throwable match {
          //pour les erreurs d'assertions => sans stacktrace
          case Some(failure: MyTestFailedException) =>
            sendFail(failure, e.suiteName, e.testName)
          //pour les __ => avec context
          case Some(pending: MyTestPendingException) =>
            sendPending(pending, e.suiteName, e.testName, Some("You need to replace __ by the correct value"))
          //pour les ??? => sans context
          case Some(pending: MyNotImplException) =>
            sendPending(pending, e.suiteName, e.testName, Some("You need to replace ??? by the correct implementation"))
          //pour les autres erreurs => avec stacktrace
          case Some(failure: MyException) =>
            sendFail(failure, e.suiteName, e.testName)
          //ça ne devrait pas arriver
          case Some(e) =>
            println("something went wrong")
          //ça non plus, un TestFailed a normalement une excepetion attachée
          case None =>
            sendInfo(headerFail
              , e.suiteName
              , e.testName
              , None
              , None
              , None
              ,
              footerFail
            )
        }
      }
      case e: TestPending =>
        sendInfo(headerPending
          , e.suiteName
          , e.testName
          , None
          , Some("pending")
          , None
          , footerPending)
      case _ => other(event)
    }
  }
}

object HandsOnSuite {
  @volatile var cancelRemaining = false
  object partie1 extends Tag("support.partie1")
  object partie2 extends Tag("support.partie2")
}


trait HandsOnSuite extends MyFunSuite with Matchers {
  def __ : Matcher[Any] = {
    throw new NotImplementedError("__")
  }

  implicit val suite:MyFunSuite = this


  import HandsOnSuite._

  abstract override def withFixture(test: NoArgTest): Outcome = {
    if (cancelRemaining)
      Canceled("Canceled by CancelGloballyAfterFailure because a test failed previously")
    else
      super.withFixture(test) match {
        case failed: Failed =>
          cancelRemaining = true
          failed
        case outcome => outcome
      }
  }

  final def newInstance: Suite with OneInstancePerTest = throw new UnsupportedOperationException

  def anchor[A](a:A):Unit = macro RecorderMacro.anchor[A]

  def exercise(testName:String)(testFun: Unit)(implicit suite: MyFunSuite, anchorRecorder: AnchorRecorder):Unit = macro RecorderMacro.apply



  /*override protected def test(testName: String, tags: Tag*)(testFun: => Unit):Unit


  = macro RecorderMacro.apply  */


  override def run(testName: Option[String], args: Args): Status =
    super.run(testName, args.copy(
      reporter = new ReportToTheStopper(args.reporter), stopper = new Stopper {
        override def stopRequested: Boolean = HandsOnSuite.cancelRemaining

        override def requestStop(): Unit = HandsOnSuite.cancelRemaining = true
      })
    )


}
