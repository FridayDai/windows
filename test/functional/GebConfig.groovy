import org.openqa.selenium.firefox.FirefoxDriver

driver = {
    def driverInstance = new FirefoxDriver()
    driverInstance.manage().window().maximize()
    driverInstance
}

baseNavigatorWaiting = true
atCheckWaiting = true


//reportsDir = "target/geb-reports"
//baseUrl = "http://localhost:8080/ratchet-v2-admin-portal/"
