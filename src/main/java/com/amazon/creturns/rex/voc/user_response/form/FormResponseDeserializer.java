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

/**
 * This class has methods to deserialize JSON String into FormResponse object
 */
@Log4j2
@Component
public class FormResponseDeserializer {

    @Autowired
    private WidgetResponseDeserializer widgetResponseDeserializer;

    FormResponseDeserializer() {
    }

    /**
     * create a FormResponse object from JSON String
     * @param jsonString the JSON String
     * @return the created object
     */
    public FormResponse deserializeJSONString(final String jsonString) {
        try {
            final String responseId;
            final String userId;
            final String createTime;
            final String formId;
            final List<AbstractWidgetResponse> responses = new ArrayList<>();

            JSONObject jsonObject = new JSONObject(jsonString);

            if (jsonObject.has("responseId")) {
                responseId = jsonObject.getString("responseId");
            } else {
                responseId = UUID.randomUUID().toString().replaceAll("-", "");
            }

            userId = jsonObject.getString("userId");

            if (jsonObject.has("createTime")) {
                createTime = jsonObject.getString("createTime");
            } else {
                createTime =  LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
            }

            formId = jsonObject.getString("formId");

            /*
             The responseList key in jsonObject can have Widgets stored as either JSON Strings or JSONObject
             hence we check what type it is . If JSONObject then we convert that into JSON String
             Then we convert each of those Strings into java object of a concrete subclass of AbstractWidgetResponse
            */
            final JSONArray jsonArray = jsonObject.getJSONArray("responseList");
            for (int i = 0; i < jsonArray.length(); i++) {

                final String widgetResponseJSONString = (jsonArray.get(i) instanceof JSONObject) ?
                                  jsonArray.getJSONObject(i).toString() : jsonArray.getString(i);

                final String responseType = widgetResponseDeserializer.
                        getValueForKeyInJSONString("widgetType",widgetResponseJSONString);

                responses.add(widgetResponseDeserializer.deserialize(widgetResponseJSONString,responseType));
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
            log.error("Error in deserializing JSON to FormResponse Object " , ex);
        }
        return null;
    }
}
