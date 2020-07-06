package com.amazon.creturns.rex.voc.services;

import com.amazon.creturns.rex.voc.HttpServletRequestProcessor;
import com.amazon.creturns.rex.voc.repositories.FormResponseRepository;
import com.amazon.creturns.rex.voc.user_response.form.FormResponse;
import com.amazon.creturns.rex.voc.user_response.form.FormResponseDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class FormResponseService {

    @Autowired
    private FormResponseDeserializer formResponseDeserializer;
    @Autowired
    private FormResponseRepository formResponseRepository;

    public FormResponse save(HttpServletRequest httpServletRequest) {
        String jsonBody = HttpServletRequestProcessor.convertRequestBodyToJsonString(httpServletRequest);
        FormResponse formResponse = formResponseDeserializer.deserializeJSONString(jsonBody);
        formResponseRepository.save(formResponse);
        return formResponse;
    }

    public List<FormResponse> getAllResponses() {
        return formResponseRepository.getAllResponses();
    }

    /* this is just for testing purposes , to clear all entries in widgetResponse table
     * will be removed in final version*/
    public void clearAllResponses() {
        formResponseRepository.clearAllResponses();
    }


}
