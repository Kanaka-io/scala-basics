package first_steps


import support.HandsOnSuite

/**
*  The 'var' and 'val' keywords are used to declare new variables.
*  We can make variables not visible from the outside by adding the keyword 'private' before the definition
*  By default variables are public.
*
*     - var : declares a mutable variable (which value can be modified afterwards)
*
*     - val : declares an immutable variable (this is the most usual kind of variables)
*/
class e0_vars_vals extends HandsOnSuite {

  exercise("One can change the value affected to a var after it has been declared") {
    var a = 5
    anchor(a)
    a should be(__)
    
    anchor(a)

    a = 7

    anchor(a)

    a should be(__)
  }

  exercise("On the contrary, a val cannot be changed once defined") {
    val a = 5

    a should be(__)

    /*
    *  Question :
    */
    // What would happen if we were to uncomment the following lines ?
    // a = 7
    // a should be (7)
  }
}
