package com.amazon.creturns.rex.voc.controllers;

import com.amazon.creturns.rex.voc.services.FormResponseService;
import com.amazon.creturns.rex.voc.user_response.form.FormResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * This class deals with all incoming  request for Form responses
 */
@Controller
@CrossOrigin
public class FormResponseController {

    @Autowired
    private FormResponseService formResponseService;

    /**
     * This method handles the incoming form response & saves it to 'formResponse' table
     * @param httpServletRequest the incoming request
     * @return a FormResponse object for saved response
     */
    @RequestMapping(value = "/formResponse", method = RequestMethod.POST)
    @ResponseBody
    public FormResponse saveResponse(final HttpServletRequest httpServletRequest) {
        return formResponseService.save(httpServletRequest);
    }

    /**
     * method for fetching all responses from 'formResponse' table
     * @return a List containing FormResponse object of all requests
     */
    @RequestMapping(value = "/allFormResponse", method = RequestMethod.GET)
    @ResponseBody
    public List<FormResponse> getAllFormResponses() {
        return formResponseService.getAllResponses();
    }


    /**
     *  this is just for testing purposes to clear the 'formResponse' table , will be removed in the final versions
     */
    @RequestMapping(value = "/clearFormResponseTable", method = RequestMethod.GET)
    @ResponseBody
    public String clearFormResponseTable() {
        formResponseService.clearAllResponses();
        return "success";
    }
}
