package com.amazon.creturns.rex.voc.services;

import com.amazon.creturns.rex.voc.HttpServletRequestProcessor;
import com.amazon.creturns.rex.voc.repositories.FormResponseRepository;
import com.amazon.creturns.rex.voc.user_response.form.FormResponse;
import com.amazon.creturns.rex.voc.user_response.form.FormResponseDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * This service class deals with all the methods for form responses
 */
@Service
public class FormResponseService {

    @Autowired
    private FormResponseDeserializer formResponseDeserializer;

    @Autowired
    private FormResponseRepository formResponseRepository;

    /**
     * create an object of FormResponse from httpServletRequest , save it to formResponse Table
     * in DB & return the created object.
     * @param httpServletRequest the servletRequest containing the JSON String of object of FormResponse
     * @return the created FormResponse object
     */
    public FormResponse save(final HttpServletRequest httpServletRequest) {
        final String jsonBody = HttpServletRequestProcessor.convertRequestBodyToJsonString(httpServletRequest);
        final FormResponse formResponse = formResponseDeserializer.deserializeJSONString(jsonBody);

        formResponseRepository.save(formResponse);
        return formResponse;
    }

    /**
     * fetch all Form responses from formResponse Table
     * @return a list of FormResponse objects
     */
    public List<FormResponse> getAllResponses() {
        return formResponseRepository.getAllResponses();
    }

    /**
     * this is just for testing purposes , to clear all entries in formResponse table, will be removed in
     * final version
     */
    public void clearAllResponses() {
        formResponseRepository.clearAllResponses();
    }


}
