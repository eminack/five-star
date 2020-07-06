package com.amazon.creturns.rex.voc.user_response.widget;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;

@Log4j2
public final class WidgetResponseSerializer {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T extends AbstractWidgetResponse> String serialize(final T response) {
        try {
           return mapper.writeValueAsString(response);
        } catch (Exception ex) {
            log.error("Error in Serializing WidgetResponse object to String " + ex.getMessage());
            ex.printStackTrace();
        }
        return "";
    }
}
