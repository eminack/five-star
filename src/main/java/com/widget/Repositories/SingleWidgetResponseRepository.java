package com.widget.Repositories;

import com.widget.Entities.SingleWidgetResponse;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SingleWidgetResponseRepository extends MongoRepository<SingleWidgetResponse,String> {
}
