package next_steps

import support.HandsOnSuite

/**
*   Set, like well... Set.
*
*   some useful links :
*     - http://www.scala-lang.org/api/current/index.html#scala.collection.immutable.Set
*     - http://docs.scala-lang.org/overviews/collections/sets.html
*/

class e8_sets extends HandsOnSuite {

  exercise("Creating Set") {
    val mySet = Set("South", "East", "West", "North")
    mySet.size should be(__)

    val myOtherSet = Set("South", "East", "South", "North")
    myOtherSet.size should be(__)
  }

  exercise("Operations on Sets") {
    // addition
    val mySet = Set("South", "East", "South")
    val aNewSet = mySet + "North"

    aNewSet.contains("North") should be(__)

    // removal
    val mySetBis = Set("South", "East", "Oueast", "North")
    val aNewSetBis = mySetBis - "North"
    
    // testing if an element is present
    aNewSetBis.contains("North") should be(__)

    // multiple removals
    val myOtherSet = Set("South", "East", "West", "North")
    val aNewOtherSet = myOtherSet -- List("West", "North")

    aNewOtherSet.contains("North") should be(__)
    aNewOtherSet.contains("West") should be(__)
  }
}
