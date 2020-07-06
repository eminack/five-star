package com.amazon.creturns.rex.voc.form;

import com.amazon.creturns.rex.voc.AuditingInfo;
import com.amazon.creturns.rex.voc.DynamoDbMethods;
import com.amazon.creturns.rex.voc.widget.AbstractWidget;
import com.amazon.creturns.rex.voc.widget.WidgetSerializer;
import com.amazon.creturns.rex.voc.widget.widgetConstraint.WidgetConstraintCollection;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
public class Form implements DynamoDbMethods {
    private String formId;
    private String formName;
    private String formStatus;
    private int formVersion;
    private AuditingInfo metaData;
    private List<AbstractWidget> widgets;

    public Form(String name) {
        this.formName = name;
        this.formId = UUID.randomUUID().toString().replaceAll("-", "");
        this.formStatus = "DRAFT";
        this.formVersion = 1;
        this.metaData = new AuditingInfo();
        this.widgets = new ArrayList<>();
    }

    @JsonIgnore
    public Item createDynamoDbItem() {
        Map<String, String> hashmap = this.metaData.getInfoAsMap();

        //widgets are stored as json string in dynamoDb
        List<String> widgets = new ArrayList<>();
        for (AbstractWidget ab : this.widgets) {
            widgets.add(WidgetSerializer.serialize(ab));
        }

        return new Item()
                .withPrimaryKey("formId", this.formId)
                .withString("formName", this.formName)
                 .withString("formStatus", this.formStatus)
                .withInt("formVersion", this.formVersion)
                .withMap("metaData", hashmap)
                .withList("widgets", widgets);
    }

    @JsonIgnore
    public void removeThisWidgetFromConstraintsOfAllWidgets(final String widgetId) {
        this.getWidgets().stream().parallel().
                forEach(widget -> {
                    widget.removeDeletedWidgetFromConstraints(widgetId);
                    widget.updateLastUpdateTime();
                });
    }

    @JsonIgnore
    public void addWidget(final AbstractWidget obj) {
        this.widgets.add(obj);
    }

    @JsonIgnore
    public void updateLastUpdateTime() {
        this.metaData.updateLastUpdateTime();
    }

    @JsonIgnore
    public void updateLastServeTime() {
        this.metaData.updateLastServeTime();
    }

    @JsonIgnore
    public void removeWidgetById(final String widgetId) {
        this.widgets.removeIf(widget -> (widget.getWidgetId().compareTo(widgetId) == 0));
    }

    @JsonIgnore
    public void updateWidgetById(final AbstractWidget newObj) {
        this.widgets.stream().parallel().
                filter(widget -> widget.getWidgetId().compareTo(newObj.getWidgetId()) == 0).
                findFirst().get().updateDetails(newObj);
    }

    @JsonIgnore
    public AbstractWidget getWidgetById(final String widgetId) {
        return this.widgets.stream().parallel().
                filter(widget -> widget.getWidgetId().compareTo(widgetId) == 0).findFirst().get();
    }

    /*find the widget and set it's constraints field*/
    @JsonIgnore
    public void addConstraintsToWidget(final String widgetId,
                                       final WidgetConstraintCollection constraintCollection) {
        AbstractWidget obj = this.widgets.stream().parallel()
                             .filter(widget -> widget.getWidgetId().compareTo(widgetId) == 0)
                                .findFirst().get();
        obj.updateConstraints(constraintCollection);
        obj.updateLastUpdateTime();
    }

    /*find thw widget and delete all its constraints*/
    @JsonIgnore
    public void deleteAllConstraintsFromWidget(final String widgetId) {
        AbstractWidget obj = this.widgets.stream().parallel()
                                .filter(widget -> widget.getWidgetId().compareTo(widgetId) == 0)
                                    .findFirst().get();
        obj.deleteAllConstraint();
        obj.updateLastUpdateTime();
    }
}
