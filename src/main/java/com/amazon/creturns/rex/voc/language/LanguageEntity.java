package com.amazon.creturns.rex.voc.language;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Getter;
import lombok.Setter;

/**
 * This class is used for internationalization purposes
 */
@Getter
@Setter
@DynamoDBTable(tableName = "language")
public class LanguageEntity {

    /**
     * locale : denotes the language code
     */
    @DynamoDBHashKey
    private String locale;

    /**
     * stringId : denotes the Id of string for which we need to fetch the text
     */
    @DynamoDBRangeKey
    private String stringId;

    /**
     * text : contains the text to be displayed for a particular combination of
     * locale & stringId
     */
    @DynamoDBAttribute
    private String text;

    /**
     * empty constructor
     */
    public LanguageEntity() {
    }
}
