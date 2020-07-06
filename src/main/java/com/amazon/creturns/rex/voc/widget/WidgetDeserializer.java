package com.amazon.creturns.rex.voc.widget;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class WidgetDeserializer {
    @Autowired
    private WidgetTypeFactory widgetTypeFactory;
    private static final ObjectMapper mapper = new ObjectMapper();

    WidgetDeserializer() {

    }

    public  <T extends AbstractWidget> T deserialize(final String jsonString, final String type) {

        try {
            return (T) mapper.readValue(jsonString, widgetTypeFactory.getWidgetTypeClass(type));
        } catch (Exception ex) {
           log.error("Error in deserializing jsonString to AbstractWidget" + ex.getMessage());
           ex.printStackTrace();
        }

        return null;
    }

    public String getWidgetType(final String jsonString) {
        String type = "";

        try {
            JsonNode jsonNode = mapper.readTree(jsonString);
            JsonNode node =  jsonNode.get("widgetType");
            type = node.toString();
        } catch (Exception ex) {
            log.error("Error in getting widgetType from widgetString" + ex.getMessage());
            ex.printStackTrace();
        }
        return type.replace("\"", "");
    }


    public String getWidgetJSONString(final String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            return jsonObject.getString("widgetJSONString");
        } catch (Exception ex) {
            log.error("Error in reading widgetJSONString from jsonString of" +
                    "widget fetched from DB" +  ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }
}
