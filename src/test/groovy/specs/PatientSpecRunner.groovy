package specs

import org.junit.runner.RunWith
import org.junit.runners.Suite
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
	GeneralBeforeFunctionalSpec.class,
	DASHFunctionalSpec.class,
	ODIFunctionalSpec.class,
	NRSBACKFunctionalSpec.class,
	NDIFunctionalSpec.class,
	NRSNECKFunctionalSpec.class,
	QuickDASHFunctionalSpec.class,
	HOOSFunctionalSpec.class,
	KOOSFunctionalSpec.class,
	FairleyNasalSymptomFunctionalSpec.class,
	HarrisHipScoreFunctionalSpec.class,
	GeneralAfterFunctionalSpec.class
])
class PatientSpecRunner {
}
