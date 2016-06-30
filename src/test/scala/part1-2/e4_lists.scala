package next_steps


import support.HandsOnSuite


  /**
    *   Now on to Lists ...
    *
    * The entry point to Scala's collection API is the Iterable trait (which List obviously implements).
    * Every collection provides higher-order functions (we'll get into that shortly) that allows us to
    * conveniently manipulate them
    *
    * By default, collections are immutable but mutable versions exists for each collection types.
    * (You should have guessed by now that immutability is the standard for almost everything in Scala).
    *
    *
    * A List containing elements x1, … , xn is denoted List(x1, … , xn).
    *
    * List is modeled as a singly linked list, the trait List has two implementations :
    *
    *   - Nil which is a case object and represents the empty list
    *   - :: (usually pronounced 'cons') which represents a "link" in the linked list
    *
    * That is :
    *
    *   List(x1, … , xn) == x1 :: … :: xn :: Nil
    *
    *   useful link : http://www.scala-lang.org/api/current/index.html#scala.collection.immutable.List
    */

class e4_lists extends HandsOnSuite {

  exercise("Nil is the only possible empty list") {
    val a: List[String] = Nil // Nil est la classe qui représente une liste vide
    val b: List[Int] = Nil // et oui en Scala tout est OBJET !

    (a == Nil) should be(__)
    (b == Nil) should be(__)
    (a == b) should be(__)
    (a eq b) should be(__)

  }

  exercise("Creating new lists") {
    val a = List(1, 2, 3)
    val b = List(1, 2, 3)

    (a eq b) should be(__)
    (a == b) should be(__)
  }

  /**
    *  Some useful methods on List
    */
  exercise("The List API") {
    val a = List(1, 3, 5, 7, 9)

    // Get element at index 2 in the list
    a(2) should equal(__)

    a.head should equal(__)

    a.tail should equal(__)

    a.length should equal(__)

    a.reverse should equal(__)

    a.toString should equal(__)

    // map applies a function to each successive elements in a list
    // and returns a new list composed of the resulting elements

    // The notation "x => x + 1" reads as "the function that associates x + 1 to x"
    // This notation defines an anonymous function, also dubbed as "lambda"
    // The compiler will infer the type of the parameter (x in the example above) whenever possible
    a.map(v => v * 3) should equal(__)

    // filter needs a function that takes an element of the list and returns a Boolean
    // it keeps only the elements for which that function returns true

    a.filter(v => v % 3 == 0) should equal(__)

    val c = List(1, 2, 5, 8, 9)
    val b = c.filterNot(v => v % 5 == 0)

    c should equal(List(1, 2, 5, 8, 9)) // the original list is not modified, as usual

    b should equal(__)
  }

  /**
    * There is a shorter syntax to create lambdas (anonymous functions)
    * When the parameter's type is unambiguous, both following notations are equivalent:
    *
    *   x => x + 2
    *
    *   _ + 2
    *
    * The _ keyword stands as a placeholder for the parameter in the lambda's body.
    * Be aware though that when using this notation, each occurrence of the _ placeholder
    * refers to a new parameter. In other words,
    *
    *   _ + _
    *
    *   is equivalent to
    *
    *   (x, y) => x + y
    *
    *   and not to
    *
    *   x => x + x
    */
  exercise("Lambda's can be defined with a shorter syntax") {
    val a = List(1, 2, 3)

    a.map(_ * 2) should equal(__)

    a.filter(_ % 2 != 0) should equal(__)
  }
}
