package com.amazon.creturns.rex.voc.user_response.widget.short_answer;

import com.amazon.creturns.rex.voc.user_response.widget.AbstractWidgetResponse;
import com.amazon.creturns.rex.voc.user_response.widget.WidgetResponseSerializer;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShortAnswerResponse extends AbstractWidgetResponse {
    private String responseText;
    public ShortAnswerResponse() {
        super();
    }

    @JsonIgnore
    public Item createDynamoDbItem() {
        return new Item()
                .withPrimaryKey("userId", this.getUserId(), "responseId", this.getResponseId())
                .withString("responseJSONString", WidgetResponseSerializer.serialize(this));
    }

}
