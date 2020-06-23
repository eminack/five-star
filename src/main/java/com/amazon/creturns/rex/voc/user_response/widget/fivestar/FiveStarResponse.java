package com.amazon.creturns.rex.voc.user_response.widget.fivestar;

import com.amazon.creturns.rex.voc.user_response.widget.AbstractWidgetResponse;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FiveStarResponse extends AbstractWidgetResponse {
    private int ratingValue;
    public FiveStarResponse(){super();}

    @JsonIgnore
    @Override
    public Item createDynamoDbItem() {
        Item item = new Item().
                withPrimaryKey("userId",this.getUserId(),"responseId",this.getResponseId())
                .withString("createTime",this.getCreateTime())
                .withString("widgetId",this.getWidgetId())
                .withString("widgetType",this.getWidgetType())
                .withInt("ratingValue",this.ratingValue);
        return item;
    }
}
