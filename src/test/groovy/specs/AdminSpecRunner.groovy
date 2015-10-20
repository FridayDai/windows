package specs

import org.junit.runner.RunWith
import org.junit.runners.Suite
import specs.admin.AdminFunctionalSpec

@RunWith(Suite.class)
@Suite.SuiteClasses([
	AdminFunctionalSpec.class
])
class AdminSpecRunner {
}
