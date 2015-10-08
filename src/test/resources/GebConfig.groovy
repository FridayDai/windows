/*
	This is the Geb configuration file.
	
	See: http://www.gebish.org/manual/current/configuration.html
*/


import geb.report.ReportState
import geb.report.Reporter
import geb.report.ReportingListener
import geb.report.ScreenshotReporter
import org.openqa.selenium.Dimension
import org.openqa.selenium.remote.DesiredCapabilities

import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.ie.InternetExplorerDriver

waiting {
	timeout = 30
	retryInterval =1
}

environments {
	
	// run via “./gradlew chromeTest”
	// See: http://code.google.com/p/selenium/wiki/ChromeDriver
	chrome {
		driver = {
			def driverInstance = new ChromeDriver()
			driverInstance.manage().window().setSize(new Dimension(1280, 768))
			driverInstance
		}
	}
	
	// run via “./gradlew firefoxTest”
	// See: http://code.google.com/p/selenium/wiki/FirefoxDriver
	firefox {
		driver = {
			def driverInstance = new FirefoxDriver()
			driverInstance.manage().window().setSize(new Dimension(1280, 768))
			driverInstance
		}
	}

	ie {
		driver = {
			// see http://code.google.com/p/selenium/issues/detail?id=1795
			//
			def ieCapabilities = DesiredCapabilities.internetExplorer()
			ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true)
			def driverInstance = new InternetExplorerDriver(ieCapabilities)
			driverInstance.manage().window().setSize(new Dimension(1280, 768))
			driverInstance
			//new InternetExplorerDriver()

			//new InternetExplorerDriver().navigate().to("http://google.com/ncr")
			//new RemoteWebDriver(new URL("http://google.com/ncr"),DesiredCapabilities.internetExplorer())
		}
	}
}

// To run the tests with all browsers just run “./gradlew test”

baseUrl = "http://admin.release.ratchethealth.com"

reporter = new ScreenshotReporter()
reportsDir = "target/geb-reports"
reportOnTestFailureOnly = true

reportingListener = new ReportingListener() {
	void onReport(Reporter reporter, ReportState reportState, List<File> reportFiles) {
		reportFiles.each {
			System.println "[[ATTACHMENT|$it.absolutePath]]"
		}
	}
}