package com.amazon.creturns.rex.voc.widget.widgetConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WidgetConstraintCollection {
    private List<WidgetConstraint> makeItMandatoryConstraints;
    private List<WidgetConstraint> displayConstraints;

    public WidgetConstraintCollection() {
    }

    @JsonIgnore
    public void removeThisWidgetFromConstraints(final String widgetId) {
        this.makeItMandatoryConstraints.
                removeIf(widgetConstraint -> widgetConstraint.getWidgetId().compareTo(widgetId) == 0);
        this.displayConstraints.
                removeIf(widgetConstraint -> widgetConstraint.getWidgetId().compareTo(widgetId) == 0);
    }

    @JsonIgnore
    public boolean isCollectionEmpty() {
        return (this.makeItMandatoryConstraints.isEmpty() && this.displayConstraints.isEmpty());
    }
}
