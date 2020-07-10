package com.amazon.creturns.rex.voc.widget;

import com.amazon.creturns.rex.voc.widget.fivestar.FiveStar;
import com.amazon.creturns.rex.voc.widget.shortanswer.ShortAnswer;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * This factory class defines & provides the class corresponding to the String passed to it
 */
@Component
public class WidgetTypeFactory {

    /**
     * This fields maps the 'widgetType' string to its corresponding class
     */
    private static HashMap<String, Class> widgetTypeClassHashMap;

    WidgetTypeFactory() {
        widgetTypeClassHashMap = new HashMap<>();
        widgetTypeClassHashMap.put("FIVE_STAR", FiveStar.class);
        widgetTypeClassHashMap.put("SHORT_ANSWER", ShortAnswer.class);
    }

    /**
     * This method returns the Class type for the string passed to it
     * @param widgetType : String containing widget type
     * @return Java Class of that widget type
     */
    public Class getWidgetTypeClass(final String widgetType) {
        return widgetTypeClassHashMap.get(widgetType);
    }

}
