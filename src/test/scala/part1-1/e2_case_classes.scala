package first_steps

import support.HandsOnSuite

/**
  *  There is a special kind of classes, namely "case classes".
  *
  *  We create a case class by simply prepending the keyword 'case' before the class definition.
  *  By doing this, the compiler will add even more "free" code to our class :
  *
  *     - the constructor is made "implicit"
  *        => no need of the keyword 'new' to create a new instance
  *     - a "natural" implementation of equals, hascode and toString are generated for us
  *     - parameters are 'public val' by default (so we do not need to type it)
  *     - a 'copy' method is generated
  *
  *  Besides making our code mode concise, case classes enable pattern matching (which we'll explore later)
  */
class e2_case_classes extends HandsOnSuite {


  /**
    * In the following exercises, we will work with the two case classes bellow
    */
  case class MyDog(name: String, race: String) // by default, paramteers are 'public val'
  case class Person(firstname: String, lastname: String, age: Int = 0, phone: String = "") // we can set default values to some parameters


  exercise("Creating instances of case classes is easy !") {


    val d1 = MyDog("Scooby", "Doberman")
    val d2 = MyDog("Rex", "Custom")
    val d3 = new MyDog("Scooby", "Doberman")    // we can use the keyword 'new', but it is not necessary
    val d4 = MyDog.apply("Rex", "Custom")       // utilisation de la méthode apply

    (d1 == d3) should be(__)
    (d1 == d2) should be(__)
    (d2 == d3) should be(__)
    (d2 == d4) should be(__)
  }

  /**
    * Equality
    *
    * In Scala, equality is structural, that means that the '==' operation compares the values passed to
    * it, and not the references, like it would do in Java
    */
  exercise("Case classes have an equals method, that 'just works'") {

    val p1 = new MyDog("Rex", "Chihuahua")
    val p2 = new MyDog("Tiger" , "Wolfhound")
    val p3 = new MyDog("Rex", "Chihuahua")

    // Scala's == is equivalent to Java's .equals
    (p1 == p2) should be(__)
    (p1 == p3) should be(__)

    // If you need to compare references, you can use Scala's eq, which is equivalent to Java's ==
    (p1 eq p2) should be(__)
    (p1 eq p3) should be(__)
  }

  /**
    * The hashcode method
    */
  exercise("Case classes come with an automatically generated hashcode method") {

    val p1 = new MyDog("Snap", "Pitbull")
    val p2 = new MyDog("Rookie", "Rotweiler")
    val p3 = new MyDog("Snap", "Pitbull")

    (p1.hashCode == p2.hashCode) should be(__)
    (p1.hashCode == p3.hashCode) should be(__)
  }


  /**
  * Accessors
  */
  exercise("Case classes come with automatically generated accessors") {

    val d1 = MyDog("Scooby", "Doberman")
    d1.name should be(__)
    d1.race should be(__)

    // What happens if we uncomment the following line ?
    //d1.name = "Scooby Doo"
  }

  exercise("We can 'update' instance using copy") {
    val d1 = MyDog("Scooby", "Doberman")

    val d2 = d1.copy(name = "Scooby Doo") // creates a copy with the name changed

    d1.name should be(__)
    d1.race should be(__)

    d2.name should be(__)
    d2.race should be(__)
  }

  exercise("We can use named parameters and default values") {

    val p1 = Person("Sherlock", "Holmes", 23, "06-XX-XX-XX-XX")

    // relying on default values
    val p2 = Person("Doctor", "Watson")

    // using named parameters, we can change the order to our needs
    val p3 = Person(phone = "01-XX-XX-XX-XX", firstname = "Professor", lastname = "Moriarty")

    // copy using named paramters
    val p4 = p3.copy(age = 23)

    p1.firstname should be("Sherlock")
    p1.lastname should be("Holmes")
    p1.age should be(23)
    p1.phone should be(__)

    p2.firstname should be("Doctor")
    p2.lastname should be(__)
    p2.age should be(0)
    p2.phone should be(__)

    p3.firstname should be(__)
    p3.lastname should be("Professor")
    p3.age should be(0)
    p3.phone should be(__)

    (p3 == p4) should be(__)
  }
}
