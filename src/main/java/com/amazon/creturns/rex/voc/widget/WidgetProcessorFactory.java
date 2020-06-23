package com.amazon.creturns.rex.voc.widget;

import com.amazon.creturns.rex.voc.widget.fivestar.FiveStar;
import com.amazon.creturns.rex.voc.widget.fivestar.FiveStarProcessor;
import com.amazon.creturns.rex.voc.widget.shortanswer.ShortAnswer;
import com.amazon.creturns.rex.voc.widget.shortanswer.ShortAnswerProcessor;
import org.springframework.stereotype.Component;
import java.util.HashMap;
@Component
public class WidgetProcessorFactory {
        private static HashMap<String,WidgetProcessor> widgetProcessorHashMap;

        public WidgetProcessorFactory(){
            widgetProcessorHashMap = new HashMap<>();
            widgetProcessorHashMap.put("FIVE_STAR", (FiveStarProcessor) widget -> {
                if (widget.getTitle()==null) widget.setTitle(WidgetConstants.FIVE_STAR_DEFAULT_QUES);
                if (widget.getHint1()==null) widget.setHint1(WidgetConstants.FIVE_STAR_DEFAULT_HINT1);
                if (widget.getHint2()==null) widget.setHint2(WidgetConstants.FIVE_STAR_DEFAULT_HINT2);
                if (widget.getHint3()==null) widget.setHint3(WidgetConstants.FIVE_STAR_DEFAULT_HINT3);
                if (widget.getHint4()==null) widget.setHint4(WidgetConstants.FIVE_STAR_DEFAULT_HINT4);
                if (widget.getHint5()==null) widget.setHint5(WidgetConstants.FIVE_STAR_DEFAULT_HINT5);
                return widget;
            });
            widgetProcessorHashMap.put("SHORT_ANSWER", (ShortAnswerProcessor) widget -> {
                if (widget.getTitle()==null) widget.setTitle(WidgetConstants.SHORT_ANSWER_DEFAULT_TITLE);
                if (widget.getHint()==null) widget.setTitle(WidgetConstants.SHORT_ANSWER_DEFAULT_HINT);
                return widget;
            });
        }
        public WidgetProcessor getWidgetProcessor(String type){
            return widgetProcessorHashMap.get(type);
        }

    }
