package com.amazon.creturns.rex.voc;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

@Getter
@Setter
public class AuditingInfo {
    /**
     * Id of user
     */
    private String userCreated;

    /**
     * last Time when the widget/form was updated
     */
    private String lastUpdateTime;

    /**
     * last time when the widget/form was served
     */
    private String lastServeTime;

    /**
     * creation time of the widget/form
     */
    private String createTime;


    public AuditingInfo() {
        this.createTime =  LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        this.lastUpdateTime = "";
        this.lastServeTime = "";
    }

    /**
     * returns the Audit Info object as a HashMap <String,String>
     * @return A hashmap containing the AuditInfo fields and values
     */
    @JsonIgnore
    public HashMap<String, String> getInfoAsMap() {
        HashMap<String, String> hashmap = new HashMap<>();

        hashmap.put("userCreated", this.userCreated);
        hashmap.put("createTime", this.createTime);
        hashmap.put("lastServeTime", this.lastServeTime);
        hashmap.put("lastUpdateTime", this.lastUpdateTime);

        return hashmap;
    }

    /**
     * set the lastUpdateTime field to current system time
     */
    @JsonIgnore
    public void updateLastUpdateTime() {
        this.lastUpdateTime =  LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }

    /**
     * set the lastServeTime field to current system time
     */
    @JsonIgnore
    public void updateLastServeTime() {
        this.lastServeTime =  LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }
}
