package com.amazon.creturns.rex.voc.widget.widgetConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WidgetConstraintCollection {
    /**
     * list of WidgetConstraint object , each list represents different type of constraints
     */
    private List<WidgetConstraint> makeItMandatoryConstraints;
    private List<WidgetConstraint> displayConstraints;

    public WidgetConstraintCollection() {
    }

    /**
     * It removes a particular WidgetConstraint object (identified by widgetId) from each list attributes of this class
     * @param widgetId @{widgetId} of the WidgetConstraint which is to be removed from @{displayConstraints}
     *                 & @{makeItMandatoryConstraints} fields of this class
     */
    @JsonIgnore
    public void removeWidgetByIdFromConstraints(final String widgetId) {
        this.makeItMandatoryConstraints.
                removeIf(widgetConstraint -> widgetConstraint.getWidgetId().compareTo(widgetId) == 0);
        this.displayConstraints.
                removeIf(widgetConstraint -> widgetConstraint.getWidgetId().compareTo(widgetId) == 0);
    }

    /**
     * check if all attributes of this object are empty or not
     * @return true if all empty , false otherwise
     */
    @JsonIgnore
    public boolean isCollectionEmpty() {
        return (this.makeItMandatoryConstraints.isEmpty() && this.displayConstraints.isEmpty());
    }
}
