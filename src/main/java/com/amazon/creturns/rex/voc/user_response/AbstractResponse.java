package com.amazon.creturns.rex.voc.user_response;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Getter
@Setter
public abstract class AbstractResponse {
    private String responseId;
    private String userId;
    private String createTime;
    public AbstractResponse(){
        if (this.responseId==null) this.responseId = UUID.randomUUID().toString().replaceAll("-","");
        if (this.createTime==null) this.createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }
    @JsonIgnore
    public abstract Item createDynamoDbItem();
}
