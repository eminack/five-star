package com.amazon.creturns.rex.voc.repositories;

import com.amazon.creturns.rex.voc.language.LanguageEntity;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * This Repository deals with operations on "language" Table
 */
@Repository
public class LanguageRepository {

    @Autowired
    private DynamoDBMapper mapper;

    /**
     * Insert LanguageEntity object into Table
     * @param languageEntity java object which is to be inserted into 'language' table
     */
    public void insertIntoDynamoDb(final LanguageEntity languageEntity) {
        mapper.save(languageEntity);
    }

    /**
     * fetch LanguageEntity object using PK 'locale' && HashKey 'string' from table
     *
     * @param locale String denoting the language code
     * @param stringId String denoting the String-ID
     * @return fetched LanguageEntity java object
     */
    public LanguageEntity findByLocaleAndStringId(final String locale, final String stringId) {
        return mapper.load(LanguageEntity.class, locale, stringId);
    }

}
