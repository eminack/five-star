package com.widget.Services;

import com.widget.Entities.SingleWidgetResponse;
import com.widget.Repositories.SingleWidgetResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SingleWidgetResponseService {
    @Autowired
    private SingleWidgetResponseRepository repository;

    public SingleWidgetResponse createNewResponse(SingleWidgetResponse response) {
        return new SingleWidgetResponse(response.getUserId(),response.getWidgetId(),response.getWidgetType(),
                response.getData());
    }

    public void saveResponse(SingleWidgetResponse singleWidgetResponse) {
        repository.save(singleWidgetResponse);
    }
}
