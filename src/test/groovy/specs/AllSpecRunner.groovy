package specs

import org.junit.runner.RunWith
import org.junit.runners.Suite
import specs.client.ClientFunctionalSpec

@RunWith(Suite.class)
@Suite.SuiteClasses([
	AdminSpecRunner.class,
	ClientFunctionalSpec.class,
	PatientSpecRunner.class
])
class AllSpecRunner {
}
