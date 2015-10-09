package specs

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite.class)
@Suite.SuiteClasses([
	ClientSmokeFunctionalSpec.class,
	PatientSmokeFunctionalSpec.class
])
class ClientPatientSpecRunner {
}
