package com.amazon.creturns.rex.voc.widget.fivestar;

import com.amazon.creturns.rex.voc.widget.AbstractWidget;
import com.amazon.creturns.rex.voc.widget.WidgetSerializer;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FiveStar extends AbstractWidget {

    /**
     * title/question to be displayed in fivestar widget
     */
    private String title;

    /**
     * below is on hover text for each of the 5 stars
     */
    private String hint1;
    private String hint2;
    private String hint3;
    private String hint4;
    private String hint5;


    public FiveStar() {
        super();
    }

    /**
     * updates this object with new values of title, all hint attributes & also updates
     * the widgetVersion, lastUpdateTime, widgetName attributes
     *
     * @param widget object which contains the new values for title,hints,Name attributes
     */
    @JsonIgnore
    @Override
    public void updateDetails(final AbstractWidget widget) {
        final FiveStar typeCastedWidget = (FiveStar) widget;

        this.getMetaData().updateLastUpdateTime();
        this.setWidgetVersion(this.getWidgetVersion() + 1);

        this.setWidgetName(typeCastedWidget.getWidgetName());
        this.title = typeCastedWidget.title;
        this.hint1 = typeCastedWidget.hint1;
        this.hint2 = typeCastedWidget.hint2;
        this.hint3 = typeCastedWidget.hint3;
        this.hint4 = typeCastedWidget.hint4;
        this.hint5 = typeCastedWidget.hint5;
    }

    /**
     * creates a widget Item from java object of this class which is to be saved in 'widget' table
     * @return created widget Item
     */
    @JsonIgnore
    @Override
    public Item createDynamoDbItem() {
        return new Item()
                .withPrimaryKey("widgetId", this.getWidgetId())
                .with("widgetJSONString", WidgetSerializer.serialize(this));
    }
}
