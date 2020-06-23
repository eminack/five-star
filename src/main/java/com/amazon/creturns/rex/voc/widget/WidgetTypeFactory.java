package com.amazon.creturns.rex.voc.widget;

import com.amazon.creturns.rex.voc.widget.fivestar.FiveStar;
import com.amazon.creturns.rex.voc.widget.shortanswer.ShortAnswer;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class WidgetTypeFactory {
    private static HashMap <String,Class> widgetTypeClassHashMap;

    WidgetTypeFactory(){
        widgetTypeClassHashMap = new HashMap<>();
        widgetTypeClassHashMap.put("FIVE_STAR", FiveStar.class);
        widgetTypeClassHashMap.put("SHORT_ANSWER", ShortAnswer.class);
    }

    public Class getWidgetTypeClass(String widgetType){
        return widgetTypeClassHashMap.get(widgetType);
    }

}
