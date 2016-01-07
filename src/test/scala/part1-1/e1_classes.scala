package first_steps

import support.HandsOnSuite

/**
  *  To define a class, we use the keyword 'class'.
  *  Inside a class we can define fields and methods.
  *  Once again, everything is public by default.
  *
  *  There are some specificities though :
  *     - we can define val/var parameters to a class (see the example bellow)
  *     - getters and setters for such parameters are automatically provided by the compiler
  *
  *  This makes the Scala syntax way more concise than its Java counterpart :
  *
  *   In Java we would write :
  *
  *     public class ClassJava {
  *
  *       String name;
  *
  *       public String getName() {
  *         return this.name;
  *       }
  *
  *       public void setName(String name) {
  *         this.name = name;
  *       }
  *     }
  *
  *
  *   The exact Scala equivalent would be :
  *
  *     class ClassScala(var name: String)
  *
  *   The generated getters/setters do not have get/set in their names, so we would use them like so :
  *
  *     println(classScalaInstance.name)
  *
  *     classScalaInstance.name = "new Name"
  *
  */

class e1_classes extends HandsOnSuite {

  // Notice that we can omit the {} if the class' body is empty
  class ClassWithValParameter(val name: String)

  /**
  * When we define an immutable parameter (field) no setter is generated
  */
  exercise("A getter is generated for a val parameter (field)") {
    val aClass = new ClassWithValParameter("name goes here")
    aClass.name should be(__)
  }

  class ClassWithVarParameter(var description: String)

  exercise("A getter and a setter are generated for a var parameter (field)") {
    val aClass = new ClassWithVarParameter("description goes here")
    aClass.description should be(__)

    aClass.description = "new description"
    aClass.description should be(__)
  }


  /**
  * We can also define private parameters (fields)
  */
  class ClassWithPrivateVarFields(private var name: String){
    override def toString: String = name
    def changeName(newname: String) = {
      name = newname
    }
  }

  exercise("Mutable private field of a class") {
    val aClass = new ClassWithPrivateVarFields("name")
    // NOTE: aClass.name is not accessible from the outside

    aClass.toString should be(__)

    aClass.changeName("newname")
    aClass.toString should be(__)
  }
}
