package specs

import org.junit.runner.RunWith
import org.junit.runners.Suite
import specs.client.ClientFunctionalSpec
import specs.patient.DASHFunctionalSpec
import specs.patient.FairleyNasalSymptomFunctionalSpec
import specs.patient.GeneralAfterFunctionalSpec
import specs.patient.GeneralBeforeFunctionalSpec
import specs.patient.HOOSFunctionalSpec
import specs.patient.HarrisHipScoreFunctionalSpec
import specs.patient.KOOSFunctionalSpec
import specs.patient.NDIFunctionalSpec
import specs.patient.NRSBACKFunctionalSpec
import specs.patient.NRSNECKFunctionalSpec
import specs.patient.ODIFunctionalSpec
import specs.patient.QuickDASHFunctionalSpec

@RunWith(Suite.class)
@Suite.SuiteClasses([
        ClientFunctionalSpec.class,
//        PatientSpecRunner.class
        GeneralBeforeFunctionalSpec.class,
        DASHFunctionalSpec.class,
        FairleyNasalSymptomFunctionalSpec.class,
        HarrisHipScoreFunctionalSpec.class,
        HOOSFunctionalSpec.class,
        KOOSFunctionalSpec.class,
        NDIFunctionalSpec.class,
        NRSBACKFunctionalSpec.class,
        NRSNECKFunctionalSpec.class,
        ODIFunctionalSpec.class,
        QuickDASHFunctionalSpec.class,
        GeneralAfterFunctionalSpec.class,
])
class ClientPatientSpecRunner {
}
