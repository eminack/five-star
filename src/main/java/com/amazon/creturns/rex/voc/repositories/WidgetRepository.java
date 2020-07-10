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

/**
 * This Repository class deals with all the operations performed on "widget" Table
 */
@Log4j2
@Component
public class WidgetRepository {

    @Autowired
    private WidgetDeserializer widgetDeserializer;
    @Autowired
    private DynamoDB dynamoDB;

    private Table table;

    /**
     * @param obj This java object is converted to widget item and inserted into Table
     */
    public void save(final AbstractWidget obj) {
        table = dynamoDB.getTable("widget");

        final Item item = obj.createDynamoDbItem();

        try {
            table.putItem(item);
        } catch (Exception ex) {
           log.error("Error in Saving Widget To DB ", ex);
        }
    }

    /**
     * fetch a single widget Item By Id from table & converts into java object
     * @param Id ID of Item to be fetched from table
     * @return The java object of fetched item
     */
    public AbstractWidget getSingleWidgetById(final String Id) {
        table = dynamoDB.getTable("widget");

        final GetItemSpec spec = new GetItemSpec().withPrimaryKey("widgetId", Id);

        try {
            final Item outcome = table.getItem(spec);

            final String jsonString = outcome.toJSON();

            final String widgetJSONString = widgetDeserializer.
                    getValueForKeyInJSONString("widgetJSONString", jsonString);
            final String widgetType = widgetDeserializer.
                    getValueForKeyInJSONString("widgetType", widgetJSONString);

            return widgetDeserializer.deserialize(widgetJSONString, widgetType);

        } catch (Exception ex) {
            log.error("Error in fetching Widget from DB ", ex);
        }
        return null;
    }

    /**
     * fetches All widget Item from table & deserialize them into java object
     * @return a list containing java object of every widget item in Table
     */
    public List<AbstractWidget> getAllWidgets() {
        final List<AbstractWidget> widgetList = new ArrayList<>();

        table = dynamoDB.getTable("widget");
        final ScanSpec scanSpec = new ScanSpec();

        try {
            final ItemCollection<ScanOutcome> items = table.scan(scanSpec);

            for (final Item item : items) {
                final String jsonString = item.toJSON();

                final String widgetJSONString = widgetDeserializer.
                        getValueForKeyInJSONString("widgetJSONString", jsonString);
                final String widgetType = widgetDeserializer.
                        getValueForKeyInJSONString("widgetType", widgetJSONString);

                widgetList.add(widgetDeserializer.deserialize(widgetJSONString, widgetType));
            }
        } catch (Exception ex) {
            log.error("Error in fetching all Widgets From DB ", ex);
        }

        return widgetList;
    }

    /**
     * delete widget Item in table using it's widgetId
     * @param id : ID of item to be deleted from table
     */
    public void deleteWidgetById(final String id) {
        table = dynamoDB.getTable("widget");

        try {
            table.deleteItem(new PrimaryKey("widgetId", id));
        } catch (Exception ex) {
            log.error("Error in deleting widget From DB ", ex);
        }
    }
}
