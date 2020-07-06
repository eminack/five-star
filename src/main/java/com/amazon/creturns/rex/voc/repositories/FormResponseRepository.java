package com.amazon.creturns.rex.voc.repositories;

import com.amazon.creturns.rex.voc.user_response.form.FormResponse;
import com.amazon.creturns.rex.voc.user_response.form.FormResponseDeserializer;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Component
public class FormResponseRepository {
    @Autowired
    private DynamoDB dynamoDB;
    @Autowired
    private FormResponseDeserializer formResponseDeserializer;

    private Table table;

    public void save(final FormResponse formResponse) {
        table = dynamoDB.getTable("formResponse");
        Item item = formResponse.createDynamoDbItem();
        try {
            table.putItem(item);
        } catch (Exception ex) {
            log.error("Error in saving FormResponse to DB " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public List<FormResponse> getAllResponses() {
        List<FormResponse> responseList = new ArrayList<>();
        table = dynamoDB.getTable("formResponse");
        ScanSpec scanSpec = new ScanSpec();
        try {
            ItemCollection<ScanOutcome> items = table.scan(scanSpec);

            for (Item item : items) {
                String jsonString = item.toJSON();
                responseList.add(formResponseDeserializer.deserializeJSONString(jsonString));
            }
        } catch (Exception ex) {
            log.error("Error in fetching all form responses " + ex.getMessage());
            ex.printStackTrace();
        }
        return responseList;
    }

    /*
    this is just for testing purposes to clear the whole
    FormResponse table, will be removed in final versions
     */
    public void clearAllResponses() {
        table = dynamoDB.getTable("formResponse");
        ScanSpec scanSpec = new ScanSpec();
        try {
            ItemCollection<ScanOutcome> items = table.scan(scanSpec);

            for (Item item : items) {
                String JSONString = item.toJSON();
                FormResponse formResponse = formResponseDeserializer.deserializeJSONString(JSONString);
                table.deleteItem("userId", formResponse.getUserId(),
                                "responseId", formResponse.getResponseId());
            }
        } catch (Exception ex) {
            log.error("Error in deleting All responses " + ex.getMessage());
            ex.printStackTrace();
        }
    }


}
