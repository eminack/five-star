package com.amazon.creturns.rex.voc.user_response;

import com.amazon.creturns.rex.voc.DynamoDbMethods;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Getter
@Setter
public abstract class AbstractResponse implements DynamoDbMethods {
    private String responseId;
    private String userId;
    private String createTime;

    protected AbstractResponse() {
        if (this.responseId == null) {
            this.responseId = UUID.randomUUID().toString().replaceAll("-", "");
        }
        if (this.createTime == null) {
            this.createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        }
    }

}
