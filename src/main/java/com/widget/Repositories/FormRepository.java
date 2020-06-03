package com.widget.Repositories;

import com.widget.Entities.Form;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FormRepository extends MongoRepository<Form,String> {
}
