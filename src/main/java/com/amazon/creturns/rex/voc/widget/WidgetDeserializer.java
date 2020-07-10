package com.amazon.creturns.rex.voc.widget;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This class deals with conversion of JSON Strings to AbstractWidget object
 */
@Log4j2
@Component
public class WidgetDeserializer {

    @Autowired
    private WidgetTypeFactory widgetTypeFactory;

    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * Deserializes the JSON string to java object of concrete class of AbstractWidget
     *
     * @param jsonString JSON string from which java object to be created
     * @param type The type of java object which should be created
     * @param <T> the concrete subclass of AbstractWidget class
     * @return The java object for that concrete class
     */
    public  <T extends AbstractWidget> T deserialize(final String jsonString, final String type) {

        try {
            return (T) mapper.readValue(jsonString, widgetTypeFactory.getWidgetTypeClass(type));
        } catch (Exception ex) {
           log.error("Error in deserializing jsonString to AbstractWidget ", ex);
        }

        return null;
    }

    /**
     * returns value for key ín input JSON String
     * @param key the ley for which value needs to be found
     * @param jsonString The JSON String containing that key
     * @return the value for that key
     */
    public String getValueForKeyInJSONString(final String key, final String jsonString) {
        try {
            return new JSONObject(jsonString).getString(key);
        } catch (Exception ex) {
            log.error("Error in getting value for key in JSON ", ex);
        }

        return null;
    }

}
