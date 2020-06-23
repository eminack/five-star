package com.amazon.creturns.rex.voc.user_response.widget;

import com.amazon.creturns.rex.voc.user_response.widget.fivestar.FiveStarResponse;
import com.amazon.creturns.rex.voc.user_response.widget.short_answer.ShortAnswerResponse;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class WidgetResponseTypeFactory {
    private static HashMap<String,Class> widgetResponseTypeClassHashMap;

    public WidgetResponseTypeFactory(){
        widgetResponseTypeClassHashMap = new HashMap<>();
        widgetResponseTypeClassHashMap.put("FIVE_STAR", FiveStarResponse.class);
        widgetResponseTypeClassHashMap.put("SHORT_ANSWER", ShortAnswerResponse.class);
    }
    public Class getWidgetResponseTypeClass(String widgetType){
        return widgetResponseTypeClassHashMap.get(widgetType);
    }
}
