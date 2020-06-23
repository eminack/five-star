package com.amazon.creturns.rex.voc.repositories.widget_response;

import com.amazon.creturns.rex.voc.user_response.widget.AbstractWidgetResponse;
import com.amazon.creturns.rex.voc.user_response.widget.WidgetResponseDeserializer;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
@Log4j2
@Component
public class WidgetResponseRepository {
    @Autowired
    private DynamoDB dynamoDB;
    @Autowired
    private WidgetResponseDeserializer widgetResponseDeserializer;

    private Table table;

    public void save(AbstractWidgetResponse obj){
        table = dynamoDB.getTable("widgetResponse");
        Item item = obj.createDynamoDbItem();
        try{
            PutItemOutcome outcome = table.putItem(item);
        }catch (Exception ex){
            log.error("Error in saving widget Response to DB " + ex.getMessage());
            ex.printStackTrace();
        }
    }


    /*
    this is just for testing purposes
     to clear the whole widgetResponse table,
     will be removed in final versions
     */
    public void clearAllResponse() {
        table = dynamoDB.getTable("widgetResponse");
        ScanSpec scanSpec = new ScanSpec();
        try{
            ItemCollection<ScanOutcome> items = table.scan(scanSpec);
            Iterator<Item> itemIterator = items.iterator();
            while (itemIterator.hasNext()){
                Item item = itemIterator.next();
                String jsonString  = item.toJSON();
                String widgetType =  widgetResponseDeserializer.getWidgetType(jsonString);
                AbstractWidgetResponse obj = widgetResponseDeserializer.deserialize(jsonString,widgetType);
                table.deleteItem("userId",obj.getUserId(),"responseId",obj.getResponseId());
            }
        }catch (Exception ex){
            System.err.println("error clearing all entries");
            ex.printStackTrace();
        }
    }
}
