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
    /**
     * responseId is unique for every response.
     */
    private String responseId;

    /**
     * user who submitted the response.
     */
    private String userId;

    /**
     * time at which the response is created.
     */
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
