package com.amazon.creturns.rex.voc.widget.widgetConstraint;

import com.amazon.creturns.rex.voc.HttpServletRequestProcessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * This class deals with the conversion of JSON strings into WidgetConstraintCollection object
 */
@Log4j2
@Component
public class WidgetConstraintCollectionDeserializer {

    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * It creates a WidgetConstraintCollection java object from HttpServletRequest body
     * @param httpServletRequest object to HttpServletRequest
     * @return Optional<WidgetConstraintCollection> java object
     */
    public static Optional<WidgetConstraintCollection> getConstraintCollectionObjectFromHttpRequest(
                                                        final HttpServletRequest httpServletRequest) {

        final String httpBodyAsJSON = HttpServletRequestProcessor.convertRequestBodyToJsonString(httpServletRequest);

        try {
            return Optional.ofNullable(mapper.readValue(httpBodyAsJSON, WidgetConstraintCollection.class));
        } catch (Exception ex) {
            log.error("Error in deserializing constraint Collection ", ex);
        }

        return Optional.empty();
    }
}
