package com.widget.Entities;

import java.util.HashMap;
import java.util.UUID;

public class Widget {
    private String widgetId;
    private String widgetName;
    private String widgetType;
    private int widgetVersion;
    private String templateId;
    private HashMap<String,String> data;

    public String getWidgetType() {
        return widgetType;
    }

    public void setWidgetType(String widgetType) {
        this.widgetType = widgetType;
    }

    public Widget(String widgetName, String widgetType, int widgetVersion, String templateId, HashMap<String, String> data) {
        this.widgetType = widgetType;
        this.widgetId =  UUID.randomUUID().toString().replaceAll("-","");
        this.widgetName = widgetName;
        this.widgetVersion = widgetVersion;
        this.templateId = templateId;
        this.data = data;
    }

    public String getWidgetId() {
        return widgetId;
    }

    public void setWidgetId(String id) {
        this.widgetId = id;
    }

    public String getWidgetName() {
        return widgetName;
    }

    public void setWidgetName(String widgetName) {
        this.widgetName = widgetName;
    }

    public int getWidgetVersion() {
        return widgetVersion;
    }

    public void setWidgetVersion(int widgetVersion) {
        this.widgetVersion = widgetVersion;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public HashMap<String, String> getData() {
        return data;
    }

    public void setData(HashMap<String, String> data) {
        this.data = data;
    }
}
