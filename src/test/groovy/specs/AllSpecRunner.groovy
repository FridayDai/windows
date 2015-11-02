package specs

import org.junit.runner.RunWith
import org.junit.runners.Suite
import specs.client.ClientFunctionalSpec
import specs.patient.InClinicFunctionalSpec

@RunWith(Suite.class)
@Suite.SuiteClasses([
	AdminSpecRunner.class,
	ClientFunctionalSpec.class,
    InClinicFunctionalSpec.class

//	PatientSpecRunner.class
])
class AllSpecRunner {
}
