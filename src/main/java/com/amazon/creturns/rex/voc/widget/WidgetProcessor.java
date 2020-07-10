package com.amazon.creturns.rex.voc.widget;

/**
 * interface containing method to process a widget
 * @param <T> denotes the concrete subclass of AbstractWidget
 */
public interface WidgetProcessor<T extends AbstractWidget> {
    /**
     * This method processes the passed widget & returns the processed widget
     * @param widget some subclass object of AbstractWidget
     * @return that subclass object after processing it
     */
    T process(T widget);
}
