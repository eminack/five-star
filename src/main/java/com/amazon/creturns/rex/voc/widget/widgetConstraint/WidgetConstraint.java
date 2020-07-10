package com.amazon.creturns.rex.voc.widget.widgetConstraint;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WidgetConstraint {

    /**
     * ID of widget on which a constraint depends
     */
    private String widgetId;

    /**
     * response ID of the response of widget with ID 'widgetId' on which a constraint depends
     */
    private String responseId;

    public WidgetConstraint() {
    }

}
