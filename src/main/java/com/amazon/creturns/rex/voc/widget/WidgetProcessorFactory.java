package com.amazon.creturns.rex.voc.widget;

import com.amazon.creturns.rex.voc.widget.fivestar.FiveStarProcessor;
import com.amazon.creturns.rex.voc.widget.shortanswer.ShortAnswerProcessor;
import org.springframework.stereotype.Component;
import java.util.HashMap;

/**
 * This class is factory class which defines & also provides the WidgetProcessor for the given widget type
 */
@Component
public class WidgetProcessorFactory {
    /**
     * This field maps the widget type string to its corresponding Processor interface
     */
    private static  HashMap<String, WidgetProcessor> widgetProcessorHashMap;

    /**
     * Constructor which initializes the hashMap and define the interface method for type of Widgets
     */
    public WidgetProcessorFactory() {
        widgetProcessorHashMap = new HashMap<>();

        widgetProcessorHashMap.put("FIVE_STAR", (FiveStarProcessor) widget -> {
            if (widget.getTitle() == null) {
                widget.setTitle(WidgetConstants.FIVE_STAR_DEFAULT_QUES);
            }
            if (widget.getHint1() == null) {
                widget.setHint1(WidgetConstants.FIVE_STAR_DEFAULT_HINT1);
            }
            if (widget.getHint2() == null) {
                widget.setHint2(WidgetConstants.FIVE_STAR_DEFAULT_HINT2);
            }
            if (widget.getHint3() == null) {
                widget.setHint3(WidgetConstants.FIVE_STAR_DEFAULT_HINT3);
            }
            if (widget.getHint4() == null) {
                widget.setHint4(WidgetConstants.FIVE_STAR_DEFAULT_HINT4);
            }
            if (widget.getHint5() == null) {
                widget.setHint5(WidgetConstants.FIVE_STAR_DEFAULT_HINT5);
            }
            return widget;
        });

        widgetProcessorHashMap.put("SHORT_ANSWER", (ShortAnswerProcessor) widget -> {
            if (widget.getTitle() == null) {
                widget.setTitle(WidgetConstants.SHORT_ANSWER_DEFAULT_TITLE);
            }
            if (widget.getHint() == null) {
                widget.setHint(WidgetConstants.SHORT_ANSWER_DEFAULT_HINT);
            }
            return widget;
        });
    }

    /**
     * This method returns the Processor interface mapped to the input String
     * @param type String denoting the type of AbstractWidget
     * @return Processor interface for a particular widget type
     */
    public WidgetProcessor getWidgetProcessor(final String type) {
            return widgetProcessorHashMap.get(type);
        }

}
