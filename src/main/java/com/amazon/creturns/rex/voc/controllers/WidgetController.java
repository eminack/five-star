package com.amazon.creturns.rex.voc.controllers;

import com.amazon.creturns.rex.voc.HttpServletRequestProcessor;
import com.amazon.creturns.rex.voc.services.WidgetService;
import com.amazon.creturns.rex.voc.widget.AbstractWidget;
import com.amazon.creturns.rex.voc.widget.WidgetDeserializer;

import com.amazon.creturns.rex.voc.widget.WidgetTypeFactory;

import com.amazon.creturns.rex.voc.widget.fivestar.FiveStar;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@Log4j2
@Controller
@CrossOrigin
public class WidgetController {

    @Autowired
    private WidgetService widgetService;

    @RequestMapping(value = "/widget",method = RequestMethod.POST)
    @ResponseBody
    public AbstractWidget createWidget(HttpServletRequest httpServletRequest){
        AbstractWidget obj =  widgetService.createNewWidget(httpServletRequest);
        return widgetService.save(obj);
    }

    @RequestMapping(value = "/widget/{id}",method = RequestMethod.GET)
    public String getWidgetById(@PathVariable("id") String id, Model model){
        model.addAttribute("widget",widgetService.getWidgetById(id));
        log.info("Rendering view-single-widget page");
        return "view-single-widget";
    }

    @RequestMapping(value = "/widget/{id}/delete",method = RequestMethod.POST)
    @ResponseBody
    public String deleteWidgetById(@PathVariable String id){
        widgetService.deleteWidgetById(id);
        return "success";
    }

    @RequestMapping(value = "/widget/{id}/edit",method = RequestMethod.POST)
    @ResponseBody
    public AbstractWidget UpdateWidget(@PathVariable String id,HttpServletRequest httpServletRequest){
        log.info("Updating widget By Id");
        return widgetService.updateWidget(id,httpServletRequest);
    }

}
