package com.amazon.creturns.rex.voc.widget.shortanswer;

import com.amazon.creturns.rex.voc.widget.AbstractWidget;
import com.amazon.creturns.rex.voc.widget.fivestar.FiveStar;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ShortAnswer extends AbstractWidget {
    private String title,hint;
    public ShortAnswer(){super();}

    @JsonIgnore
    @Override
    public Item createDynamoDbItem() {
        Map<String,String> infoMap = this.getMetaData().getInfoAsMap();
        Item item = new Item()
                .withPrimaryKey("widgetId",this.getWidgetId())
                .withString("widgetName",this.getWidgetName())
                .withString("widgetType",this.getWidgetType())
                .withInt("widgetVersion",this.getWidgetVersion())
                .withString("templateId",this.getTemplateId())
                .withMap("metaData",infoMap)
                .withString("title",title)
                .withString("hint",hint);
        return item;
    }
    @JsonIgnore
    @Override
    public ShortAnswer updateDetails(AbstractWidget widget) {
        widget = (ShortAnswer)widget;
        this.getMetaData().updateLastUpdateTime();
        this.setWidgetVersion(this.getWidgetVersion()+1);
        this.setWidgetName(widget.getWidgetName());
        this.title = ((ShortAnswer) widget).title;
        this.hint = ((ShortAnswer) widget).hint;
        return this;
    }

}
