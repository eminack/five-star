package com.amazon.creturns.rex.voc.widget;

public interface WidgetProcessor<T extends AbstractWidget> {
    T process(T widget);
}
