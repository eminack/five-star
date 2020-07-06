package com.amazon.creturns.rex.voc.widget;

import com.fasterxml.jackson.databind.ObjectMapper;

public final class WidgetSerializer {
    private static final ObjectMapper mapper = new ObjectMapper();

    public  static <T extends AbstractWidget> String serialize(final T widget) {
        try {
            return mapper.writeValueAsString(widget);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }
}
