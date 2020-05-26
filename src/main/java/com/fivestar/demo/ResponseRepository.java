package com.fivestar.demo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface ResponseRepository extends MongoRepository<Response,String> {
    Response findByUserId(String id);
}
