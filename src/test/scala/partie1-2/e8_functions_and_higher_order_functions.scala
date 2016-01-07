package pas_suivant

import support.HandsOnSuite

/**
  * Before digging deeper into the collections API, we need to look further into functions
  * and higher order functions
  *
  * We already know about methods, that are some kind of functions and which are defined
  * on a class (or an object) using the keyword 'def'.
  *
  *
  * Functions, on the other hand, are plain values like Ints or Booleans. We can attach
  * a function to a variable, pass it as a parameter to a method (or function), and return it
  * as a result. We can also see methods as fields of a class whose value is a function.
  *
  * Functions that manipulate other functions (by taking them as parameters and/or returning
  * other functions) are called higher-order functions. We've already seen examples of such
  * higher-order functions (or rather methods) with map and filter.
  *
  * Internally, functions are modelled as instances of a FunctionN trait. For example, functions
  * of no argument implement the Function0 trait, functions of one argument implement Function1,
  * and so on up to Function21 (that is, you cannot define functions with more than 21 arguments).
  *
  * The FunctionN traits define only one method 'apply' that take N arguments. There is a special rule
  * in Scala syntax that allow to call methods named 'apply' without typing the name, that is :
  *
  *   someInstance.apply(x)
  *
  * is equivalent to
  *
  *   someInstance(x)
  *
  * The notation "x => x + 1" we've seen before is mere syntactic sugar that eases the creation of a subclass of Function1.
  *
  * But the => keyword can also be used to denote function *types*. For example,
  *
  *   Int => Int
  *
  * denotes the type of functions that take an Int as parameter and return an Int.
  * Such types can grow to arbitrary complexity :
  *
  *   (Int, String) => Boolean          is the type of functions that take an Int and a String and return a Boolean
  *
  *   (String, Int => Int) => String    is ???
  *
  *   (Long => Int) => (Long => Long)   is ???
  *
  *   ... etc
  *
  *
  *
*/
class e8_functions_and_higher_order_functions extends HandsOnSuite {

  exercise("We can attach functions to variables") {
    /**
      * We could have defined lambda using this syntax :
      *
      * val lambda = {
      *   x: Int => x + 1
      * }
      *
      * but the use of curly braces is only needed when the body of the
      * function spans multiple expressions
      */
    val lambda = (x: Int) => x + 1
    def result = List(1, 2, 3) map lambda

    result should be(__)
  }

  /**
    * We can also use the keyword 'def' inside a method's body to create a local function
    * */
  exercise("Local functions") {
    def result = List(1, 2, 3) map (__)

    // Notice that when a function have no parameters, we don't need
    // parenthesis to call it
    result should be(List(2, 3, 4))


    def shorterSyntax = List(1, 2, 3) map ( _ * 2 )
    shorterSyntax should be(__)
  }



  /**
  *  Les fonctions de plus haut niveau peuvent retourner des fonctions
  */
  exercise("Functions returning functions") {
    def addWithoutSyntaxSugar(x: Int) = {
      new Function1[Int, Int]() {
        def apply(y: Int): Int = x + y
      }
    }

    // remember, this is equivalent to addWithoutSyntaxSugar(1).apply(2)
    addWithoutSyntaxSugar(1)(2) should be(__)

    //ou plus simplement
    def add(x: Int) = (y: Int) => x + y
    add(2)(3) should be(__)

    def fiveAdder = add(5)
    fiveAdder(42) should be(__)
  }

  exercise("Functions taking functions as parameters") {
    def makeUpper(xs: List[String]) = xs map {
      _.toUpperCase
    }
    makeUpper(List("abc", "xyz", "123")) should be(List("ABC", "XYZ", "123"))

    def makeWhatEverYouLike(xs: List[String], transformation: String => String) = xs map transformation

    makeWhatEverYouLike(List("ABC", "XYZ", "123"), ???) should be(List("abc", "xyz", "123"))

    //using it inline
    List("Scala", "Erlang", "Clojure") map {
      _.length
    } should be(__)
  }

  /**
    * Currying
    *
    * Functions enjoy a nice property called "currying", after the name of Haskell Curry, who first formalised it
    * back in the 50's.
    *
    * To put it in Scala syntax, we can say that :
    *
    *   (A, B) => C
    *
    * is strictly equivalent to :
    *
    *   A => B => C               we could also have written A => (B => C)
    *
    * In english, we would say that :
    *   a function that takes two parameters of types A and B and returns a result of type C
    * is strictly equivalent to
    *   a function of one parameter of type A that returns a function of one parameter of type B that returns a C
    *
    * In Scala, we can define functions in their curried form by specifying multiple parameters groups.
    * For example :
    *
    *   def addCurried(x: Int)(y: Int): Int = x + y
    *
    * Does the same thing that :
    *
    *   def addSimple(x: Int, y: Int): Int = x + y
    *
    * But enables us to do things like this :
    *
    *   val increment : Int => Int = addCurried(1)    which can be written more concisely :
    *   val increment = addCurried(1) _               notice the _ placeholder, it tells the compiler to make a function
    *                                                 out of the partially applied parameters, rather than attempting a
    *                                                 function call (that wouldn't compile since all parameter are not
    *                                                 specified).
    *
    * Finally, we can transform a function of multiple parameters into its curried counterpart by calling the 'curried'
    * method on it.
    */
  exercise("We can curry arbitrary functions") {
    def multiply(x: Int, y: Int) = x * y
    val multiplyCurried = (multiply _).curried

    multiply(4, 5) should be(__)
    multiplyCurried(4)(5) should be(__)
  }


  exercise("Currying allows to create specialized versions of a function") {
    def customFilter(f: Int => Boolean)(xs: List[Int]) = {
      xs filter f
    }
    def isEven(x: Int) = x % 2 == 0
    val xs = List(12, 11, 5, 20, 3, 13, 2)
    customFilter(isEven)(xs) should be(__)

    val onlyEvenFilter = customFilter(isEven) _

    onlyEvenFilter(xs) should be(__)
  }
}
