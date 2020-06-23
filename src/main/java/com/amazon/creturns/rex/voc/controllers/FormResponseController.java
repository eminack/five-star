package com.amazon.creturns.rex.voc.controllers;

import com.amazon.creturns.rex.voc.services.FormResponseService;
import com.amazon.creturns.rex.voc.user_response.form.FormResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
@Controller
@CrossOrigin
public class FormResponseController {
    @Autowired
    private FormResponseService formResponseService;

    @RequestMapping(value = "/formresponse",method = RequestMethod.POST)
    @ResponseBody
    public FormResponse saveResponse(HttpServletRequest httpServletRequest){
        return formResponseService.save(httpServletRequest);
    }
}
