package premiers_pas

import support.HandsOnSuite

/**
*  Les for, comprendre le 'for' classique et le 'for comprehension'.
*
*  Les for sont applicable sur toutes collections.
*/

class e3_for_loops_and_comprehensions extends HandsOnSuite {

  //for classique

  exercise("The for loop is straightforward") {

    val digits = Range(0, 10)    //0, 1, 2, 3, 4, 5, 6, 7, 8, 9

    // we could also have written
    //  val digits = 0 to 9
    // or
    //  val digits = 0 until 10

    // Lets mutate some variable in a loop
    var sum = 0
    for (i <- digits) {
      sum += i
    }

    sum should equal(__)
  }

  exercise("We can add 'filters' in a for") {
    val digits = 0 until 10
    var sum = 0

    // Lets sum only even digits
    for (i <- digits if i % 2 == 0) {
      sum += i
    }

    sum should equal(__)
  }


  /**
    * When it comes to Scala, the term for-loop is a kind of misuse.
    *
    * The constructs above look like loops, but are rather named for-comprehensions in Scala.
    * In the examples above, we relied on side effects within the comprehensions because it resemble the loops you're
    * probably used to, but is not idiomatic Scala.
    *
    * But what is a for-comprehension ?
    *
    * For-comprehensions go well beyond simple iteration. They can be applied to many more data structures than just Lists.
    * A very important point is that it is the data structures that 'knows' how to navigate trough its elements, so the
    * programmer does not need to bother with that mere implementation details.
    *
    * A for-comp is composed of two parts :
    *
    *   - a series of one or more 'generators' (defined using <-) that can have an optional filter (using if)
    *   - a body where we define what we do with each individual element of the structure. The 'yield' keyword
    *     indicates that we produce a new element for each input element, thus transforming our input structure into a new one
    */
  exercise("For-comprehension can transform data structures") {
    
    
    val digits = List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)

    // Notice that a for comprehension is an expression, that can be assigned to a variable
    val aList =
      for {
        i <- digits if (i % 2) == 0
      }
      yield i

    aList should be(__)

    // List has a method sum that sums the elements values (only if it is a List of numeric type)
    aList.sum should be(__)
  }

  exercise("We can nest generators") {
    val xValues = 1 until 5
    val yValues = 1 until 3

    val coordinates = for {
      x <- xValues
      y <- yValues
    }
    yield (x, y) // this (x, y) creates a pair (2-tuple) with x and y
    coordinates(4) should be(__)
  }

}
