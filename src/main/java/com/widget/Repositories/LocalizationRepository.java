package com.widget.Repositories;

import com.widget.Entities.Localization;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;

public interface LocalizationRepository extends MongoRepository<Localization,String> {
    Localization findByStringIdAndLocale(String stringId,String locale);
}
