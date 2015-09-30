package com.ratchethealth.admin

class TestDataController extends BaseController {

    def testDataService

    def index() {
        def testDataLinks = getTestData()
        render(view: '/testData/index', model: [links: testDataLinks])
    }

    def generateTestData() {
        String token = request.session.token
        def resp = testDataService.generateTestData(token)
        if (resp == true) {
            def testDataLinks = getTestData()
            render(view: '/testData/index', model: [links: testDataLinks])
        }
    }

    def getTestData() {
        String token = request.session.token
        def testDataMax = RatchetConstants.DEFAULT_TEST_DATA_SIZE
        def testData = testDataService.getTestData(token, testDataMax)

        testData?.links
    }
}
