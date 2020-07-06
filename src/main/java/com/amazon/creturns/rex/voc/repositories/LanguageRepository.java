package com.amazon.creturns.rex.voc.repositories;

import com.amazon.creturns.rex.voc.language.LanguageEntity;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class LanguageRepository {
    @Autowired
    private DynamoDBMapper mapper;

    public void insertIntoDynamoDb(final LanguageEntity languageEntity) {
        mapper.save(languageEntity);
    }

    public LanguageEntity findByLocaleAndStringId(final String locale, final String s) {
        return mapper.load(LanguageEntity.class, locale, s);
    }

}
