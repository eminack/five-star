package com.amazon.creturns.rex.voc;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

@Getter
@Setter
public class AuditingInfo {
    private String userCreated,lastUpdateTime,lastServeTime,createTime;
    public AuditingInfo(){
        this.createTime =  LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        this.lastUpdateTime = "";
        this.lastServeTime = "";
    }
    @JsonIgnore
    public HashMap<String,String> getInfoAsMap(){
        HashMap<String,String> hashmap = new HashMap<>();
        hashmap.put("userCreated",this.userCreated);
        hashmap.put("createTime",this.createTime);
        hashmap.put("lastServeTime",this.lastServeTime);
        hashmap.put("lastUpdateTime",this.lastUpdateTime);
        return hashmap;
    }
    @JsonIgnore
    public void updateLastUpdateTime(){
        this.lastUpdateTime =  LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }

    @JsonIgnore
    public void updateLastServeTime() {
        this.lastServeTime =  LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }
}
