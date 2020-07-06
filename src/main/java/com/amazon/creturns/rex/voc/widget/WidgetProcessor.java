package com.amazon.creturns.rex.voc.widget;

@SuppressWarnings("EmptyMethod")
public interface WidgetProcessor<T extends AbstractWidget> {
    @SuppressWarnings("unused")
    T process(T widget);
}
