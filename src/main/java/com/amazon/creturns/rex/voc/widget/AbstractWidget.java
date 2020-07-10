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

    /**
     * Id of Widget , it is a UUID.
     */
    private String widgetId;
    /**
     * variable containing the String-Id for name of widget.
     */
    private String widgetName;
    private String widgetType; // ("SHORT_ANSWER" / "FIVE_STAR")
    private int widgetVersion;

    /**
     * variable containing the name of HTML template.
     */
    private String templateId; // ("fivestar,"shortAns")
    private AuditingInfo metaData;
    private WidgetConstraintCollection constraints;


    protected AbstractWidget() {
        if (widgetId == null) {
            this.widgetId = UUID.randomUUID().toString().replaceAll("-", "");
        }
    }

    /**
     * method to update extra fields of subclass object of AbstractWidget.
     * @param widget object which contains the new values for each extra
     *               field of subclass.
     */
    @JsonIgnore
    public abstract void updateDetails(final AbstractWidget widget);

    /**
     * set constraints field with new WidgetConstraintCollection object.
     * @param constraintCollection object to which constraints fields will be set.
     */
    @JsonIgnore
    public void updateConstraints(final WidgetConstraintCollection constraintCollection) {
        this.constraints = constraintCollection;
    }

    /**
     * set constraints field to null.
     */
    @JsonIgnore
    public void deleteAllConstraint() {
        this.constraints = null;
    }

    /**
     * @param widgetId : Id of widget, which is deleted.
     *
     *  Since a widget is deleted from DB, we need to remove all constraint that other
     *  widgets may have with this widget , so it deletes those constraints &
     *   also if some widget had constraint with only the deleted widget, then after deletion
     *   no constraints will be left ,so we set the constraints field to null for this widget.
     */
    @JsonIgnore
    public void removeDeletedWidgetFromConstraints(final String widgetId) {

        /* check if there are any constraints , if yes then only further check if any constraint with
            deleted widget
         */
        if (this.constraints != null) {
            this.constraints.removeWidgetByIdFromConstraints(widgetId);

            if (this.constraints.isCollectionEmpty()) {
                this.setConstraints(null);
            }
        }
    }

    /**
     * update LastServeTime for this widget to current system time.
     */
    @JsonIgnore
    public  void updateLastServeTime() {
        this.metaData.updateLastServeTime();
    }

    /**
     * update LastUpdateTime for this widget to current system time.
     */
    @JsonIgnore
    public void updateLastUpdateTime() {
        this.metaData.updateLastUpdateTime();
    }
}


