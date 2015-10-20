package specs

import org.junit.runner.RunWith
import org.junit.runners.Suite
import specs.admin.AdminFunctionalSpec
import specs.client.ClientFunctionalSpec

@RunWith(Suite.class)
@Suite.SuiteClasses([
	AdminFunctionalSpec.class,
	ClientFunctionalSpec.class,
	PatientSpecRunner.class
])
class AllSpecRunner {
}
