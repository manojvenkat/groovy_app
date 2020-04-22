package uberall

import grails.converters.JSON
import grails.gorm.transactions.Transactional
import groovy.json.JsonSlurper
import org.apache.commons.validator.Form
import org.apache.groovy.json.internal.LazyMap
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import uberall.BizLocationController.FormatType

import java.lang.reflect.Array

@Transactional
class BizLocationService {

    ArrayList relevant_fields = (ArrayList)["name", "city", "zip", "streetAndNumber", "openingHours", "keyWords", "lat", "lng"]

    def listAllLocations(FormatType formatType) {
        File inputFile = new File("./grails-app/assets/data.json")
        JSONObject InputJSON = new JsonSlurper().parseText(inputFile.text)
        JSONObject responseObj = InputJSON.get("response")
        JSONArray locationsData = responseObj.get("locations")

        switch (formatType) {
            case FormatType.CSV:
                return renderCsvOutput(locationsData)
            case FormatType.JSON:
                responseObj = new JSONObject()
                responseObj.put("response", renderJsonOutput(locationsData))
                return responseObj
        }
    }

    def renderJsonOutput(JSONArray locationsData) {
        JSONArray responseObj = new JSONArray()
       for(obj in locationsData) {
            responseObj.put(extractRelevantFields((JSONObject) obj))
        }
        return responseObj
    }

    def renderCsvOutput(JSONArray locationsData) {
        String headerString = "name,city,zip,streetAndNumber,openingHours,keyWords,lat,lng"
        JSONArray jsonOutput = renderJsonOutput(locationsData)
        String dataString = ""
        for(obj in jsonOutput) {
            obj = (JSONObject) obj
            for(field in relevant_fields) {
                dataString += obj[field].toString() + "|"
            }
            dataString += "\n"
        }
        return headerString + "\n" + dataString
    }

    def extractRelevantFields(JSONObject obj) {
        JSONObject extractedObj = new JSONObject()
        for(field in relevant_fields) {
            extractedObj.put(field, obj.get(field, null))
        }
        return extractedObj
    }
}
