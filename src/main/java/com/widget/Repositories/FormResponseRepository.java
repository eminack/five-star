package com.widget.Repositories;

import com.widget.Entities.FormResponse;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FormResponseRepository extends MongoRepository<FormResponse,String> {
}
