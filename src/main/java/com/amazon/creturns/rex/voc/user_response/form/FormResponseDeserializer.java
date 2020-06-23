package com.amazon.creturns.rex.voc.user_response.form;

import com.amazon.creturns.rex.voc.user_response.widget.AbstractWidgetResponse;
import com.amazon.creturns.rex.voc.user_response.widget.WidgetResponseDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.UUID;
@Log4j2
@Component
public class FormResponseDeserializer {
    @Autowired
    private WidgetResponseDeserializer widgetResponseDeserializer;
    private static ObjectMapper mapper;

    FormResponseDeserializer(){
        mapper = new ObjectMapper();
    }

    //deserialize JSON String received from client to FormResponse Object
    public FormResponse deserializeJsonReceivedFromClient(String jsonString){
        log.info("Deserializing client JSON string into FormResponse Object");
        try{
            JSONObject jsonObject = new JSONObject(jsonString);
            String responseId="";
            if (jsonObject.has("responseId")){
                responseId = jsonObject.getString("responseId");
            }else{
                responseId = UUID.randomUUID().toString().replaceAll("-", "");
            }

            String userId = jsonObject.getString("userId");

            String createTime;
            if (jsonObject.has("createTime")){
                createTime = jsonObject.getString("createTime");
            }else{
                createTime =  LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
            }

            String formId = jsonObject.getString("formId");

            ArrayList<AbstractWidgetResponse> responses = new ArrayList<>();
            JSONArray jsonArray = jsonObject.getJSONArray("responseList");
            for(int i=0;i<jsonArray.length();i++){
                String element = jsonArray.getJSONObject(i).toString();
                responses.add(widgetResponseDeserializer.deserialize(element,widgetResponseDeserializer.getWidgetType(element)));
            }
            //create the FormResponse Object
            FormResponse formResponse = new FormResponse();
            formResponse.setResponseId(responseId);
            formResponse.setUserId(userId);
            formResponse.setCreateTime(createTime);
            formResponse.setFormId(formId);
            formResponse.setResponseList(responses);
            return formResponse;
        }catch (Exception ex){
            log.error("Error in deserializing Client JSON to FormResponse Object "+ ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }

    //deserialize JSON String received from DB
    public FormResponse deserializeJsonReceivedFromDb(String jsonString){
        log.info("Deserialize JSON string from DB to FormResponse Object");
        try{
            JSONObject jsonObject = new JSONObject();
            String responseId = jsonObject.getString("responseId");
            String userId = jsonObject.getString("userId");
            String createTime = jsonObject.getString("createTime");
            String formId = jsonObject.getString("formId");
            ArrayList<AbstractWidgetResponse> responses = new ArrayList<>();
            JSONArray jsonArray = jsonObject.getJSONArray("responseList");
            for(int i=0;i<jsonArray.length();i++){
                String element = jsonArray.getString(i);
                responses.add(widgetResponseDeserializer.deserialize(element,widgetResponseDeserializer.getWidgetType(element)));
            }
            FormResponse formResponse = new FormResponse();
            formResponse.setResponseId(responseId);
            formResponse.setUserId(userId);
            formResponse.setCreateTime(createTime);
            formResponse.setFormId(formId);
            formResponse.setResponseList(responses);
            return formResponse;
        }catch (Exception ex){
           log.error("Error in deserializing JSON string received from DB to FormResponse Object "+ ex.getMessage());
           ex.printStackTrace();
        }
        return null;
    }


}
