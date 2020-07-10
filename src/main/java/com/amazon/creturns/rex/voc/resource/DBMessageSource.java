package com.amazon.creturns.rex.voc.resource;

import com.amazon.creturns.rex.voc.language.LanguageEntity;
import com.amazon.creturns.rex.voc.repositories.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Locale;

/**
 * This is the resource class which has methods to fetch text corresponding to a combination of locale & String-ID
 */
@Component("messageSource")
public class DBMessageSource extends AbstractMessageSource {

    @Autowired
    private LanguageRepository languageRepository;

    private static final String DEFAULT_LOCALE_CODE = "en_US";

    /**
     * @param stringId text for this String-ID needs to be found
     * @param locale Locale object containing the language param sent from browser
     * @return a MessageFormat object containing the text corresponding to passed locale & String-ID
     */
    @Override
    protected MessageFormat resolveCode(final String stringId, final Locale locale) {

        LanguageEntity message = languageRepository.findByLocaleAndStringId(locale.getLanguage(), stringId);

        /* if text for locale is not available in DB, then use the DEFAULT locale*/
        if (message == null) {
            message = languageRepository.findByLocaleAndStringId(DEFAULT_LOCALE_CODE, stringId);
        }

        return new MessageFormat(message.getText(), locale);
    }
}
