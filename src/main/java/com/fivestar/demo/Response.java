package com.fivestar.demo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.annotation.processing.Generated;

@Data
@Getter
@Setter
@NoArgsConstructor
@Document(collection = "Responses")
public class Response {
    @MongoId
    private String Id;

    private String userId;
    private String response;

}
