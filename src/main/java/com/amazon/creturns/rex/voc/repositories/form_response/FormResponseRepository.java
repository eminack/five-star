package com.amazon.creturns.rex.voc.repositories.form_response;

import com.amazon.creturns.rex.voc.user_response.form.FormResponse;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Log4j2
@Component
public class FormResponseRepository {
    @Autowired
    private DynamoDB dynamoDB;

    private Table table;

    public void save(FormResponse formResponse) {
        table = dynamoDB.getTable("formResponse");
        Item item = formResponse.createDynamoDbItem();
        try{
            PutItemOutcome outcome = table.putItem(item);

        }catch (Exception ex){
            log.error("Error in saving FormResponse to DB " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
