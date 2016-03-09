package utils

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper

class Utility {
    static String APP_VAR_PATH = "src/test/resources/var.json"

    static def writeAppVar(json, String filePath = APP_VAR_PATH) {
        new File(filePath).write(
            new JsonBuilder(json).toPrettyString()
        )
    }

    static def readAppVar(String key = '', String filePath = APP_VAR_PATH) {
        def appVar = new JsonSlurper().parseText(new File(filePath).text)

        if (key) {
            appVar[key]
        } else {
            appVar
        }
    }

    static def updateAppVar(map, String filePath = APP_VAR_PATH) {
        def appVar = readAppVar(null, filePath)
        def mapClone = map.clone()

        mapClone.each {key, value -> appVar[key] = value}

        writeAppVar(mapClone, filePath)
    }
}
