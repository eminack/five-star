package com.amazon.creturns.rex.voc.user_response.widget;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This class has methods to deserialize the JSON Strings into widgetResponse object
 */
@Log4j2
@Component
public class WidgetResponseDeserializer {

    @Autowired
    private WidgetResponseTypeFactory widgetResponseTypeFactory;

    private static final ObjectMapper mapper = new ObjectMapper();

    public WidgetResponseDeserializer() {
    }

    /**
     * Deserialized the JSON String of specified concrete subclass of AbstractWidgetResponse to
     * java object of that concrete subclass
     *
     * @param jsonString String which needs to be deserialized
     * @param type the type of concrete subclass of AbstractWidgetResponse
     * @param <T> Jackson mapper deserializes the string to subclass of AbstractWidgetResponse
     * @return the java object of concrete subclass
     */
    public <T extends AbstractWidgetResponse> T deserialize(final String jsonString, final String type) {

        try {
            return (T) mapper.readValue(jsonString, widgetResponseTypeFactory.getWidgetResponseTypeClass(type));
        } catch (Exception ex) {
            log.error("Error in deserializing JSON String to AbstractWidgetResponse ", ex);
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
