package specs

import org.junit.runner.RunWith
import org.junit.runners.Suite
import specs.patient.InClinicFunctionalSpec

@RunWith(Suite.class)
@Suite.SuiteClasses([
        InClinicFunctionalSpec.class,
])

class InClinicPrepareSpecRunner {
}
