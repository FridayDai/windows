package specs

import org.junit.runner.RunWith
import org.junit.runners.Suite
import specs.client.ClientFunctionalSpec



@RunWith(Suite.class)
@Suite.SuiteClasses([

        AdminSpecRunner.class,
        ClientFunctionalSpec.class,
        InClinicSpecRunner.class,

])
class AllSpecRunner {
}
