package com.amazon.creturns.rex.voc.user_response.widget;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@SuppressWarnings("unchecked")
@Log4j2
@Component
public class WidgetResponseDeserializer {
    @Autowired
    private WidgetResponseTypeFactory widgetResponseTypeFactory;
    private static final ObjectMapper mapper = new ObjectMapper();

    public WidgetResponseDeserializer() {
    }

    public <T extends AbstractWidgetResponse> T deserialize(final String jsonString, final String type) {
        try {
            return (T) mapper.readValue(jsonString, widgetResponseTypeFactory.getWidgetResponseTypeClass(type));
        } catch (Exception ex) {
            log.error("Error in deserializing JSON String to AbstractWidgetResponse " + ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }

    public String getWidgetType(final String jsonString) {
        String type = "";
        try {
            JsonNode jsonNode = mapper.readTree(jsonString);
            JsonNode node = jsonNode.get("widgetType");
            type = node.toString();
        } catch (Exception ex) {
            log.error("Error in getting WidgetType from WidgetResponse JSON string " + ex.getMessage());
            ex.printStackTrace();
        }
        return type.replace("\"", "");
    }

    public String getResponseJSONString(final String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            return jsonObject.getString("responseJSONString");
        } catch (Exception ex) {
            log.error("Error in reading ResponseJSONString from jsonString of" +
                    "widget Response fetched from DB" +  ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }
}
