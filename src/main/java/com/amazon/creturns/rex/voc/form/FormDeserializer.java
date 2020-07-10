package com.amazon.creturns.rex.voc.form;

import com.amazon.creturns.rex.voc.AuditingInfo;
import com.amazon.creturns.rex.voc.widget.AbstractWidget;
import com.amazon.creturns.rex.voc.widget.WidgetDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * This class Deserialized JSON Strings into java object of Form class
 */
@Log4j2
@Component
public class FormDeserializer {
    @Autowired
    private  WidgetDeserializer widgetDeserializer;

    private static final ObjectMapper mapper = new ObjectMapper();

    FormDeserializer() {
    }

    /**
     * deserialize the JSON String of form Item into java object of Form class
     * @param jsonString the JSON String to be deserialized
     * @return the deserialized Form object
     */
    public Form deserialize(final String jsonString) {
        try {
            final JSONObject jsonObject = new JSONObject(jsonString);

            final String formId = jsonObject.getString("formId");
            final String formName = jsonObject.getString("formName");
            final String formStatus = jsonObject.getString("formStatus");
            final int formVersion = jsonObject.getInt("formVersion");
            final AuditingInfo auditingInfo = mapper.readValue(jsonObject.getJSONObject("metaData").toString(), AuditingInfo.class);

            //deserialize widgets array
            final List<AbstractWidget> widgets = new ArrayList<>();

            final JSONArray widgetsJSONArray = jsonObject.getJSONArray("widgets");
            for (int i = 0; i < widgetsJSONArray.length(); i++) {

                final String element = widgetsJSONArray.getString(i);
                final String type = widgetDeserializer.getValueForKeyInJSONString("widgetType", element);

                widgets.add(widgetDeserializer.deserialize(element, type));
            }

            //creating the form
            final Form form = new Form(formName);
            form.setFormId(formId);
            form.setFormStatus(formStatus);
            form.setFormVersion(formVersion);
            form.setMetaData(auditingInfo);
            form.setWidgets(widgets);

            return form;

        } catch (Exception ex) {
            log.error("Error in Deserializing Form JSON String", ex);
        }
        return null;
    }
}
