package specs

import org.junit.runner.RunWith
import org.junit.runners.Suite
import specs.api.ApiTestRunner
import specs.client.ClientFunctionalSpec
import specs.client.PatientWithoutEmailFunctionalSpec
import specs.patient.EnterEmailFunctionalSpec
import specs.patient.GeneralAfterFunctionalSpec
import specs.patient.GeneralBeforeFunctionalSpec

@RunWith(Suite.class)
@Suite.SuiteClasses([

        AdminSpecRunner.class,
        ClientFunctionalSpec.class,
        GeneralBeforeFunctionalSpec.class,
        FirstHalfQuestionnairesSpecRunner.class,
        PatientWithoutEmailFunctionalSpec.class,
        InClinicPrepareSpecRunner.class,
        SecondHalfQuestionnairesSpecRunner.class,
        EnterEmailFunctionalSpec.class,
        GeneralAfterFunctionalSpec.class,
        ApiTestRunner.class,
])
class AllSpecWithAPIRunner {
}
