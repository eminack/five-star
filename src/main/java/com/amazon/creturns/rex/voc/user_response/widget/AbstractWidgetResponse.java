package com.amazon.creturns.rex.voc.user_response.widget;

import com.amazon.creturns.rex.voc.user_response.AbstractResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractWidgetResponse extends AbstractResponse {
    private String widgetId;
    private String widgetType;

    protected AbstractWidgetResponse() {
        super();
    }


}
