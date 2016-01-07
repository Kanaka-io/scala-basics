package next_steps

import support.HandsOnSuite

/**
  * Pattern matching
  *
  * Pattern matching is a very powerful technique that allows us to reason about the structure
  * of object (without breaking type erasure principle).
  *
  * Using pattern matching, we can extract some pieces of an object, do different things according
  * to its shape or properties, etc.
  *
  * It can be seen as a functional alternative to polymorphic dispatch.
  */

class e4_pattern_matching extends HandsOnSuite {
  /**
    * Pattern matching relies on so-called extractors.
    *
    * An extractor is a method called 'unapply' defined on the companion object of a class,
    * which simply "deconstructs" an instance of a class into smaller elements
    */
  exercise("An extractor is the converse of a constructor") {
    class Email(val value: String)

    object Email {
      /**
        * Given an Email, deconstruct it to return only its value
        *
        * unapply must return an Option because (under circumstances that are not relevant here) we might
        * not be able to perform this deconstruction
        */
      def unapply(email: Email): Option[String] = Option(email.value)
    }

    val mailstring = "foo@bar.com"
    val email = new Email(mailstring)



    val Email(extractedString) = email

    (extractedString == mailstring) should be(__)
  }

  exercise("Extractors work also with multiple fields") {
    class Email(val value: String, val spamRatio: Integer)
    object Email {
      /**
        * When the class has multiple constituents, we wrap them in a tuple (notice the parens bellow)
        */
      def unapply(email: Email): Option[(String, Integer)] = Option((email.value, email.spamRatio))
    }

    val email = new Email("foo@bar.com", 5)
    val Email(extractedString, extractedRatio) = email

    extractedRatio should be(__)
    extractedString should be(__)
  }

  /**
    * Case classes are meant to enable pattern matching
    */
  exercise("The compiler generates extractors for cases classes") {
    case class Email(value: String)

    val mailstring = "foo@bar.com"
    val email = new Email(mailstring)
    val Email(extractedString) = email

    extractedString should be(__)
  }

  /**
    * Pattern matching is most often used via the 'match' construct.
    * This can be seen as a generalization of the switch of languages like Java or C.
    *
    * Like any other construct in Scala, a match block is a fully fledged expression, that has
    * a type and a value.
    *
    * It has the following form :
    *
    *   expr match {
    *     case pattern1 => expr1
    *     case pattern2 => expr2
    *     ...
    *     case patternN => exprN
  *     }
    *
    * Notice the case keyword, it's not a coincidence that it is the same that the one we use to
    * declare case classes !
    *
    * Each pattern is attempted in order, the first that matches expr wins and its body (exprI)
    * is evaluated to give the result of the block.
    * If no pattern matches, a (runtime) MatchError is raised, but the compiler does its best to
    * detect incomplete match blocks (where the programmer did not cover all the possible cases),
    * in order to reduce the risk of run time MatchErrors.
    *
    */
  exercise("Match block replace switch cases") {
    val string = "B"

    val actual = "B" match {
      case "A" => "stringA"
      case "B" => "stringB"
      case "C" => "stringC"
      // In the context of a pattern, _ matches everything,
      // it's often a way to ensure we've got every case covered
      case _ => "DEFAULT"
    }

    actual should be(__)

    val nextActual = "E" match {
      case "A" => "stringA"
      case "B" => "stringB"
      case "C" => "stringC"
      case _ => "DEFAULT"
    }

    nextActual should be(__)
  }


  /**
    * Inside a pattern, we can capture a field and attach it to a variable
    * that will be scoped to the body of the corrspounding case (after the =>)
    *
    * IMPORTANT NOTE : pattern variables MUST begin with a lower-cased character
    */
  exercise("Match blocks can extract fields from case classes") {
    case class A(a: String, b: String)

    val someA: A = A(a = "string", b = "B")

    val actual = someA match {
      case A(aa, bb) => aa + bb // on strings, the method + stands for concatenation
      case _ => "DEFAULT"
    }

    actual should be(__)
  }

  exercise("Capturing all the fields is not mandatory") {
    case class A(a: String, b: String)
    val someA: A = new A(a = "string", b = "B")

    val actual = someA match {
      case A(aa, _) => aa
      case _ => "DEFAULT"
    }

    actual should be(__)
  }

  /**
    * We can also add "guards" to our patterns, using the if keyword,
    * this allows for further filtering based on properties of
    * the captured pattern variables.
    */
  exercise("Patterns with guards") {
    case class B(b: String, c: Int)

    def bigB(b: B) = b match {
      case B(bb, cc) if cc > 10 => bb
      case _ => "Too small"
    }

    bigB(B("The answer", 42)) should be(__)

    bigB(B("The question", 9)) should be(__)

  }

  /**
    * With pattern matching, we can explore arbitrarily nested structures
    */
  exercise("We can go deeper") {
    case class A(a: String)
    case class B(a: A, b: Int)
    case class C(a: A, b: B)

    val deeplyNested = C(
      a = A("Scala"),
      b = B(
        a = A("Java"),
        b = 8
      )
    )

    val contrived = deeplyNested match {
      case C( A("Erlang"), _ ) => "robust"
      case C( _ , B(_, version)) if version <= 7 => "obsolete"
      case _ => "shiny"
    }

    contrived should be(__)
  }

  /**
    * Finally (although it is seldom used) we can define a pattern variable
    * AND impose constraints on its value in one go, using the '@' keyword
    */
  exercise("Nesting patterns") {
    case class A(str: String, int: Int)

    val someA = A("Scala", 42)

    val result = someA match {
      case aa @ A(_, i) if i > 10 => aa
      case _ => A("", 0)
    }

    result should be(__)
  }


  exercise("List are case classes, we can pattern match on them !") {

    // Seq is an abstraction that encompasses Lists, among other data structures
    val s = Seq("a", "b")
    val actual = s match {
      case Seq("a", "b") => "ok"
      case _ => "DEFAULT"
    }

    actual should be(__)

    val consActual = s match {
      case "a" :: Nil => "nok"
      case "a" :: "b" :: Nil => "ok"
      case _ => "DEFAULT"
    }

    consActual should be(__)

    val headtailActual = s match {
      case head :: tail => tail
      case _ => "DEFAULT"
    }

    headtailActual should be(__)
  }

}
