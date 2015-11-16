package specs

import org.junit.runner.RunWith
import org.junit.runners.Suite
import specs.client.ClientFunctionalSpec
import specs.patient.EmailStartFunctionalSpec


@RunWith(Suite.class)
@Suite.SuiteClasses([

        AdminSpecRunner.class,
        ClientFunctionalSpec.class,
        InClinicSpecRunner.class,
        PatientSpecRunner.class

])
class AllSpecRunner {
}
