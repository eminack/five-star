package com.amazon.creturns.rex.voc.widget.shortanswer;

import com.amazon.creturns.rex.voc.widget.AbstractWidget;
import com.amazon.creturns.rex.voc.widget.WidgetSerializer;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShortAnswer extends AbstractWidget {
    /**
     * Title of the short answer widget
     */
    private String title;

    /**
     * hint / placeholder to be displayed in text area
     */
    private String hint;


    public ShortAnswer() {
        super();
    }

    /**
     * updates this object with new values of title, hint attributes & also updates
     * the widgetVersion, lastUpdateTime, widgetName attributes
     *
     * @param widget object which contains the new values for title,hint,Name attributes
     */
    @JsonIgnore
    @Override
    public void updateDetails(final AbstractWidget widget) {
        final ShortAnswer typeCastedWidget = (ShortAnswer) widget;

        this.getMetaData().updateLastUpdateTime();
        this.setWidgetVersion(this.getWidgetVersion() + 1);

        this.setWidgetName(typeCastedWidget.getWidgetName());
        this.title = typeCastedWidget.title;
        this.hint = typeCastedWidget.hint;
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
                .withString("widgetJSONString", WidgetSerializer.serialize(this));
    }
}
