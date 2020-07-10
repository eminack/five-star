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


/**
 * This Repository class deals with all the Operations on "widgetResponse" Table in DB
 */
@Log4j2
@Component
public class WidgetResponseRepository {

    @Autowired
    private DynamoDB dynamoDB;

    @Autowired
    private WidgetResponseDeserializer widgetResponseDeserializer;

    private Table table;

    /**
     * This method inserts the widgetResponse item into the Table
     * @param obj a java object which is converted to widgetResponse Item and inserted into table
     */
    public void save(final AbstractWidgetResponse obj) {
        table = dynamoDB.getTable("widgetResponse");

        /*create DynamoDB Item from this object*/
        final Item item = obj.createDynamoDbItem();

        try {
            table.putItem(item);
        } catch (Exception ex) {
            log.error("Error in saving widget Response to DB ", ex);
        }
    }

    /**
     * fetch All widgetResponse Items from table & convert then into AbstractWidgetResponse java object
     * @return a list containing java objects of all widgetResponse item
     */
    public List<AbstractWidgetResponse> getAllResponses() {
        table = dynamoDB.getTable("widgetResponse");

        final List<AbstractWidgetResponse> responseList = new ArrayList<>();
        final ScanSpec scanSpec = new ScanSpec();

        try {
            final ItemCollection<ScanOutcome> items = table.scan(scanSpec);

            for (final Item item : items) {
                final String element = item.toJSON();

                final String responseJSONString = widgetResponseDeserializer.
                        getValueForKeyInJSONString("responseJSONString", element);

                final String responseType = widgetResponseDeserializer.
                        getValueForKeyInJSONString("widgetType", responseJSONString);

                responseList.add(widgetResponseDeserializer.deserialize(responseJSONString, responseType));
            }

        } catch (Exception ex) {
            log.error("Error in fetching all widget responses ", ex);
        }

        return responseList;
    }

    /**
     * this is just for testing purposes to clear the whole widgetResponse table , will be removed in final versions
     * This method clears all the widgetResponse Item present in the table
     */
    public void clearAllResponse() {
        table = dynamoDB.getTable("widgetResponse");

        final ScanSpec scanSpec = new ScanSpec();

        try {
            final ItemCollection<ScanOutcome> items = table.scan(scanSpec);

            for (final Item item : items) {
                table.deleteItem("userId", item.getString("userId"),
                                "responseId", item.getString("responseId"));
            }
        } catch (Exception ex) {
            log.error("Error in Clearing widgetResponse Table ", ex);
        }
    }


}
