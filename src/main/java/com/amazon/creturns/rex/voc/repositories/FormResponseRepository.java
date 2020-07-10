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

/**
 * This Repository class deals with all operations in "formResponse" table
 */
@Log4j2
@Component
public class FormResponseRepository {

    @Autowired
    private DynamoDB dynamoDB;

    @Autowired
    private FormResponseDeserializer formResponseDeserializer;

    private Table table;

    /**
     * This method inserts formResponse Item into table
     * @param formResponse this java object is converted into formResponse Item
     *                     which is to be inserted into the table
     */
    public void save(final FormResponse formResponse) {
        table = dynamoDB.getTable("formResponse");
        final Item item = formResponse.createDynamoDbItem();

        try {
            table.putItem(item);
        } catch (Exception ex) {
            log.error("Error in saving FormResponse to DB ", ex);
        }
    }

    /**
     * @return a list containing java object of all formResponse Item present in table
     */
    public List<FormResponse> getAllResponses() {
        final List<FormResponse> responseList = new ArrayList<>();

        table = dynamoDB.getTable("formResponse");
        final ScanSpec scanSpec = new ScanSpec();

        try {
            final ItemCollection<ScanOutcome> items = table.scan(scanSpec);

            for (final Item item : items) {
                final String jsonString = item.toJSON();
                responseList.add(formResponseDeserializer.deserializeJSONString(jsonString));
            }

        } catch (Exception ex) {
            log.error("Error in fetching all form responses ", ex);
        }

        return responseList;
    }

    /**
     * this is just for testing purposes to clear the whole table, will be removed in final versions
     */
    public void clearAllResponses() {
        table = dynamoDB.getTable("formResponse");
        final ScanSpec scanSpec = new ScanSpec();

        try {
            final ItemCollection<ScanOutcome> items = table.scan(scanSpec);

            for (final Item item : items) {
                final String JSONString = item.toJSON();

                //deserialize JSONString to FormResponse object
                FormResponse formResponse = formResponseDeserializer.deserializeJSONString(JSONString);

                table.deleteItem("userId", formResponse.getUserId(),
                                "responseId", formResponse.getResponseId());
            }
        } catch (Exception ex) {
            log.error("Error in deleting All responses ", ex);
        }
    }

}
