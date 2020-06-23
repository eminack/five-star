package com.amazon.creturns.rex.voc.widget;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.PrimitiveIterator;

@Component
public class WidgetDeserializer {
    @Autowired
    private WidgetTypeFactory widgetTypeFactory;
    private static ObjectMapper mapper;

    WidgetDeserializer(){
        mapper = new ObjectMapper();
    }

    public  <T extends AbstractWidget> T deserialize(String jsonString, String type){
        try{
            return (T) mapper.readValue(jsonString,widgetTypeFactory.getWidgetTypeClass(type));
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public String getWidgetType(String jsonString) {
        String type = "";
        try {
            JsonNode jsonNode = mapper.readTree(jsonString);
            JsonNode node =  jsonNode.get("widgetType");
            type = node.toString();
        }catch (Exception ex){ex.printStackTrace();}
        return type.replace("\"","");
    }


}
