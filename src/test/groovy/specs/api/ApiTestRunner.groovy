package specs.api

import org.junit.runner.RunWith
import org.junit.runners.Suite
import specs.api.patient.CheckPatientFunctionalSpec
import specs.api.patient.AddPatientFunctionalTest
import specs.api.patient.CheckTreatmentFunctionalSpec
import specs.api.patient.GetPatientFunctionalTest
import specs.api.patient.UpdatePatientFunctionalTest
import specs.api.treatment.AddTreatmentFunctionalTest
import specs.api.treatment.DeleteTreatmentFunctionalTest
import specs.api.treatment.GetTreatmentFunctionalTest
import specs.api.treatment.GetTreatmentsFunctionalTest
import specs.api.treatment.UpdateTreatmentFunctionalTest

@RunWith(Suite.class)
@Suite.SuiteClasses([
        AddPatientFunctionalTest.class,
        UpdatePatientFunctionalTest.class,
        GetPatientFunctionalTest.class,
        CheckPatientFunctionalSpec.class,
        AddTreatmentFunctionalTest.class,
        GetTreatmentsFunctionalTest.class,
        UpdateTreatmentFunctionalTest.class,
        GetTreatmentFunctionalTest.class,
        DeleteTreatmentFunctionalTest.class,
        CheckTreatmentFunctionalSpec.class
    ])
class ApiTestRunner {
}

