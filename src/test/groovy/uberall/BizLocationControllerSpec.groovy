package uberall

import grails.testing.mixin.integration.Integration
import grails.testing.web.controllers.ControllerUnitTest
import groovy.json.JsonSlurper
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import spock.lang.Specification

@Integration
class BizLocationControllerSpec extends Specification implements ControllerUnitTest<BizLocationController> {

    def setup() {
    }

    def cleanup() {
    }

    void "test index method without params"() {
        expect:"fix me"

        when: "The index method is called with JSON output without params"
        controller.index()

        then: "The result should be in JSON"
        JSONObject json = new JsonSlurper().parseText(response.text)
        assert json.has("response")
        JSONArray locationsData = json.get('response')
        JSONObject randomElement = (JSONObject) locationsData.get(0)
        assert randomElement.has("name")
        assert randomElement.has("city")
    }

    void "test index method with params"() {
        expect:"fix me"

        when: "The index method is called with JSON output without params"
        controller.params.formatType = "JSON"
        controller.index()

        then: "The result should be in JSON"
        JSONObject json = new JsonSlurper().parseText(response.text)
        assert json.has("response")
        JSONArray locationsData = json.get('response')
        JSONObject randomElement = (JSONObject) locationsData.get(0)
        assert randomElement.has("name")
        assert randomElement.has("city")
    }

    void "test index method with CSV"() {
        expect:"fix me"

        when: "The index method is called with JSON output without params"
        controller.params.formatType = "CSV"
        controller.index()

        then: "The result should be in JSON"
        String csv = response.getOutputStream().targetStream
        String[] arr = csv.split('\n')
        assert arr[0] == "name,city,zip,streetAndNumber,openingHours,keyWords,lat,lng"
    }
}
