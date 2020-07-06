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

@Controller
@CrossOrigin
public class FormResponseController {
    @Autowired
    private FormResponseService formResponseService;

    @RequestMapping(value = "/formResponse", method = RequestMethod.POST)
    @ResponseBody
    public FormResponse saveResponse(HttpServletRequest httpServletRequest) {
        return formResponseService.save(httpServletRequest);
    }

    @RequestMapping(value = "/allFormResponse", method = RequestMethod.GET)
    @ResponseBody
    public List<FormResponse> getAllFormResponses() {
        return formResponseService.getAllResponses();
    }


    /* this is just for testing purposes to clear the widget Response table
       , will be removed in the final versions*/
    @RequestMapping(value = "/clearFormResponseTable", method = RequestMethod.GET)
    @ResponseBody
    public String clearFormResponseTable() {
        formResponseService.clearAllResponses();
        return "success";
    }
}
