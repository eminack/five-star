package com.amazon.creturns.rex.voc.widget.shortanswer;

import com.amazon.creturns.rex.voc.widget.WidgetProcessor;

public interface ShortAnswerProcessor extends WidgetProcessor<ShortAnswer> {
    ShortAnswer process(ShortAnswer widget);
}
