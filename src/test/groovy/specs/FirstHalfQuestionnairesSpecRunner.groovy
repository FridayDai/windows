package specs

import org.junit.runner.RunWith
import org.junit.runners.Suite
import specs.patient.DASHFunctionalSpec
import specs.patient.NDIFunctionalSpec
import specs.patient.NRSBACKFunctionalSpec
import specs.patient.NRSNECKFunctionalSpec
import specs.patient.QuickDASHFunctionalSpec

@RunWith(Suite.class)
@Suite.SuiteClasses([
        DASHFunctionalSpec.class,
        NDIFunctionalSpec.class,
        NRSBACKFunctionalSpec.class,
        NRSNECKFunctionalSpec.class,
        QuickDASHFunctionalSpec.class
])
class FirstHalfQuestionnairesSpecRunner {   
}
