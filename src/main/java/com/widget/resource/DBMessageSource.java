package com.widget.resource;

import com.widget.Entities.Localization;
import com.widget.Repositories.LocalizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Locale;

@Component("messageSource")
public class DBMessageSource extends AbstractMessageSource {

    @Autowired
    private LocalizationRepository localizationRepository;
    private static final String DEFAULT_LOCALE_CODE = "en_US";
    @Override
    protected MessageFormat resolveCode(String s, Locale locale) {
        Localization message = localizationRepository.findByStringIdAndLocale(s,locale.getLanguage());
        if (message==null){
            message = localizationRepository.findByStringIdAndLocale(s,DEFAULT_LOCALE_CODE);
        }
        return new MessageFormat(message.getText(),locale);
    }
}
