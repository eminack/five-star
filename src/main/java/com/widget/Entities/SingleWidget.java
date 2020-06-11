package com.widget.Entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.UUID;

@Data
@Getter
@Setter
@NoArgsConstructor
@Document(collection = "Widgets")
public class SingleWidget {
    @MongoId
    private String widgetId;
    private String widgetName;
    private String widgetType;
    private int widgetVersion;
    private String templateId;
    private String createTime,lastUpdateTime,lastServeTime;
    private String userCreated;
    private HashMap<String,String> data;

    public SingleWidget(String widgetName, String widgetType, int widgetVersion, String templateId, String userCreated, HashMap<String, String> data) {
        this.widgetId = UUID.randomUUID().toString().replaceAll("-","");
        this.widgetName = widgetName;
        this.widgetType = widgetType;
        this.widgetVersion = widgetVersion;
        this.templateId = templateId;
        this.userCreated = userCreated;
        this.data = data;
        this.createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        this.lastUpdateTime = this.createTime;
        this.lastServeTime = "";
    }

    public String getWidgetId() {
        return widgetId;
    }

    public void setWidgetId(String widgetId) {
        this.widgetId = widgetId;
    }

    public String getWidgetName() {
        return widgetName;
    }

    public void setWidgetName(String widgetName) {
        this.widgetName = widgetName;
    }

    public String getWidgetType() {
        return widgetType;
    }

    public void setWidgetType(String widgetType) {
        this.widgetType = widgetType;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getLastServeTime() {
        return lastServeTime;
    }

    public void setLastServeTime(String lastServeTime) {
        this.lastServeTime = lastServeTime;
    }

    public String getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(String userCreated) {
        this.userCreated = userCreated;
    }

    public HashMap<String, String> getData() {
        return data;
    }

    public void setData(HashMap<String, String> data) {
        this.data = data;
    }

    public void updateDetails(SingleWidget oldWidget) {
        this.userCreated = oldWidget.getUserCreated();
        this.lastUpdateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        this.createTime = oldWidget.getCreateTime();
        this.lastServeTime = oldWidget.getLastServeTime();
        this.widgetVersion = oldWidget.getWidgetVersion()+1;
    }
}
