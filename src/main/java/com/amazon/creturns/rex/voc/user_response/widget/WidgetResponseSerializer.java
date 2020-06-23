package com.amazon.creturns.rex.voc.user_response.widget;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;

@Log4j2
public final class WidgetResponseSerializer {
    public static <T extends AbstractWidgetResponse> String serialize(T response){
        ObjectMapper mapper = new ObjectMapper();
        try{
           return mapper.writeValueAsString(response);
        }catch (Exception ex){
            log.error("Error in Serializing WidgetResponse object to String " + ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }
}
