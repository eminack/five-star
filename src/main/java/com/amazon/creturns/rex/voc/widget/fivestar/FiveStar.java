package com.amazon.creturns.rex.voc.widget.fivestar;

import com.amazon.creturns.rex.voc.widget.AbstractWidget;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.Map;

@Getter
@Setter
public class FiveStar extends AbstractWidget{
    private String title;
    private String hint1,hint2,hint3,hint4,hint5;

    public FiveStar(){
        super();
    }

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
                .withString("hint1",hint1)
                .withString("hint2",hint2)
                .withString("hint3",hint3)
                .withString("hint4",hint4)
                .withString("hint5",hint5);
        return item;
    }
    @JsonIgnore
    @Override
    public FiveStar updateDetails(AbstractWidget widget) {
        widget = (FiveStar)widget;
        this.getMetaData().updateLastUpdateTime();
        this.setWidgetVersion(this.getWidgetVersion()+1);
        this.setWidgetName(widget.getWidgetName());
        this.title = ((FiveStar) widget).title;
        this.hint1 = ((FiveStar) widget).hint1;
        this.hint2 = ((FiveStar) widget).hint2;
        this.hint3 = ((FiveStar) widget).hint3;
        this.hint4 = ((FiveStar) widget).hint4;
        this.hint5 = ((FiveStar) widget).hint5;
        return this;
    }


}
