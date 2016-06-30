package we_need_to_go_deeper

import support.HandsOnSuite

/**
  * Int is a perfectly fine type, but why should we limit our Bags to it ?
  * Lets see if we can make a generic Bag !
  *
  * You know the motto : just follow the types !
  */
class e1_generic_bag  extends  HandsOnSuite {

  case class Bag[A](content:A) {

    def map[B](function: A => B):Bag[B] = ???

    def flatMap[B](function: A => Bag[B]):Bag[B] = ???

  }


  exercise("Just like before, we can 'lift' a function into our Bag") {
    val littleBag = Bag(0)

    littleBag.map(x => x + 1).content should be(1)

  }

  exercise("Lets 'combine' Bags of different types") {

    val littleIntBag = Bag(0)

    val littleStringBag = Bag("A")

    val combined = for (i <- littleIntBag; s <- littleStringBag) yield { i.toString + s}


    combined.content should be("0A")
  }

}
