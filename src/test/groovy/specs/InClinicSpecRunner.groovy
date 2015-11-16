package specs

import org.junit.runner.RunWith
import org.junit.runners.Suite
import specs.patient.EmailStartFunctionalSpec
import specs.patient.EnterEmailFunctionalSpec
import specs.patient.GeneralAfterFunctionalSpec
import specs.patient.GeneralBeforeFunctionalSpec
import specs.patient.InClinicFunctionalSpec
import specs.patient.LastTaskFunctionalSpec
import specs.patient.QuickDASHFunctionalSpec

@RunWith(Suite.class)
@Suite.SuiteClasses([

        GeneralBeforeFunctionalSpec.class,
        PatientSpecRunner.class,
        QuickDASHFunctionalSpec.class,
        GeneralAfterFunctionalSpec.class,
        EmailStartFunctionalSpec,
        InClinicFunctionalSpec.class,
        PatientSpecRunner.class,
        LastTaskFunctionalSpec.class,
        EnterEmailFunctionalSpec.class,
        GeneralAfterFunctionalSpec.class,

])

class InClinicSpecRunner {
}
