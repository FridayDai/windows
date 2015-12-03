package specs.api.treatment

import groovy.json.JsonSlurper
import org.junit.Before
import org.junit.Test
import specs.api.RatchetAPITest
import spock.lang.Shared

import static org.junit.Assert.assertEquals


class UpdateTreatmentFunctionalTest extends RatchetAPITest {

    @Shared IDENTIFY

    @Before
    public void setupSpec() {
        IDENTIFY = new JsonSlurper().parseText(new File(APP_VAR_PATH).text).IDENTIFY

    }
    @Test
    public void updateTreatment() {
        def treatmentId = getTreatmentId()[0]
        def url = "http://api.develop.ratchethealth.com/api/v2/clients/${clientId}/patients/api${IDENTIFY}/treatments/${treatmentId}"

        def (token, dateString) = getToken('PUT', "/api/v2/clients/${clientId}/patients/api${IDENTIFY}/treatments/${treatmentId}");

        withPut(token, dateString, url) { req ->
            def resp = req
                    .queryString("groupId", "54051199")
                    .queryString("providerNpi", "2154154615")
                    .queryString("surgeryDate", getDate())
                    .asString()

            assertEquals(200, resp.status);
        }
    }

    @Test
    public void updateTreatmentWithInvalidTemplateId() {

        def url = "http://api.develop.ratchethealth.com/api/v2/clients/${clientId}/patients/api${IDENTIFY}/treatments/25143215"

        def (token, dateString) = getToken('PUT', "/api/v2/clients/${clientId}/patients/api${IDENTIFY}/treatments/25143215");

        withPut(token, dateString, url) { req ->
            def resp = req
                    .queryString("groupId", "54051199")
                    .queryString("providerNpi", "2154154615")
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
        def url = "http://api.develop.ratchethealth.com/api/v2/clients/${clientId}/patients/api${IDENTIFY}/treatments/${treatmentId}"

        def (token, dateString) = getToken('PUT', "/api/v2/clients/${clientId}/patients/api${IDENTIFY}/treatments/${treatmentId}");

        withPut(token, dateString, url) { req ->
            def resp = req
                    .queryString("groupId", "98041799")
                    .queryString("providerNpi", "2154154615")
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
        def url = "http://api.develop.ratchethealth.com/api/v2/clients/${clientId}/patients/api${IDENTIFY}/treatments/${treatmentId}"

        def (token, dateString) = getToken('PUT', "/api/v2/clients/${clientId}/patients/api${IDENTIFY}/treatments/${treatmentId}");

        withPut(token, dateString, url) { req ->
            def resp = req
                    .queryString("groupId", "54051199")
                    .queryString("providerNpi", "2154154615")
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
        def url = "http://api.develop.ratchethealth.com/api/v2/clients/${clientId}/patients/api${IDENTIFY}/treatments/${treatmentId}"

        def (token, dateString) = getToken('PUT', "/api/v2/clients/${clientId}/patients/api${IDENTIFY}/treatments/${treatmentId}");

        withPut(token, dateString, url) { req ->
            def resp = req
                    .queryString("groupId", "54051199")
                    .queryString("providerNpi", "2154154615")
                    .queryString("surgeryDate", '')
                    .asString()

            def result = slurper.parseText(resp.body)
            assertEquals(400, resp.status)
            assertEquals(114, result.error.errorId)
        }
    }

    @Test
    public void updateTreatmentWithArchivedTreatment() {

        def url = "http://api.develop.ratchethealth.com/api/v2/clients/${clientId}/patients/api20/treatments/54158022"

        def (token, dateString) = getToken('PUT', "/api/v2/clients/${clientId}/patients/api20/treatments/54158022");

        withPut(token, dateString, url) { req ->
            def resp = req
                    .queryString("groupId", "54051199")
                    .queryString("providerNpi", "2154154615")
                    .queryString("surgeryDate", getDate())
                    .asString()

            def result = slurper.parseText(resp.body)
            assertEquals(400, resp.status)
            assertEquals(113, result.error.errorId)
        }
    }
}
