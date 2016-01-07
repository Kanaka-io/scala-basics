package we_need_to_go_deeper

import support.HandsOnSuite
import util.Random
import scala.collection

/**
  * Lets build our own collection type !
  *
  * Well, this will be quite a boring and useless collection, but hopefully, it
  * will give you some insight about how Scala's collection work internally.
  *
  * We'll define the Bag structure, which is a collection containing exactly one
  * element (boring, eh ?). We'll define just enough methods on it so that users
  * can interact with it, without the need of knowing its (dead simple) internal
  * structure.
  *
  * Before jumping into the implementation (i.e, replacing the ??? bellow with
  * actual code), we need to fill the few blanks ( __ ) in the tests at the
  * bottom of the file.
  *
  * Although it is a very simple structure, the methods you'll have to implement
  * can appear a bit strange to you. But fear not, "all" you have to do is to
  * apply a precept that is pervasive in Scala programming : Just Follow the Types™
  *
  */
class e0_bags extends HandsOnSuite {

  class Bag(val content:Int) {

    /**
      * We already know about map, it simply applies the function to the content of
      * our Bag, without changing its structure.
      */
    def map(function:Int => Int):Bag = ???

    /**
      * flatmap is a slightly scarier beast. We pass if a function that returns a new
      * Bag, but instead of returning us a Bag of Bag, it somehow "flattens" (hence
      * the name) the result to give us a simple Bag back.
      *
      * Once again, just follow the types ...
      */
    def flatMap(function:Int => Bag):Bag = ???

  }

  /**
    * To pass this test we need the map function implemented on Bag
    */
  exercise("I can apply a function inside a Bag") {
    val increment:(Int => Int) = (i:Int) => i + 1

    increment(0) should be(1)

    val myLittleBagOfZero = new Bag(0)

    myLittleBagOfZero.map(increment).content should be(1)
  }

  exercise("I can use for-comprehension on Bag") {

    val myLittleBagOfZero = new Bag(0)

    val more:Int = 12345

    val myBiggerBag  = for (i <- myLittleBagOfZero) yield i + more


    myBiggerBag.content should be(__)

  }

  /**
    * For this test to pass, we need flatMap implemented on Bag.
    */
  exercise("I can use complex for-comprehension on Bag") {



    val myLittleBagOfTwo = new Bag(2)
    val myBiggerBagOfAHundred = new Bag(100)

    val myBagsMerged = for (l <- myLittleBagOfTwo; b <- myBiggerBagOfAHundred) yield  l * b

    /**
      * Now we can tell you. For comprehensions are mere syntactic sugar (yes, again. Some say Scala suffers
      * from syntax diabetes).
      *
      * But for-comprehension is really a neat feature. Without it, we would need to write the line above like :
      *   val myBagmerged = myLittleBagOfTwo.flatMap{ l => myBiggerBagOfAHundred.map(b => l * b))
      * Imagine how tedious it would be with 3 or more nested flatMap calls...
      */

    myBagsMerged.content should be(200)
  }

}