package com.amazon.creturns.rex.voc.resource;

import com.amazon.creturns.rex.voc.language.LanguageEntity;
import com.amazon.creturns.rex.voc.repositories.language.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Locale;

@Component("messageSource")
public class DBMessageSource extends AbstractMessageSource {

    @Autowired
    private LanguageRepository languageRepository;

    private static final String DEFAULT_LOCALE_CODE = "en_US";

    @Override
    protected MessageFormat resolveCode(String s, Locale locale) {
        LanguageEntity message =languageRepository.findByLocaleAndStringId(locale.getLanguage(),s);
        if (message==null){
            message = languageRepository.findByLocaleAndStringId(DEFAULT_LOCALE_CODE,s);
        }
        return new MessageFormat(message.getText(),locale);
    }
}
