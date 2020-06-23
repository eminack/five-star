package com.amazon.creturns.rex.voc.repositories.form;

import com.amazon.creturns.rex.voc.form.Form;
import com.amazon.creturns.rex.voc.form.FormDeserializer;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;

import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import software.amazon.ion.apps.SymtabApp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Log4j2
@Component
public class FormRepository {
    @Autowired
    private DynamoDB dynamoDB;
    @Autowired
    private FormDeserializer formDeserializer;

    private Table table;


    public void save(Form form) {
        table = dynamoDB.getTable("form");
        Item item = form.createDynamoDbItem();
        try{
            PutItemOutcome outcome = table.putItem(item);
        }catch(Exception ex){
            log.error("Error in Saving Form to DB " + ex.getMessage() );
            ex.printStackTrace();
        }
    }

    public Form getFormById(String formId) {
        table = dynamoDB.getTable("form");
        GetItemSpec spec = new GetItemSpec().withPrimaryKey("formId",formId);
        Form form=null;
        try {
            Item outcome = table.getItem(spec);
            String jsonString = outcome.toJSON();
            form = formDeserializer.deserialize(jsonString);
        }catch (Exception ex){
            log.error("Error in fetching Form from DB " + ex.getMessage());
            ex.printStackTrace();
        }
        return form;
    }

    public void deleteFormById(String formId) {
        table = dynamoDB.getTable("form");
        try{
            table.deleteItem(new PrimaryKey("formId",formId));
        }catch (Exception ex){
            log.error("Error in deleting Form from DB " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public List<Form> getAllForms() {
        ArrayList<Form> formList = new ArrayList<>();
        table = dynamoDB.getTable("form");
        ScanSpec scanSpec = new ScanSpec();
        try{
            ItemCollection<ScanOutcome> items = table.scan(scanSpec);
            Iterator<Item> iter = items.iterator();
            while(iter.hasNext()){
                Item item = iter.next();
                String jsonString = item.toJSON();
               formList.add(formDeserializer.deserialize(jsonString));
            }
        }catch (Exception ex){
           log.error("Error in fetching all forms from DB "+ex.getMessage());
           ex.printStackTrace();
        }
        return formList;
    }
}
