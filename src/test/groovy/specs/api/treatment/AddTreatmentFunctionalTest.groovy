package specs.api.treatment

import groovy.json.JsonSlurper
import org.junit.Before
import org.junit.Test
import specs.api.RatchetAPITest
import spock.lang.Shared

import static org.junit.Assert.assertEquals


class AddTreatmentFunctionalTest extends RatchetAPITest {

    @Shared IDENTIFY
    @Shared url
    @Shared clientId
    @Shared GROUP_ID
    @Shared NPI
    @Shared TREATMENT_ID


    @Before
    public void setupSpec() {
        IDENTIFY = new JsonSlurper().parseText(new File(APP_VAR_PATH).text).IDENTIFY
        clientId = new JsonSlurper().parseText(new File(APP_VAR_PATH).text).CLIENTID
        TREATMENT_ID = new JsonSlurper().parseText(new File(APP_VAR_PATH).text).TREATMENT_ID
        GROUP_ID = new JsonSlurper().parseText(new File(APP_CLIENT_PATH).text).GROUP_ID
        NPI      = new JsonSlurper().parseText(new File(APP_CLIENT_PATH).text).NPI
        url = "http://api.develop.ratchethealth.com/api/v2/clients/${clientId}/patients/api${IDENTIFY}/treatments"
    }

    @Test
    public void addTreatment() {
        def (token, dateString) = getToken('POST', "/api/v2/clients/${clientId}/patients/api${IDENTIFY}/treatments");

        withPost(token, dateString, url) { req ->
            def resp = req
                    .queryString("groupId", GROUP_ID)
                    .queryString("providerNpi", NPI)
                    .queryString("treatmentTemplateId", TREATMENT_ID)
                    .queryString("surgeryDate", getDate())
                    .asString()
            assertEquals(201, resp.status)
        }
    }

    @Test
    public void addTreatmentWithInvalidTemplateId() {

        def (token, dateString) = getToken('POST', "/api/v2/clients/${clientId}/patients/api${IDENTIFY}/treatments");

        withPost(token, dateString, url) { req ->
            def resp = req
                    .queryString("groupId", GROUP_ID)
                    .queryString("providerNpi", NPI)
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

        def (token, dateString) = getToken('POST', "/api/v2/clients/${clientId}/patients/api${IDENTIFY}/treatments");

        withPost(token, dateString, url) { req ->
            def resp = req
                    .queryString("groupId", "87511784")
                    .queryString("providerNpi", NPI)
                    .queryString("treatmentTemplateId", TREATMENT_ID)
                    .queryString("surgeryDate", "2016-08-09")
                    .asString()
            def result = slurper.parseText(resp.body)
            assertEquals(400, resp.status)
            assertEquals(110, result.error.errorId)
        }
    }

    @Test
    public void addTreatmentWithSameSurgeryDate() {

        def (token, dateString) = getToken('POST', "/api/v2/clients/${clientId}/patients/api${IDENTIFY}/treatments");

        withPost(token, dateString, url) { req ->
            def resp = req
                    .queryString("groupId", GROUP_ID)
                    .queryString("providerNpi", NPI)
                    .queryString("treatmentTemplateId", TREATMENT_ID)
                    .queryString("surgeryDate", getDate())
                    .asString()
            def result = slurper.parseText(resp.body)
            assertEquals(400, resp.status)
            assertEquals(111, result.error.errorId)
        }
    }

    @Test
    public void addTreatmentWithInvalidSurgeryDate() {

        def (token, dateString) = getToken('POST', "/api/v2/clients/${clientId}/patients/api${IDENTIFY}/treatments");

        withPost(token, dateString, url) { req ->
            def resp = req
                    .queryString("groupId", GROUP_ID)
                    .queryString("providerNpi", NPI)
                    .queryString("treatmentTemplateId", TREATMENT_ID)
                    .queryString("surgeryDate", "2015-02")
                    .asString()
            def result = slurper.parseText(resp.body)
            assertEquals(400, resp.status)
            assertEquals(112, result.error.errorId)
        }
    }

    @Test
    public void addTreatmentWithNoSurgeryDate() {

        def (token, dateString) = getToken('POST', "/api/v2/clients/${clientId}/patients/api${IDENTIFY}/treatments");

        withPost(token, dateString, url) { req ->
            def resp = req
                    .queryString("groupId", GROUP_ID)
                    .queryString("providerNpi", NPI)
                    .queryString("treatmentTemplateId", TREATMENT_ID)
                    .queryString("surgeryDate",'')
                    .asString()
            def result = slurper.parseText(resp.body)
            assertEquals(400, resp.status)
            assertEquals(114, result.error.errorId)
        }
    }

}