package specs

import org.junit.runner.RunWith
import org.junit.runners.Suite
import specs.patient.EmailStartFunctionalSpec
import specs.patient.GeneralBeforeFunctionalSpec
import specs.patient.InClinicFunctionalSpec

@RunWith(Suite.class)
@Suite.SuiteClasses([

        GeneralBeforeFunctionalSpec.class,
        PatientSpecRunner.class,
        EmailStartFunctionalSpec,
        InClinicFunctionalSpec.class,

])

class InClinicSpecRunner {
}
