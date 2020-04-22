package uberall

import grails.testing.mixin.integration.Integration
import grails.testing.services.ServiceUnitTest
import groovy.json.JsonSlurper
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import spock.lang.Specification
import uberall.BizLocationController.FormatType

@Integration
class BizLocationServiceSpec extends Specification implements ServiceUnitTest<BizLocationService>{

    def setup() {
    }

    def cleanup() {
    }

    void "test output type of listAllLocations"() {
        expect:"Type Checking"
            BizLocationService bizLocationService = new BizLocationService()
            (bizLocationService.listAllLocations(FormatType.CSV) instanceof String)
            (bizLocationService.listAllLocations(FormatType.JSON) instanceof JSONObject)
    }

    void "test output of extractRelevantFields"() {
        expect:"Output checking"
            BizLocationService bizLocationService = new BizLocationService()
            JSONObject sample_payload = new JsonSlurper().parseText('{"name":"OBI Markt Kempen","street":"Kleinbahnstraße","streetNo":"32","streetAndNumber":"Kleinbahnstraße 32","addressExtra":null,"zip":"47906","city":"Kempen","cellphone":null,"fax":null,"website":"https://www.obi.de/baumarkt/kempen/?wt_mc=GP.de292_Kempen...","email":null,"descriptionShort":null,"descriptionLong":null,"openingHoursNotes":null,"lastSyncStarted":null,"country":"DE","lat":51.3740233,"lng":6.4182039,"imprint":null,"addressDisplay":true,"autoSync":false,"skipFacebookPicturesUpdate":false,"phone":"+49 2152 898710","status":"ACTIVE","cleansingStatus":"CLEANSED","cleansingInvalidDataReason":null,"photos":[{"id":2657710,"dateCreated":"2017-11-24T12:06:25.000+01:00","repoKey":"0x8wg49WI4.jpg","locationId":620412,"description":null,"identifier":null,"main":true,"logo":false,"type":"MAIN","url":"/uploads/userpics/620412/0x8wg49WI4.jpg","publicUrl":"https://s3.eu-central-1.amazonaws.com/uberall-userpics-prod/620412/medium_0x8wg49WI4.jpg","cropHeight":null,"cropWidth":null,"cropOffsetX":null,"cropOffsetY":null},{"id":2657711,"dateCreated":"2017-11-24T12:06:25.000+01:00","repoKey":"nFCTmq9DBi.jpg","locationId":620412,"description":null,"identifier":null,"main":false,"logo":true,"type":"LOGO","url":"/uploads/userpics/620412/nFCTmq9DBi.jpg","publicUrl":"https://s3.eu-central-1.amazonaws.com/uberall-userpics-prod/620412/medium_nFCTmq9DBi.jpg","cropHeight":null,"cropWidth":null,"cropOffsetX":null,"cropOffsetY":null}],"keywords":[],"labels":[],"categories":[536],"locationSyncable":true,"businessId":280413,"socialPostId":null,"hasFacebook":false,"endDate":"2017-12-24T12:06:24.000+01:00","averageRating":0.0,"openingHours":[{"dayOfWeek":1,"from1":"08:00","to1":"20:00"},{"dayOfWeek":2,"from1":"08:00","to1":"20:00"},{"dayOfWeek":3,"from1":"08:00","to1":"20:00"},{"dayOfWeek":4,"from1":"08:00","to1":"20:00"},{"dayOfWeek":5,"from1":"08:00","to1":"20:00"},{"dayOfWeek":6,"from1":"08:00","to1":"18:00"}],"specialOpeningHours":[],"openNow":true,"attributes":[],"activeDirectoriesCount":44,"activeListingsCount":44,"publishedListingsCount":22,"profileCompleteness":63,"missingMandatoryFields":["EMAIL","KEYWORDS","SERVICES","DESCRIPTION_SHORT"],"directoriesMissingConnect":["GOOGLE","FACEBOOK"],"listingsInSync":13,"listingsBeingUpdated":19,"visibilityIndex":275,"dataPoints":2,"province":"Nordrhein-Westfalen","suggestionsForFieldsAvailable":false}')
            assert bizLocationService.extractRelevantFields(sample_payload).has("name")
            assert bizLocationService.extractRelevantFields(sample_payload).has("website") == false
    }

