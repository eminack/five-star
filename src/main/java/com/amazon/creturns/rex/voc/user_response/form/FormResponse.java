package com.amazon.creturns.rex.voc.user_response.form;

import com.amazon.creturns.rex.voc.user_response.AbstractResponse;
import com.amazon.creturns.rex.voc.user_response.widget.AbstractWidgetResponse;
import com.amazon.creturns.rex.voc.user_response.widget.WidgetResponseSerializer;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class FormResponse extends AbstractResponse {
    private String formId;
    private List<AbstractWidgetResponse> responseList;

    FormResponse() {
        super();
        responseList = new ArrayList<>();
    }

    @JsonIgnore
    public Item createDynamoDbItem() {
        //Responses of widget in Form are saved as Json String in DB
        List<String> widgetResponses = new ArrayList<>();
        for (AbstractWidgetResponse ab : responseList) {
            widgetResponses.add(WidgetResponseSerializer.serialize(ab));
        }
        return new Item()
                .withPrimaryKey("userId", this.getUserId(),
                                "responseId", this.getResponseId())
                .withString("createTime", this.getCreateTime())
                .withString("formId", this.formId)
                .withList("responseList", widgetResponses);
    }
}
