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
    private String title;
    private String hint1, hint2, hint3, hint4, hint5;

    public FiveStar() {
        super();
    }

    @JsonIgnore
    @Override
    public void updateDetails(AbstractWidget widget) {
        widget = (FiveStar) widget;
        this.getMetaData().updateLastUpdateTime();
        this.setWidgetVersion(this.getWidgetVersion() + 1);
        this.setWidgetName(widget.getWidgetName());
        this.title = ((FiveStar) widget).title;
        this.hint1 = ((FiveStar) widget).hint1;
        this.hint2 = ((FiveStar) widget).hint2;
        this.hint3 = ((FiveStar) widget).hint3;
        this.hint4 = ((FiveStar) widget).hint4;
        this.hint5 = ((FiveStar) widget).hint5;
    }

    @JsonIgnore
    @Override
    public Item createDynamoDbItem() {
        return new Item()
                .withPrimaryKey("widgetId", this.getWidgetId())
                .with("widgetJSONString", WidgetSerializer.serialize(this));
    }
}
