package com.amazon.creturns.rex.voc.user_response.widget;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Log4j2
@Component
public class WidgetResponseDeserializer {
    @Autowired
    private WidgetResponseTypeFactory widgetResponseTypeFactory;
    private static ObjectMapper mapper;

    public WidgetResponseDeserializer(){
        mapper = new ObjectMapper();
    }

    public <T extends AbstractWidgetResponse> T deserialize(String jsonString,String type){
        try{
            return (T) mapper.readValue(jsonString,widgetResponseTypeFactory.getWidgetResponseTypeClass(type));
        }catch (Exception ex){
            log.error("Error in deserializing JSON String to AbstractWidgetResponse "+ ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }
    public String getWidgetType(String jsonString){
        String type="";
        try {
            JsonNode jsonNode = mapper.readTree(jsonString);
            JsonNode node =  jsonNode.get("widgetType");
            type = node.toString();
        }catch (Exception ex){
            log.error("Error in getting WidgetType from WidgetResponse JSON string " + ex.getMessage());
            ex.printStackTrace();
        }
        return type.replace("\"","");
    }
}
