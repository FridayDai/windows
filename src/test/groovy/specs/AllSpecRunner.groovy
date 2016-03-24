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
        //Normal Test
//        AdminSpecRunner.class,
//        ClientFunctionalSpec.class,
//        GeneralBeforeFunctionalSpec.class,
//        FirstHalfQuestionnairesSpecRunner.class,
//        //SecondHalfQuestionnairesSpecRunner.class,
//        GeneralAfterFunctionalSpec.class,

        //Test Get Code Function
        AdminSpecRunner.class,
        ClientFunctionalSpec.class,
        InClinicPrepareSpecRunner.class,
        FirstHalfQuestionnairesSpecRunner.class,
        //SecondHalfQuestionnairesSpecRunner.class,
        GeneralAfterFunctionalSpec.class,

])
class AllSpecRunner {
}
