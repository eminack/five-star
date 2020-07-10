package com.amazon.creturns.rex.voc.widget;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/**
 * This class has method for serialization of widget object into strings
 */
@Log4j2
@Component
public class WidgetSerializer {

    /**
     * It serializes the java object of any subclass of AbstractWidget into String
     *
     * @param widget Object to be serialized
     * @param <T> concrete class of the object
     * @return the serialized object as string
     */
    public static <T extends AbstractWidget> String serialize(final T widget) {
        final ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.writeValueAsString(widget);
        } catch (Exception ex) {
            log.error("Error in serializing AbstractWidget ", ex);
        }

        return null;
    }
}
