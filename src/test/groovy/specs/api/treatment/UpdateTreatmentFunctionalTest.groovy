package specs.api.treatment

import groovy.json.JsonSlurper
import org.junit.Before
import org.junit.Test
import specs.api.RatchetAPITest
import spock.lang.Shared

import static org.junit.Assert.assertEquals


class UpdateTreatmentFunctionalTest extends RatchetAPITest {

    @Shared IDENTIFY
    @Shared clientId
    @Shared GROUP_ID
    @Shared TREATMENT_ID
    @Shared NPI
    @Shared baseUrl = getBaseUrl()

    @Before
    public void setupSpec() {
        IDENTIFY = new JsonSlurper().parseText(new File(APP_VAR_PATH).text).IDENTIFY
        clientId = new JsonSlurper().parseText(new File(APP_VAR_PATH).text).CLIENTID
        TREATMENT_ID = new JsonSlurper().parseText(new File(APP_VAR_PATH).text).TREATMENT_ID
        GROUP_ID = new JsonSlurper().parseText(new File(APP_CLIENT_PATH).text).GROUP_ID
        NPI      = new JsonSlurper().parseText(new File(APP_CLIENT_PATH).text).NPI
    }
    @Test
    public void updateTreatment() {
        def treatmentId = getTreatmentId()[0]
        def url = "${baseUrl}/api/v2/clients/${clientId}/patients/api${IDENTIFY}/treatments/${treatmentId}"

        def (token, dateString) = getToken('PUT', "/api/v2/clients/${clientId}/patients/api${IDENTIFY}/treatments/${treatmentId}");

        withPut(token, dateString, url) { req ->
            def resp = req
                    .queryString("groupId", GROUP_ID)
                    .queryString("providerNpi", NPI)
                    .queryString("surgeryDate", getDate())
                    .asString()

            assertEquals(200, resp.status);
        }
    }

    @Test
    public void updateTreatmentWithInvalidTemplateId() {

        def url = "${baseUrl}/api/v2/clients/${clientId}/patients/api${IDENTIFY}/treatments/25143215"

        def (token, dateString) = getToken('PUT', "/api/v2/clients/${clientId}/patients/api${IDENTIFY}/treatments/25143215");

        withPut(token, dateString, url) { req ->
            def resp = req
                    .queryString("groupId", GROUP_ID)
                    .queryString("providerNpi", NPI)
                    .queryString("surgeryDate", getDate())
                    .asString()

            def result = slurper.parseText(resp.body)
            assertEquals(404, resp.status)
            assertEquals(100, result.error.errorId)
        }
    }

    @Test
    public void updateTreatmentWithNoExistGroupId() {
        def treatmentId = getTreatmentId()[0]
        def url = "${baseUrl}/api/v2/clients/${clientId}/patients/api${IDENTIFY}/treatments/${treatmentId}"

        def (token, dateString) = getToken('PUT', "/api/v2/clients/${clientId}/patients/api${IDENTIFY}/treatments/${treatmentId}");

        withPut(token, dateString, url) { req ->
            def resp = req
                    .queryString("groupId", "98041799")
                    .queryString("providerNpi", NPI)
                    .queryString("surgeryDate", getDate())
                    .asString()

            def result = slurper.parseText(resp.body)
            assertEquals(400, resp.status)
            assertEquals(110, result.error.errorId);
        }
    }

    @Test
    public void updateTreatmentWithInvalidSurgeryDate() {
        def treatmentId = getTreatmentId()[0]
        def url = "${baseUrl}/api/v2/clients/${clientId}/patients/api${IDENTIFY}/treatments/${treatmentId}"

        def (token, dateString) = getToken('PUT', "/api/v2/clients/${clientId}/patients/api${IDENTIFY}/treatments/${treatmentId}");

        withPut(token, dateString, url) { req ->
            def resp = req
                    .queryString("groupId", GROUP_ID)
                    .queryString("providerNpi", NPI)
                    .queryString("surgeryDate", "2015-03")
                    .asString()

            def result = slurper.parseText(resp.body)
            assertEquals(400, resp.status)
            assertEquals(112, result.error.errorId)
        }
    }

    @Test
    public void updateTreatmentWithNoSurgeryDate() {
        def treatmentId = getTreatmentId()[0]
        def url = "${baseUrl}/api/v2/clients/${clientId}/patients/api${IDENTIFY}/treatments/${treatmentId}"

        def (token, dateString) = getToken('PUT', "/api/v2/clients/${clientId}/patients/api${IDENTIFY}/treatments/${treatmentId}");

        withPut(token, dateString, url) { req ->
            def resp = req
                    .queryString("groupId", GROUP_ID)
                    .queryString("providerNpi", NPI)
                    .queryString("surgeryDate", '')
                    .asString()

            def result = slurper.parseText(resp.body)
            assertEquals(400, resp.status)
            assertEquals(114, result.error.errorId)
        }
    }

}
