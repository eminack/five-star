package com.amazon.creturns.rex.voc.controllers;

import com.amazon.creturns.rex.voc.services.WidgetService;
import com.amazon.creturns.rex.voc.widget.AbstractWidget;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Log4j2
@Controller
@CrossOrigin
public class WidgetController {

    @Autowired
    private WidgetService widgetService;

    @RequestMapping(value = "/widget", method = RequestMethod.POST)
    @ResponseBody
    public AbstractWidget createWidget(HttpServletRequest httpServletRequest) {
        AbstractWidget obj =  widgetService.createNewWidget(httpServletRequest);
        return widgetService.save(obj);
    }

    @RequestMapping(value = "/widget/{id}", method = RequestMethod.GET)
    public String getWidgetById(@PathVariable("id") final String id, Model model) {
        model.addAttribute("widget", widgetService.getWidgetById(id));
        log.info("Rendering view-single-widget page");
        return "view-single-widget";
    }

    @RequestMapping(value = "/widget/{id}/delete", method = RequestMethod.POST)
    @ResponseBody
    public String deleteWidgetById(@PathVariable final String id) {
        widgetService.deleteWidgetById(id);
        return "success";
    }

    @RequestMapping(value = "/widget/{id}/edit", method = RequestMethod.POST)
    @ResponseBody
    public AbstractWidget updateWidget(@PathVariable final String id, HttpServletRequest httpServletRequest) {
        return widgetService.updateWidget(id, httpServletRequest);
    }

}
