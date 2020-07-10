package com.amazon.creturns.rex.voc.user_response.widget.fivestar;

import com.amazon.creturns.rex.voc.user_response.widget.AbstractWidgetResponse;
import com.amazon.creturns.rex.voc.user_response.widget.WidgetResponseSerializer;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FiveStarResponse extends AbstractWidgetResponse {
    /**
     * It contains the rating value from 1 to 5 as provided by customer
     */
    private int ratingValue;

    /**
     * Empty constructor
     */
    public FiveStarResponse() {
        super();
    }

    /**
     * create widgetResponse item from this java object which will be saved in 'widgetResponse' table
     * @return created DynamoDB item
     */
    @JsonIgnore
    public Item createDynamoDbItem() {
        return new Item().
                withPrimaryKey("userId", this.getUserId(),
                        "responseId", this.getResponseId()).
                withString("responseJSONString",
                        WidgetResponseSerializer.serialize(this));
    }
}
