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

import java.util.*;

@Getter
@Setter
public class Form implements DynamoDbMethods {

    /**
     * ID of form object . it is a UUID
     */
    private String formId;

    /**
     * variable containing the string-ID for form name
     */
    private String formName;
    private String formStatus; //("DRAFT" / "PUBLISHED")
    private int formVersion;
    private AuditingInfo metaData;
    private List<AbstractWidget> widgets;

    /**
     * constructor which takes name and initializes formId , formStatus, formVersion,metaData,widgets
     * @param name :String-Id of name of the form
     */
    public Form(final String name) {
        this.formName = name;
        this.formId = UUID.randomUUID().toString().replaceAll("-", "");
        this.formStatus = "DRAFT";
        this.formVersion = 1;
        this.metaData = new AuditingInfo();
        this.widgets = new ArrayList<>();
    }

    /**
     * creates a form Item which can be stored into 'form' Table from this object
     * @return returns the created Item
     */
    @JsonIgnore
    public Item createDynamoDbItem() {
        final HashMap<String, String> hashmap = this.metaData.getInfoAsMap();

        //widgets are stored as json string in item
        final List<String> widgets = new ArrayList<>();
        for (final AbstractWidget ab : this.widgets) {
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

    /**
     * this method removes WidgetConstraint object having @{widgetId} as the param passed , from each of the
     * widget of this object & also sets the @{lastUpdateTime} field to current time for each of the widget
     *
     * @param widgetId ID of WidgetConstraint object which is to be removed
     */
    @JsonIgnore
    public void removeThisWidgetFromConstraintsOfAllWidgets(final String widgetId) {
        this.getWidgets().stream().parallel().forEach(widget -> {
                    widget.removeDeletedWidgetFromConstraints(widgetId);
                    widget.updateLastUpdateTime();
                });
    }

    /**
     * adds a AbstractWidget object to @{widgets} field of this object
     * @param obj object to be added to @{widgets} field
     */
    @JsonIgnore
    public void addWidget(final AbstractWidget obj) {
        this.widgets.add(obj);
    }

    /**
     * this method sets the @{lastUpdateTime} field in @{metaData} attribute to current system time
     */
    @JsonIgnore
    public void updateLastUpdateTime() {
        this.metaData.updateLastUpdateTime();
    }

    /**
     * this method sets the @{lastServeTime} field in @{metaData} attribute to current system time
     */
    @JsonIgnore
    public void updateLastServeTime() {
        this.metaData.updateLastServeTime();
    }

    /**
     * removes the AbstractWidget object having their @{widgetId} equals to param passed ,from @{widgets} field
     * @param widgetId Id of Widget object which is to be removed
     */
    @JsonIgnore
    public void removeWidgetById(final String widgetId) {
        this.widgets.removeIf(widget -> (widget.getWidgetId().compareTo(widgetId) == 0));
    }

    /**
     * call updateDetails() on AbstractWidget object which has it's @{widgetId} field same as @param{widgetId}
     * @param widgetId ID of object on which updateDetails should be called
     * @param newObj an object whose fields will be used to update the selected objects's field
     */
    @JsonIgnore
    public void updateWidgetById(final String widgetId, final AbstractWidget newObj) {
        this.widgets.stream().parallel().filter(widget -> widget.getWidgetId().compareTo(widgetId) == 0)
                .findFirst().ifPresent(widget -> widget.updateDetails(newObj));
    }

    /**
     * find widget from @{widgets} list having it's @{widgetId} field same as the param passed
     * @param widgetId ID of widget which needs to be found
     * @return the found widget object
     */
    @JsonIgnore
    public AbstractWidget getWidgetById(final String widgetId) {
        return this.widgets.stream().parallel().
                filter(widget -> widget.getWidgetId().compareTo(widgetId) == 0).
                findFirst().get();
    }

    /**
     * find the widget in @{widgets} field and call method on it to set it @{constraints} field with
     * the 2nd param passed & also sets @{lastUpdateTime} field of that widget to current system time
     * @param widgetId ID of widget whose fields need to be set
     * @param constraintCollection the @{constraints} field will be set to this java object
     */
    @JsonIgnore
    public void addConstraintsToWidget(final String widgetId, final WidgetConstraintCollection constraintCollection) {

        this.widgets.stream().parallel().filter(widget -> widget.getWidgetId().compareTo(widgetId) == 0)
                .findFirst().ifPresent(widget -> {
                widget.updateConstraints(constraintCollection);
                widget.updateLastUpdateTime();
                });

    }


    /**
     * Deletes the @{constraints} field of a particular widget in @{widgets} list & also sets the @{lastUpdateTime}
     * field of that widget to current system time
     * @param widgetId ID of widget whose constraints need to be deleted
     */
    @JsonIgnore
    public void deleteAllConstraintsFromWidget(final String widgetId) {

        this.widgets.stream().parallel().filter(widget -> widget.getWidgetId().compareTo(widgetId) == 0)
                .findFirst().ifPresent(widget -> {
                    widget.deleteAllConstraint();
                    widget.updateLastUpdateTime();
            });

    }
}
