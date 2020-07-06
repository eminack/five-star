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

@Log4j2
@Component
public class FormDeserializer {
    @Autowired
    private  WidgetDeserializer widgetDeserializer;
    private static final ObjectMapper mapper = new ObjectMapper();

    FormDeserializer() {
    }

    //deserialize the JsonString fetched from DB
    public Form deserialize(final String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            String formId = jsonObject.getString("formId");
            String formName = jsonObject.getString("formName");
            String formStatus = jsonObject.getString("formStatus");
            int formVersion = jsonObject.getInt("formVersion");
            AuditingInfo auditingInfo = mapper.readValue(jsonObject.getJSONObject("metaData").toString(), AuditingInfo.class);

            //deserialize widgets array
            List<AbstractWidget> widgets = new ArrayList<>();
            JSONArray widgetsJSONArray = jsonObject.getJSONArray("widgets");
            for (int i = 0; i < widgetsJSONArray.length(); i++) {
                String element = widgetsJSONArray.getString(i);
                widgets.add(widgetDeserializer.deserialize(element, widgetDeserializer.getWidgetType(element)));
            }

            //creating the form
            Form form = new Form(formName);
            form.setFormId(formId);
            form.setFormStatus(formStatus);
            form.setFormVersion(formVersion);
            form.setMetaData(auditingInfo);
            form.setWidgets(widgets);
            return form;
        } catch (Exception ex) {
            log.error("Error in Deserializing Form JSON String" + ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }
}
