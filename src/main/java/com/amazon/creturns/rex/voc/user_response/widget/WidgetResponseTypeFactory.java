package com.amazon.creturns.rex.voc.user_response.widget;

import com.amazon.creturns.rex.voc.user_response.widget.fivestar.FiveStarResponse;
import com.amazon.creturns.rex.voc.user_response.widget.short_answer.ShortAnswerResponse;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * This is a factory class which tells the concrete subclass of AbstractWidgetResponse class corresponding to a String
 */
@Component
public class WidgetResponseTypeFactory {
    /**
     * This attribute maps the String to corresponding concrete class of AbstractWidgetResponse
     */
    private static HashMap<String, Class> widgetResponseTypeClassHashMap;

    /**
     * constructor which initializes the HashMap & load the values into it
     */
    public WidgetResponseTypeFactory() {
        widgetResponseTypeClassHashMap = new HashMap<>();
        widgetResponseTypeClassHashMap.put("FIVE_STAR", FiveStarResponse.class);
        widgetResponseTypeClassHashMap.put("SHORT_ANSWER", ShortAnswerResponse.class);
    }

    /**
     * This method returns the class which is mapped to String passed
     * @param widgetType String for which Class has to be known
     * @return the class mapped to this String
     */
    public Class getWidgetResponseTypeClass(final String widgetType) {
        return widgetResponseTypeClassHashMap.get(widgetType);
    }
}
