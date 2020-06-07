package com.widget.Repositories;

import com.widget.Entities.SingleWidget;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WidgetRepository extends MongoRepository<SingleWidget,String> {
}
