package com.amazon.creturns.rex.voc.user_response.widget.short_answer;

import com.amazon.creturns.rex.voc.user_response.widget.AbstractWidgetResponse;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShortAnswerResponse extends AbstractWidgetResponse {
    private String responseText;
    public ShortAnswerResponse(){super();}

    @JsonIgnore
    @Override
    public Item createDynamoDbItem() {
        Item item = new Item()
                .withPrimaryKey("userId",this.getUserId(),"responseId",this.getResponseId())
                .withString("createTime",this.getCreateTime())
                .withString("widgetId",this.getWidgetId())
                .withString("widgetType",this.getWidgetType())
                .withString("responseText",this.getResponseText());
        return item;
    }

}
