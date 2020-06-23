package com.amazon.creturns.rex.voc.services;

import com.amazon.creturns.rex.voc.HttpServletRequestProcessor;
import com.amazon.creturns.rex.voc.repositories.form_response.FormResponseRepository;
import com.amazon.creturns.rex.voc.user_response.form.FormResponse;
import com.amazon.creturns.rex.voc.user_response.form.FormResponseDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class FormResponseService {
    @Autowired
    private HttpServletRequestProcessor httpServletRequestProcessor;
    @Autowired
    private FormResponseDeserializer formResponseDeserializer;
    @Autowired
    private FormResponseRepository formResponseRepository;

    public FormResponse save(HttpServletRequest httpServletRequest) {
        String jsonBody = httpServletRequestProcessor.convertRequestBodyToJsonString(httpServletRequest);
        FormResponse formResponse = formResponseDeserializer.deserializeJsonReceivedFromClient(jsonBody);
        formResponseRepository.save(formResponse);
        return formResponse;
    }
}
