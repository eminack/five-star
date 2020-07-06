package com.amazon.creturns.rex.voc.language;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@DynamoDBTable(tableName = "language")
public class LanguageEntity {
    @DynamoDBHashKey
    private String locale;
    @DynamoDBRangeKey
    private String stringId;
    @DynamoDBAttribute
    private String text;

    public LanguageEntity() {
    }
}
