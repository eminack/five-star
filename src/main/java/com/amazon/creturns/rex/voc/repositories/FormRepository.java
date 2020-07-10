package com.amazon.creturns.rex.voc.repositories;

import com.amazon.creturns.rex.voc.form.Form;
import com.amazon.creturns.rex.voc.form.FormDeserializer;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * This Repository class deals with all operations on "form" table
 */
@Log4j2
@Component
public class FormRepository {
    @Autowired
    private DynamoDB dynamoDB;
    @Autowired
    private FormDeserializer formDeserializer;

    private Table table;

    /**
     * @param form This java object is converted to form Item & that is inserted into table
     */
    public void save(final Form form) {
        table = dynamoDB.getTable("form");

        final Item item = form.createDynamoDbItem();

        try {
            table.putItem(item);
        } catch (Exception ex) {
            log.error("Error in Saving Form to DB ", ex);
        }
    }

    /**
     * fetch form Item from table by PK 'formId' then deserialize it into java object & return it
     * @param formId PK for fetching form Item from table
     * @return deserialized java object
     */
    public Form getFormById(final String formId) {
        table = dynamoDB.getTable("form");

        final GetItemSpec spec = new GetItemSpec().withPrimaryKey("formId", formId);

        try {
            final Item outcome = table.getItem(spec);
            final String jsonString = outcome.toJSON();

            return formDeserializer.deserialize(jsonString);
        } catch (Exception ex) {
            log.error("Error in fetching Form from DB ", ex);
        }
        return null;
    }

    /**
     * delete form Item from table using PK formId
     * @param formId PK for deleting form Item from table
     */
    public void deleteFormById(final String formId) {
        table = dynamoDB.getTable("form");

        try {
            table.deleteItem(new PrimaryKey("formId", formId));
        } catch (Exception ex) {
            log.error("Error in deleting Form from DB ", ex);
        }
    }

    /**
     * fetch all form Item from table & deserialize them into Form object & return a list of them
     * @return a list containing objects of Form class
     */
    public List<Form> getAllForms() {
        final List<Form> formList = new ArrayList<>();

        table = dynamoDB.getTable("form");
        final ScanSpec scanSpec = new ScanSpec();

        try {
            final ItemCollection<ScanOutcome> items = table.scan(scanSpec);

            for (final Item item : items) {
                final String jsonString = item.toJSON();
                formList.add(formDeserializer.deserialize(jsonString));
            }

        } catch (Exception ex) {
           log.error("Error in fetching all forms from DB ", ex);
        }
        return formList;
    }
}
