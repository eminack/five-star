package com.amazon.creturns.rex.voc.controllers;

import com.amazon.creturns.rex.voc.HttpServletRequestProcessor;
import com.amazon.creturns.rex.voc.services.WidgetResponseService;
import com.amazon.creturns.rex.voc.user_response.widget.AbstractWidgetResponse;
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
public class WidgetResponseController {

    @Autowired
    private WidgetResponseService widgetResponseService;

    @RequestMapping(value = "/widgetresponse",method = RequestMethod.POST)
    @ResponseBody
    public AbstractWidgetResponse saveResponse(HttpServletRequest httpServletRequest){
        return widgetResponseService.saveResponse(httpServletRequest);
    }

    /* this is just for testing purposes to clear the widget Response table
        , will be removed in the final versions*/
    @RequestMapping(value = "/clearTable",method = RequestMethod.GET)
    @ResponseBody
    public String clearAllResponse(){
        widgetResponseService.clearAllResponse();
        return "success";
    }
}
