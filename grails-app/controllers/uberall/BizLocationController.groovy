package uberall

import org.grails.web.json.JSONObject
import static org.springframework.http.HttpStatus.OK
import grails.config.Config
import grails.core.support.GrailsConfigurationAware


class BizLocationController {

    enum FormatType{
        JSON,
        CSV
    }

    BizLocationService bizLocationService = new BizLocationService()

    def index(){
        FormatType formatType

        try {
            formatType = FormatType.valueOf(params.get("formatType"))
        } catch (Exception e){
            formatType = FormatType.JSON
        }
        String formatTypeLowerCase = formatType.toString().toLowerCase()
        Object locationData = bizLocationService.listAllLocations(formatType)
        render(contentType:'text/'+formatTypeLowerCase, text:locationData)
    }

}
