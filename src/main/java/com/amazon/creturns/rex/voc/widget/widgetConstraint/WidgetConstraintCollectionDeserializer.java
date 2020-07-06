package com.amazon.creturns.rex.voc.widget.widgetConstraint;

import com.amazon.creturns.rex.voc.HttpServletRequestProcessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
@Log4j2
public class WidgetConstraintCollectionDeserializer {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static WidgetConstraintCollection getConstraintCollectionObjectFromHttpRequest(HttpServletRequest
                                                                                            httpServletRequest) {

        String httpBodyAsJSON = HttpServletRequestProcessor.convertRequestBodyToJsonString(httpServletRequest);
        try {
            return mapper.readValue(httpBodyAsJSON, WidgetConstraintCollection.class);
        } catch (Exception ex) {
            log.error("Error in deserializing constraint Collection " + ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }
}
