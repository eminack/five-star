package com.amazon.creturns.rex.voc.widget.shortanswer;

import com.amazon.creturns.rex.voc.widget.WidgetProcessor;

public interface ShortAnswerProcessor extends WidgetProcessor<ShortAnswer> {
    /**
     * This method process the passed widget & returns the processed widget
     * @param widget object of ShortAnswer class
     * @return object of processed ShortAnswer widget
     */
    ShortAnswer process(ShortAnswer widget);
}
