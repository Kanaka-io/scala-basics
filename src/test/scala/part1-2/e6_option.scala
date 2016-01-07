package next_steps

import support.HandsOnSuite

/**
  * Option is a very (very) useful type.
  *
  * It can be seen as a collection that contains at most one element (but can be empty).
  * We use it to denote the fact that something can be present or not in a (way) safer
  * and (arguably) cleaner way than resorting to (the dreadful) null.
  *
  * Option[A] has two subtypes :
  *
  *   - None : the empty case, defined as Option[Nothing] (remember, Nothing can satisfy every other type)
  *   - Some[A] : the case where a content is defined
  *
  *   see http://www.scala-lang.org/api/current/index.html#scala.Option
  */

class e6_option extends HandsOnSuite {
  exercise("None is equal to ...None") {
   None should be(__)
  }

  exercise("None is, well ... empty") {
    None.isEmpty should be(__)
  }


  exercise("None is 'real' object, we can call methods on it") {
    val optional: Option[String] = None
    optional.isEmpty should be(__)
    optional should be(__)
  }

  exercise("Some everything but None") {
    val optional: Option[String] = Some("Some Value")
    (optional == None) should be(__)
    optional.isEmpty should be(__)
  }

  exercise("We can call getOrElse on an option to safely extract its content or fallback to a default value") {
    val optional: Option[String] = Some("Some Value")
    val optional2: Option[String] = None
    optional.getOrElse("No Value") should be(__)
    optional2.getOrElse("No Value") should be(__)
  }

  exercise("We can use map on an Option to safely transform its content") {
    def toUpper(s: String) = s.toUpperCase

    None map toUpper should be(__)
    Some("scala") map toUpper should be(__)

  }

  exercise("We can use Option in for-comprehensions") {

    val someString = Some("scala")
    val someInt = Some(42)
    val none = None

    val interpolate1 = for {
      str <- someString
      int <- someInt
    } yield s"$str ${int * 2}" // this is string interpolation notice the s before the opening double-quote

    interpolate1 should be(__)

    val interpolate2 = for {
      str <- someString
      nn <- none
      int <- someInt
    } yield s"$str ${int * 2}"

    interpolate2 should be(__)
  }

}
