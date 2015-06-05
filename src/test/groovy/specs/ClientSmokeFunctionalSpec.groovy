package specs

import com.gmongo.GMongoClient
import com.mongodb.MongoCredential
import com.mongodb.ServerAddress
import geb.spock.GebReportingSpec
import spock.lang.Shared
import spock.lang.Stepwise

@Stepwise
class ClientSmokeFunctionalSpec extends GebReportingSpec {
	@Shared IDENTIFY

	def setupSpec() {
		def credentials = MongoCredential.createMongoCRCredential('albert.zhang', 'ratchet-tests', 'Passw0rd_1' as char[])
		def client = new GMongoClient(new ServerAddress('ds043012.mongolab.com', 43012), [credentials])
		def db = client.getDB('ratchet-tests');

		IDENTIFY = db.smoking.findOne(name: 'IDENTIFY').value
	}

	def 'assert identify'() {
		when:
		def a = 1

		then:
		a == 1
		IDENTIFY == 'asdasdad'
	}
}
