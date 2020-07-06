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
    private String title;
    private String hint;

    public ShortAnswer() {
        super();
    }

    @JsonIgnore
    @Override
    public void updateDetails(AbstractWidget widget) {
        widget = (ShortAnswer) widget;
        this.getMetaData().updateLastUpdateTime();
        this.setWidgetVersion(this.getWidgetVersion() + 1);
        this.setWidgetName(widget.getWidgetName());
        this.title = ((ShortAnswer) widget).title;
        this.hint = ((ShortAnswer) widget).hint;
    }

    @JsonIgnore
    @Override
    public Item createDynamoDbItem() {
        return new Item()
                .withPrimaryKey("widgetId", this.getWidgetId())
                .withString("widgetJSONString", WidgetSerializer.serialize(this));
    }
}
