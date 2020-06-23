package com.amazon.creturns.rex.voc.widget;

import com.amazon.creturns.rex.voc.AuditingInfo;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
public abstract class AbstractWidget {
    private String widgetId;
    private String widgetName;
    private String widgetType;
    private int widgetVersion;
    private String templateId;
    private AuditingInfo metaData;
    public AbstractWidget(){
        if (widgetId==null) this.widgetId = UUID.randomUUID().toString().replaceAll("-","");
    }

    @JsonIgnore
    public abstract Item createDynamoDbItem();
    @JsonIgnore
    public abstract AbstractWidget updateDetails(AbstractWidget widget);
}


