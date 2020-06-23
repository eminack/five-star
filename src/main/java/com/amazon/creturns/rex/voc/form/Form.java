package com.amazon.creturns.rex.voc.form;

import com.amazon.creturns.rex.voc.AuditingInfo;
import com.amazon.creturns.rex.voc.widget.AbstractWidget;
import com.amazon.creturns.rex.voc.widget.WidgetDeserializer;
import com.amazon.creturns.rex.voc.widget.WidgetSerializer;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
public class Form{
    private String formId;
    private String formName;
    private String formStatus;
    private int formVersion;
    private AuditingInfo metaData;
    private ArrayList<AbstractWidget> widgets;

    public Form(String name) {
        this.formName = name;
        this.formId = UUID.randomUUID().toString().replaceAll("-", "");
        this.formStatus = "DRAFT";
        this.formVersion = 1;
        this.metaData = new AuditingInfo();
        this.widgets = new ArrayList<>();
    }

    @JsonIgnore
    public Item createDynamoDbItem() {
        Map<String,String> hashmap = this.metaData.getInfoAsMap();
        //widgets are stored as json string in dynamoDb
        ArrayList<String> widgetList = new ArrayList<>();
        for(AbstractWidget ab:this.widgets) widgetList.add(WidgetSerializer.serialize(ab));
        Item item = new Item()
                .withPrimaryKey("formId",this.formId)
                .withString("formName",this.formName)
                 .withString("formStatus",this.formStatus)
                .withInt("formVersion",this.formVersion)
                .withMap("metaData",hashmap)
                .withList("widgets",widgetList);
        return item;
    }
}
