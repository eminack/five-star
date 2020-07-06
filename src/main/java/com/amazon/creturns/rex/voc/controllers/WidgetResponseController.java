package com.amazon.creturns.rex.voc.controllers;

import com.amazon.creturns.rex.voc.services.WidgetResponseService;
import com.amazon.creturns.rex.voc.user_response.widget.AbstractWidgetResponse;
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
public class WidgetResponseController {

    @Autowired
    private WidgetResponseService widgetResponseService;

    @RequestMapping(value = "/widgetResponse", method = RequestMethod.POST)
    @ResponseBody
    public AbstractWidgetResponse saveResponse(HttpServletRequest httpServletRequest) {
        return widgetResponseService.saveResponse(httpServletRequest);
    }

    @RequestMapping(value = "/allWidgetResponse", method = RequestMethod.GET)
    @ResponseBody
    public List<AbstractWidgetResponse> getAllWidgetResponses() {
        return widgetResponseService.getAllResponses();
    }

    /* this is just for testing purposes to clear the widget Response table
        , will be removed in the final versions*/
    @RequestMapping(value = "/clearWidgetResponseTable", method = RequestMethod.GET)
    @ResponseBody
    public String clearAllResponse() {
        widgetResponseService.clearAllResponse();
        return "success";
    }
}
