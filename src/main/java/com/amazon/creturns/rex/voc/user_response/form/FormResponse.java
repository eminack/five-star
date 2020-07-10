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
    /**
     * Id of form for which the customer responded
     */
    private String formId;

    /**
     * list containing the responses for various widgets of Form
     */
    private List<AbstractWidgetResponse> responseList;

    FormResponse() {
        super();
        responseList = new ArrayList<>();
    }

    /**
     * create formResponse Item from this java object which will be saved in 'formResponse' table
     * @return the created java object
     */
    @JsonIgnore
    public Item createDynamoDbItem() {

        //each element of responseList is saved as String in "responseList" key of Item
        final List<String> widgetResponses = new ArrayList<>();
        for (final AbstractWidgetResponse ab : responseList) {
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
