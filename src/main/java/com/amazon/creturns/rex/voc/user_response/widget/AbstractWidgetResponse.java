package com.amazon.creturns.rex.voc.user_response.widget;

import com.amazon.creturns.rex.voc.user_response.AbstractResponse;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractWidgetResponse extends AbstractResponse {
    private String widgetId;
    private String widgetType;
    public AbstractWidgetResponse(){super();}

    @JsonIgnore
    public abstract Item createDynamoDbItem();
}
