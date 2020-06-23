package com.amazon.creturns.rex.voc.widget;

import com.fasterxml.jackson.databind.ObjectMapper;

public final class WidgetSerializer {
    public  static <T extends AbstractWidget> String serialize(T widget){
        ObjectMapper mapper = new ObjectMapper();
        try{
            return mapper.writeValueAsString(widget);
        }catch (Exception ex){
            System.err.println(ex.getMessage());
        }
        return null;
    }
}
