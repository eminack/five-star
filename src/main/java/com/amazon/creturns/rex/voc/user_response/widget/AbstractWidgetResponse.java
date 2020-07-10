package com.amazon.creturns.rex.voc.user_response.widget;

import com.amazon.creturns.rex.voc.user_response.AbstractResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractWidgetResponse extends AbstractResponse {
    /**
     * Id of widget for which the response is submitted
     */
    private String widgetId;

    /**
     * Type of widget for which the response is submitted
     */
    private String widgetType; //("FIVE_STAR,"SHORT_ANSWER")

    protected AbstractWidgetResponse() {
        super();
    }


}
