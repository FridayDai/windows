/*
	This is the Geb configuration file.

	See: http://www.gebish.org/manual/current/configuration.html
*/


import geb.report.ReportState
import geb.report.Reporter
import geb.report.ReportingListener
import geb.report.ScreenshotReporter
import org.openqa.selenium.Dimension
import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.DesiredCapabilities

import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.ie.InternetExplorerDriver
import org.openqa.selenium.remote.RemoteWebDriver

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
			driverInstance.manage().window().setSize(new Dimension(1920, 1080))
			driverInstance
		}
	}

	// run via “./gradlew firefoxTest”
	// See: http://code.google.com/p/selenium/wiki/FirefoxDriver
	firefox {
		driver = {
			def driverInstance = new FirefoxDriver()
			driverInstance.manage().window().setSize(new Dimension(1920, 1080))
			driverInstance
		}
	}

	// run as "grails -Dgeb.env=ie test-app functional:"
  // See: https://code.google.com/p/selenium/wiki/InternetExplorerDriver
  ie {
//    String ieDriverVersion = "2.48.0"
//    String ieDriverVersionMajor = ieDriverVersion.substring(0, ieDriverVersion.lastIndexOf('.'))
//
//    String ieDriverZipFileName = "IEDriverServer_Win32_${ieDriverVersion}.zip"
//
//    String ieDriverDownloadFullPath = "http://selenium-release.storage.googleapis.com/${ieDriverVersionMajor}/${ieDriverZipFileName}"
//
//    File ieDriverLocalFile = downloadDriver(ieDriverDownloadFullPath, "IEDriverServer.exe", 'zip')
//
//    System.setProperty('webdriver.ie.driver', ieDriverLocalFile.absolutePath)
    driver = {
			def driverInstance = new InternetExplorerDriver()
			driverInstance.manage().window().setSize(new Dimension(1280, 768))
			driverInstance
		}
  }
}

//private File downloadDriver(String driverDownloadFullPath, String driverFilePath, String archiveFileExtension) {
//  File destinationDirectory = new File("target/drivers")
//  if (!destinationDirectory.exists()) {
//    destinationDirectory.mkdirs()
//  }
//
//  File driverFile = new File("${destinationDirectory.absolutePath}/${driverFilePath}")
//
//  String localArchivePath = "target/driver.${archiveFileExtension}"
//
//  if (!driverFile.exists()) {
//    def ant = new AntBuilder()
//    ant.get(src: driverDownloadFullPath, dest: localArchivePath)
//
//    if (archiveFileExtension == "zip") {
//      ant.unzip(src: localArchivePath, dest: destinationDirectory)
//    } else {
//      ant.untar(src: localArchivePath, dest: destinationDirectory, compression: 'bzip2')
//    }
//
//    ant.delete(file: localArchivePath)
//    ant.chmod(file: driverFile, perm: '700')
//  }
//
//  return driverFile
//}

// To run the tests with all browsers just run “./gradlew test”

baseUrl = "http://admin.develop.ratchethealth.com"

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
