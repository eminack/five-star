package com.amazon.creturns.rex.voc.repositories;

import com.amazon.creturns.rex.voc.user_response.widget.AbstractWidgetResponse;
import com.amazon.creturns.rex.voc.user_response.widget.WidgetResponseDeserializer;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Component
public class WidgetResponseRepository {
    @Autowired
    private DynamoDB dynamoDB;
    @Autowired
    private WidgetResponseDeserializer widgetResponseDeserializer;

    private Table table;

    public void save(final AbstractWidgetResponse obj) {
        table = dynamoDB.getTable("widgetResponse");
        Item item = obj.createDynamoDbItem();
        try {
            table.putItem(item);
        } catch (Exception ex) {
            log.error("Error in saving widget Response to DB " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public List<AbstractWidgetResponse> getAllResponses() {
        table = dynamoDB.getTable("widgetResponse");
        List<AbstractWidgetResponse> responseList = new ArrayList<>();
        ScanSpec scanSpec = new ScanSpec();
        try {
            ItemCollection<ScanOutcome> items = table.scan(scanSpec);
            for (Item item : items) {
                String element = item.toJSON();
                String responseJSONString = widgetResponseDeserializer.getResponseJSONString(element);
                String responseType = widgetResponseDeserializer.getWidgetType(responseJSONString);
                responseList.add(widgetResponseDeserializer.
                                 deserialize(responseJSONString, responseType));
            }
        } catch (Exception ex) {
            log.error("Error in fetching all widget responses " + ex.getMessage());
            ex.printStackTrace();
        }
        return responseList;
    }

    /*
    this is just for testing purposes
     to clear the whole widgetResponse table,
     will be removed in final versions
     */
    public void clearAllResponse() {
        table = dynamoDB.getTable("widgetResponse");
        ScanSpec scanSpec = new ScanSpec();
        try {
            ItemCollection<ScanOutcome> items = table.scan(scanSpec);

            for (Item item : items) {
                table.deleteItem("userId", item.getString("userId"),
                                "responseId", item.getString("responseId"));
            }

        } catch (Exception ex) {
           log.error("Error in Clearing widgetResponse Table " + ex.getMessage());
            ex.printStackTrace();
        }
    }


}
