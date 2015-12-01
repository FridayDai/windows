package specs.api.treatment

import org.junit.Test
import specs.api.RatchetAPITest

import static org.junit.Assert.assertEquals


class AddTreatmentFunctionalTest extends RatchetAPITest {
    def url = "http://api.develop.ratchethealth.com/api/v2/clients/${clientId}/patients/api${TimeMills}/treatments"
    @Test
    public void addTreatment() {
        def (token, dateString) = getToken('POST', "/api/v2/clients/${clientId}/patients/api${TimeMills}/treatments");

        withPost(token, dateString, url) { req ->
            def resp = req
                    .queryString("groupId", "54051199")
                    .queryString("providerNpi", "2154154615")
                    .queryString("treatmentTemplateId", "54132843")
                    .queryString("surgeryDate", getDate())
                    .asString()
            assertEquals(201, resp.status)
        }
    }

    @Test
    public void addTreatmentWithInvalidTemplateId() {

        def (token, dateString) = getToken('POST', "/api/v2/clients/${clientId}/patients/api${TimeMills}/treatments");

        withPost(token, dateString, url) { req ->
            def resp = req
                    .queryString("groupId", "54051199")
                    .queryString("providerNpi", "2154154615")
                    .queryString("treatmentTemplateId", "54132751")
                    .queryString("surgeryDate", getDate())
                    .asString()
            def result = slurper.parseText(resp.body)
            assertEquals(400, resp.status)
            assertEquals(103, result.error.errorId)
        }
    }

    @Test
    public void addTreatmentWithNoExistGroupId() {

        def (token, dateString) = getToken('POST', "/api/v2/clients/${clientId}/patients/api${TimeMills}/treatments");

        withPost(token, dateString, url) { req ->
            def resp = req
                    .queryString("groupId", "87511784")
                    .queryString("providerNpi", "2154154615")
                    .queryString("treatmentTemplateId", "54132843")
                    .queryString("surgeryDate", "2016-08-09")
                    .asString()
            def result = slurper.parseText(resp.body)
            assertEquals(400, resp.status)
            assertEquals(110, result.error.errorId)
        }
    }

    @Test
    public void addTreatmentWithSameSurgeryDate() {

        def (token, dateString) = getToken('POST', "/api/v2/clients/${clientId}/patients/api${TimeMills}/treatments");

        withPost(token, dateString, url) { req ->
            def resp = req
                    .queryString("groupId", "54051199")
                    .queryString("providerNpi", "2154154615")
                    .queryString("treatmentTemplateId", "54132843")
                    .queryString("surgeryDate", getDate())
                    .asString()
            def result = slurper.parseText(resp.body)
            assertEquals(400, resp.status)
            assertEquals(111, result.error.errorId)
        }
    }

    @Test
    public void addTreatmentWithInvalidSurgeryDate() {

        def (token, dateString) = getToken('POST', "/api/v2/clients/${clientId}/patients/api${TimeMills}/treatments");

        withPost(token, dateString, url) { req ->
            def resp = req
                    .queryString("groupId", "54051199")
                    .queryString("providerNpi", "2154154615")
                    .queryString("treatmentTemplateId", "54132843")
                    .queryString("surgeryDate", "2015-02")
                    .asString()
            def result = slurper.parseText(resp.body)
            assertEquals(400, resp.status)
            assertEquals(112, result.error.errorId)
        }
    }

    @Test
    public void addTreatmentWithNoSurgeryDate() {

        def (token, dateString) = getToken('POST', "/api/v2/clients/${clientId}/patients/api${TimeMills}/treatments");

        withPost(token, dateString, url) { req ->
            def resp = req
                    .queryString("groupId", "54051199")
                    .queryString("providerNpi", "2154154615")
                    .queryString("treatmentTemplateId", "54132843")
                    .queryString("surgeryDate",'')
                    .asString()
            def result = slurper.parseText(resp.body)
            assertEquals(400, resp.status)
            assertEquals(114, result.error.errorId)
        }
    }

}