import first_steps._
import next_steps._
import we_need_to_go_deeper._
import cons_et_nil._

import org.scalatest._
import support.CustomStopper

class HandsOn extends Suite {
  override def run(testName: Option[String], reporter: Reporter, stopper: Stopper, filter: Filter,
                   configMap: Map[String, Any], distributor: Option[Distributor], tracker: Tracker) {
    if(!CustomStopper.oneTestFailed)
    super.run(testName, reporter, CustomStopper, filter, configMap, distributor, tracker)
  }
}

class HandsOnScala extends HandsOn {
  override def nestedSuites = List(
    new first_steps,
    new next_steps,
    new we_need_to_go_deeper
  )
}

class first_steps extends HandsOn {
  override def nestedSuites = List(
    new e0_vars_vals,
    new e1_classes,
    new e2_case_classes,
    new e3_for_loops_and_comprehensions,
    new e4_pattern_matching
  )
}

class next_steps extends HandsOn {
  override def nestedSuites = List(
    new e4_lists,
    new e5_functions_and_higher_order_functions,
    new e6_option,
    new e7_maps,
    new e8_sets
  )
}

class we_need_to_go_deeper extends HandsOn {
  override def nestedSuites = List(
    new e0_bags,
    new e1_generic_bag,
    new e2_algebraic_bag

  )
}
