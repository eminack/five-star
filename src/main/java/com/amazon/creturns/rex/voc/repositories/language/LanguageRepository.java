package com.amazon.creturns.rex.voc.repositories.language;

import com.amazon.creturns.rex.voc.language.LanguageEntity;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class LanguageRepository {
    @Autowired
    private DynamoDBMapper mapper;

    public void insertIntoDynamoDb(LanguageEntity languageEntity)
    {
        mapper.save(languageEntity);
    }

    public LanguageEntity findByLocaleAndStringId(String locale,String s){
        return mapper.load(LanguageEntity.class,locale,s);
    }

}
