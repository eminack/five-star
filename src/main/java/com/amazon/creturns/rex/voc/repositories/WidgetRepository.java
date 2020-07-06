package com.amazon.creturns.rex.voc.repositories;

import com.amazon.creturns.rex.voc.widget.AbstractWidget;
import com.amazon.creturns.rex.voc.widget.WidgetDeserializer;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Log4j2
@Component
public class WidgetRepository {
    @Autowired
    private WidgetDeserializer widgetDeserializer;

    @Autowired
    private DynamoDB dynamoDB;

    private Table table;

    public void save(final AbstractWidget obj) {
        table = dynamoDB.getTable("widget");
        Item item = obj.createDynamoDbItem();
        try {
            table.putItem(item);
        } catch (Exception ex) {
           log.error("Error in Saving Widget To DB " + ex.getMessage());
           ex.printStackTrace();
        }
    }
    public AbstractWidget getSingleWidgetById(final String Id) {
        table = dynamoDB.getTable("widget");
        GetItemSpec spec = new GetItemSpec().withPrimaryKey("widgetId", Id);
        AbstractWidget obj = null;
        try {
            Item outcome = table.getItem(spec);
            String jsonString = outcome.toJSON();
            String widgetJSONString = widgetDeserializer.getWidgetJSONString(jsonString);
            String widgetType = widgetDeserializer.getWidgetType(widgetJSONString);
            obj =  widgetDeserializer.deserialize(widgetJSONString, widgetType);
        } catch (Exception ex) {
            log.error("Error in fetching Widget from DB " + ex.getMessage());
            ex.printStackTrace();
        }
        return obj;
    }

    public List<AbstractWidget> getAllWidgets() {
        List<AbstractWidget> widgetList = new ArrayList<>();
        table = dynamoDB.getTable("widget");
        ScanSpec scanSpec = new ScanSpec();
        try {
            ItemCollection<ScanOutcome> items = table.scan(scanSpec);
            for (Item item : items) {
                String jsonString = item.toJSON();
                String widgetJSONString = widgetDeserializer.getWidgetJSONString(jsonString);
                String widgetType = widgetDeserializer.getWidgetType(widgetJSONString);
                widgetList.add(widgetDeserializer.deserialize(widgetJSONString, widgetType));
            }
        } catch (Exception ex) {
            log.error("Error in fetching all Widgets From DB " + ex.getMessage());
            ex.printStackTrace();
        }
        return widgetList;
    }

    public void deleteWidgetById(final String id) {
        table = dynamoDB.getTable("widget");
        try {
            table.deleteItem(new PrimaryKey("widgetId", id));
        } catch (Exception ex) {
            log.error("Error in deleting widget From DB " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
