package com.amazon.creturns.rex.voc.repositories.widget;

import com.amazon.creturns.rex.voc.widget.AbstractWidget;
import com.amazon.creturns.rex.voc.widget.WidgetDeserializer;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Log4j2
@Component
public class WidgetRepository {
    @Autowired
    private WidgetDeserializer widgetDeserializer;

    @Autowired
    private DynamoDB dynamoDB;

    private Table table;

    public void save(AbstractWidget obj){
        table = dynamoDB.getTable("widget");
        Item item = obj.createDynamoDbItem();
        try{
            PutItemOutcome outcome = table.putItem(item);
        }catch(Exception ex){
           log.error("Error in Saving Widget To DB " + ex.getMessage());
           ex.printStackTrace();
        }
    }
    public AbstractWidget getSingleWidgetById(String Id){
        table = dynamoDB.getTable("widget");
        GetItemSpec spec = new GetItemSpec().withPrimaryKey("widgetId",Id);
        AbstractWidget obj = null;
        try{
            Item outcome = table.getItem(spec);
            String jsonString = outcome.toJSON();
            String widgetType = widgetDeserializer.getWidgetType(jsonString);
            obj =  widgetDeserializer.deserialize(jsonString,widgetType);

        }catch (Exception ex){
            log.error("Error in fetching Widget from DB " + ex.getMessage());
            ex.printStackTrace();
        }
        return obj;
    }

    public List<AbstractWidget> getAllWidgets() {
        ArrayList<AbstractWidget> widgetList = new ArrayList<>();
        table = dynamoDB.getTable("widget");
        ScanSpec scanSpec = new ScanSpec();
        try{
            ItemCollection<ScanOutcome> items = table.scan(scanSpec);
            Iterator<Item> iter = items.iterator();
            while(iter.hasNext()){
                Item item = iter.next();
                String jsonString = item.toJSON();
                String widgetType = widgetDeserializer.getWidgetType(jsonString);
                widgetList.add(widgetDeserializer.deserialize(jsonString,widgetType));
            }
        }catch (Exception ex){
            log.error("Error in fetching all Widgets From DB " + ex.getMessage());
            ex.printStackTrace();
        }
        return widgetList;
    }

    public void deleteWidgetById(String id) {
        table = dynamoDB.getTable("widget");
        try{
            table.deleteItem(new PrimaryKey("widgetId",id));
        }catch (Exception ex){
            log.error("Error in deleting widget From DB " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
