package com.amazon.creturns.rex.voc;

import com.amazonaws.services.dynamodbv2.document.Item;

public interface DynamoDbMethods {

    /**
     * @returns a DynamoDB item created from the java object
     */
    Item createDynamoDbItem();
}
