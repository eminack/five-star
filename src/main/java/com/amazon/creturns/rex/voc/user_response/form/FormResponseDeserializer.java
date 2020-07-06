package com.amazon.creturns.rex.voc.user_response.form;

import com.amazon.creturns.rex.voc.user_response.widget.AbstractWidgetResponse;
import com.amazon.creturns.rex.voc.user_response.widget.WidgetResponseDeserializer;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Log4j2
@Component
public class FormResponseDeserializer {
    @Autowired
    private WidgetResponseDeserializer widgetResponseDeserializer;

    FormResponseDeserializer() {
    }

    //deserialize JSON String to FormResponse Object
    public FormResponse deserializeJSONString(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            String responseId;

            if (jsonObject.has("responseId")) {
                responseId = jsonObject.getString("responseId");
            } else {
                responseId = UUID.randomUUID().toString().replaceAll("-", "");
            }

            String userId = jsonObject.getString("userId");

            String createTime;
            if (jsonObject.has("createTime")) {
                createTime = jsonObject.getString("createTime");
            } else {
                createTime =  LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
            }

            String formId = jsonObject.getString("formId");

            List<AbstractWidgetResponse> responses = new ArrayList<>();
            JSONArray jsonArray = jsonObject.getJSONArray("responseList");
            for (int i = 0; i < jsonArray.length(); i++) {
                String element = (jsonArray.get(i) instanceof JSONObject) ?
                                  jsonArray.getJSONObject(i).toString() : jsonArray.getString(i);

                responses.add(widgetResponseDeserializer.deserialize(element, widgetResponseDeserializer.getWidgetType(element)));
            }

            //create the FormResponse Object
            FormResponse formResponse = new FormResponse();
            formResponse.setResponseId(responseId);
            formResponse.setUserId(userId);
            formResponse.setCreateTime(createTime);
            formResponse.setFormId(formId);
            formResponse.setResponseList(responses);

            return formResponse;
        } catch (Exception ex) {
            log.error("Error in deserializing JSON to FormResponse Object " + ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }
}
