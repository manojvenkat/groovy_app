package uberall

import org.grails.web.json.JSONObject

class BizLocation {

    String name
    String city
    String zip
    String streetAddress
    JSONObject openingHours


    static constraints = {
        name size: 1..40, blank: false
        city size: 1..40, blank: false
        zip size: 1..10, blank: false
        streetAddress size: 1..100, blank: false
        openingHours blank: false
    }

}
