package com.amazon.creturns.rex.voc.widget;

import com.amazon.creturns.rex.voc.AuditingInfo;
import com.amazon.creturns.rex.voc.DynamoDbMethods;
import com.amazon.creturns.rex.voc.widget.widgetConstraint.WidgetConstraintCollection;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public abstract class AbstractWidget implements DynamoDbMethods {
    private String widgetId;
    private String widgetName;
    private String widgetType;
    private int widgetVersion;
    private String templateId;
    private AuditingInfo metaData;
    private WidgetConstraintCollection constraints;

    protected AbstractWidget() {
        if (widgetId == null) {
            this.widgetId = UUID.randomUUID().toString().replaceAll("-", "");
        }
    }

    @JsonIgnore
    public abstract void updateDetails(AbstractWidget widget);

    @JsonIgnore
    public void updateConstraints(WidgetConstraintCollection constraintCollection) {
        this.constraints = constraintCollection;
    }

    @JsonIgnore
    public void deleteAllConstraint() {
        this.constraints = null;
    }

    @JsonIgnore
    public void removeDeletedWidgetFromConstraints(String widgetId) {

        if (this.constraints != null) {
            this.constraints.removeThisWidgetFromConstraints(widgetId);
            if (this.constraints.isCollectionEmpty()) {
                this.setConstraints(null);
            }
        }
    }

    @JsonIgnore
    public  void updateLastServeTime() {
        this.metaData.updateLastServeTime();
    }

    @JsonIgnore
    public void updateLastUpdateTime() {
        this.metaData.updateLastUpdateTime();
    }
}


