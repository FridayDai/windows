package specs

import org.junit.runner.RunWith
import org.junit.runners.Suite
import specs.patient.FairleyNasalSymptomFunctionalSpec
import specs.patient.HOOSFunctionalSpec
import specs.patient.HarrisHipScoreFunctionalSpec
import specs.patient.KOOSFunctionalSpec
import specs.patient.ODIFunctionalSpec


@RunWith(Suite.class)
@Suite.SuiteClasses([
        FairleyNasalSymptomFunctionalSpec.class,
        HarrisHipScoreFunctionalSpec.class,
        HOOSFunctionalSpec.class,
        KOOSFunctionalSpec.class,
        ODIFunctionalSpec.class,

])
class SecondHalfQuestionnairesSpecRunner {
}