    void "test output of renderCsvOutput"() {
        expect:"Output checking"
        BizLocationService bizLocationService = new BizLocationService()
        JSONObject sample_payload = new JsonSlurper().parseText('{"name":"OBI Markt Kempen","street":"Kleinbahnstraße","streetNo":"32","streetAndNumber":"Kleinbahnstraße 32","addressExtra":null,"zip":"47906","city":"Kempen","cellphone":null,"fax":null,"website":"https://www.obi.de/baumarkt/kempen/?wt_mc=GP.de292_Kempen...","email":null,"descriptionShort":null,"descriptionLong":null,"openingHoursNotes":null,"lastSyncStarted":null,"country":"DE","lat":51.3740233,"lng":6.4182039,"imprint":null,"addressDisplay":true,"autoSync":false,"skipFacebookPicturesUpdate":false,"phone":"+49 2152 898710","status":"ACTIVE","cleansingStatus":"CLEANSED","cleansingInvalidDataReason":null,"photos":[{"id":2657710,"dateCreated":"2017-11-24T12:06:25.000+01:00","repoKey":"0x8wg49WI4.jpg","locationId":620412,"description":null,"identifier":null,"main":true,"logo":false,"type":"MAIN","url":"/uploads/userpics/620412/0x8wg49WI4.jpg","publicUrl":"https://s3.eu-central-1.amazonaws.com/uberall-userpics-prod/620412/medium_0x8wg49WI4.jpg","cropHeight":null,"cropWidth":null,"cropOffsetX":null,"cropOffsetY":null},{"id":2657711,"dateCreated":"2017-11-24T12:06:25.000+01:00","repoKey":"nFCTmq9DBi.jpg","locationId":620412,"description":null,"identifier":null,"main":false,"logo":true,"type":"LOGO","url":"/uploads/userpics/620412/nFCTmq9DBi.jpg","publicUrl":"https://s3.eu-central-1.amazonaws.com/uberall-userpics-prod/620412/medium_nFCTmq9DBi.jpg","cropHeight":null,"cropWidth":null,"cropOffsetX":null,"cropOffsetY":null}],"keywords":[],"labels":[],"categories":[536],"locationSyncable":true,"businessId":280413,"socialPostId":null,"hasFacebook":false,"endDate":"2017-12-24T12:06:24.000+01:00","averageRating":0.0,"openingHours":[{"dayOfWeek":1,"from1":"08:00","to1":"20:00"},{"dayOfWeek":2,"from1":"08:00","to1":"20:00"},{"dayOfWeek":3,"from1":"08:00","to1":"20:00"},{"dayOfWeek":4,"from1":"08:00","to1":"20:00"},{"dayOfWeek":5,"from1":"08:00","to1":"20:00"},{"dayOfWeek":6,"from1":"08:00","to1":"18:00"}],"specialOpeningHours":[],"openNow":true,"attributes":[],"activeDirectoriesCount":44,"activeListingsCount":44,"publishedListingsCount":22,"profileCompleteness":63,"missingMandatoryFields":["EMAIL","KEYWORDS","SERVICES","DESCRIPTION_SHORT"],"directoriesMissingConnect":["GOOGLE","FACEBOOK"],"listingsInSync":13,"listingsBeingUpdated":19,"visibilityIndex":275,"dataPoints":2,"province":"Nordrhein-Westfalen","suggestionsForFieldsAvailable":false}')
        JSONArray arr = new JSONArray()
        arr.put(sample_payload)
        assert bizLocationService.renderCsvOutput(arr) instanceof String
    }

    void "test output of renderJsonOutput"() {
        expect: "Output checking"
        BizLocationService bizLocationService = new BizLocationService()
        JSONObject sample_payload = new JsonSlurper().parseText('{"name":"OBI Markt Kempen","street":"Kleinbahnstraße","streetNo":"32","streetAndNumber":"Kleinbahnstraße 32","addressExtra":null,"zip":"47906","city":"Kempen","cellphone":null,"fax":null,"website":"https://www.obi.de/baumarkt/kempen/?wt_mc=GP.de292_Kempen...","email":null,"descriptionShort":null,"descriptionLong":null,"openingHoursNotes":null,"lastSyncStarted":null,"country":"DE","lat":51.3740233,"lng":6.4182039,"imprint":null,"addressDisplay":true,"autoSync":false,"skipFacebookPicturesUpdate":false,"phone":"+49 2152 898710","status":"ACTIVE","cleansingStatus":"CLEANSED","cleansingInvalidDataReason":null,"photos":[{"id":2657710,"dateCreated":"2017-11-24T12:06:25.000+01:00","repoKey":"0x8wg49WI4.jpg","locationId":620412,"description":null,"identifier":null,"main":true,"logo":false,"type":"MAIN","url":"/uploads/userpics/620412/0x8wg49WI4.jpg","publicUrl":"https://s3.eu-central-1.amazonaws.com/uberall-userpics-prod/620412/medium_0x8wg49WI4.jpg","cropHeight":null,"cropWidth":null,"cropOffsetX":null,"cropOffsetY":null},{"id":2657711,"dateCreated":"2017-11-24T12:06:25.000+01:00","repoKey":"nFCTmq9DBi.jpg","locationId":620412,"description":null,"identifier":null,"main":false,"logo":true,"type":"LOGO","url":"/uploads/userpics/620412/nFCTmq9DBi.jpg","publicUrl":"https://s3.eu-central-1.amazonaws.com/uberall-userpics-prod/620412/medium_nFCTmq9DBi.jpg","cropHeight":null,"cropWidth":null,"cropOffsetX":null,"cropOffsetY":null}],"keywords":[],"labels":[],"categories":[536],"locationSyncable":true,"businessId":280413,"socialPostId":null,"hasFacebook":false,"endDate":"2017-12-24T12:06:24.000+01:00","averageRating":0.0,"openingHours":[{"dayOfWeek":1,"from1":"08:00","to1":"20:00"},{"dayOfWeek":2,"from1":"08:00","to1":"20:00"},{"dayOfWeek":3,"from1":"08:00","to1":"20:00"},{"dayOfWeek":4,"from1":"08:00","to1":"20:00"},{"dayOfWeek":5,"from1":"08:00","to1":"20:00"},{"dayOfWeek":6,"from1":"08:00","to1":"18:00"}],"specialOpeningHours":[],"openNow":true,"attributes":[],"activeDirectoriesCount":44,"activeListingsCount":44,"publishedListingsCount":22,"profileCompleteness":63,"missingMandatoryFields":["EMAIL","KEYWORDS","SERVICES","DESCRIPTION_SHORT"],"directoriesMissingConnect":["GOOGLE","FACEBOOK"],"listingsInSync":13,"listingsBeingUpdated":19,"visibilityIndex":275,"dataPoints":2,"province":"Nordrhein-Westfalen","suggestionsForFieldsAvailable":false}')
        JSONArray arr = new JSONArray()
        arr.put(sample_payload)
        JSONObject jsonOutput = bizLocationService.renderJsonOutput(arr)
        assert jsonOutput.has("name")
        assert jsonOutput.has("website") == false
    }

}
