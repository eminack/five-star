package com.amazon.creturns.rex.voc.user_response.widget;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/**
 * This class has methods to serialize the java object into String
 */
@Log4j2
@Component
public class WidgetResponseSerializer {

    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * This method serializes the object of Any concrete subclass of AbstractWidgetResponse class
     * into a String
     *
     * @param response the object which is to be serialized
     * @param <T> Any concrete Subclass of AbstractWidgetResponse
     * @return the serialized String of object
     */
    public static <T extends AbstractWidgetResponse> String serialize(final T response) {

        try {
           return mapper.writeValueAsString(response);
        } catch (Exception ex) {
            log.error("Error in Serializing WidgetResponse object to String ", ex);
        }

        return null;
    }
}
