package com.widget.Entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Getter
@Setter
@NoArgsConstructor
@Document(collection = "Forms")
public class Form {
    @MongoId
    private String formId;
    private String name;
    private String status;
    private int version;
    private String createTime,lastUpdateTime,lastServeTime;
    private String userCreated;
    private ArrayList<Widget> widgets;

    public void setWidgets(ArrayList<Widget> widgets) {
        this.widgets = widgets;
    }

    public Form(String name) {
        this.formId = UUID.randomUUID().toString().replaceAll("-","");
        this.name = name;
        this.status = "draft";
        this.version= 1;
        this.createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        this.lastUpdateTime = createTime;
        this.lastServeTime = "";
        this.userCreated="";
        widgets = new ArrayList<>();
    }

    public String getFormId() {
        return formId;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public int getVersion() {
        return version;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public String getLastServeTime() {
        return lastServeTime;
    }

    public String getUserCreated() {
        return userCreated;
    }

    public ArrayList<Widget> getWidgets() {
        return widgets;
    }
    public void addWidget(Widget widget){
        widgets.add(widget);
    }
}
