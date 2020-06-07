package com.widget.Services;

import com.widget.Entities.FormResponse;
import com.widget.Repositories.FormResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormResponseService {
    @Autowired
    private FormResponseRepository repository;


    public FormResponse createNewResponse(FormResponse response) {
        return new FormResponse(response.getUserId(),response.getFormId(),response.getData());
    }

    public void saveResponse(FormResponse response) {
        repository.save(response);
    }
}
