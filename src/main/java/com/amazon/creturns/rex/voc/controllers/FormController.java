package com.amazon.creturns.rex.voc.controllers;

import com.amazon.creturns.rex.voc.HttpServletRequestProcessor;
import com.amazon.creturns.rex.voc.form.Form;
import com.amazon.creturns.rex.voc.services.FormService;
import com.amazon.creturns.rex.voc.services.WidgetService;
import com.amazon.creturns.rex.voc.widget.AbstractWidget;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
@Log4j2
@Controller
@CrossOrigin
public class FormController {
    @Autowired
    private FormService formService;
    @Autowired
    private WidgetService widgetService;

    @RequestMapping(value = "/form",method = RequestMethod.POST)
    @ResponseBody
    public Form createNewForm(@RequestBody HashMap<String,String> hmap)
    {
         return formService.createNewForm(hmap);
    }

    @RequestMapping(value = "form/{id}/delete",method = RequestMethod.POST)
    @ResponseBody
    public String deleteFormById(@PathVariable("id") String formId){
        formService.deleteFormById(formId);
        return "success";
    }

    @RequestMapping(value = "form/{id}/edit",method = RequestMethod.GET)
    public String editFormPage(@PathVariable String id, Model model){
        model.addAttribute("form",formService.getFormById(id));
        log.info("Rendering Edit-form page");
        return"edit-form";
    }

    @RequestMapping(value = "form/{id}",method = RequestMethod.GET)
    public String viewForm(@PathVariable("id") String id,Model model){
        model.addAttribute("form",formService.getFormById(id));
        model.addAttribute("preview","no");
        log.info("Rendering view Form Page");
        return "view-form";
    }
    @RequestMapping(value = "form/{id}/publish",method = RequestMethod.POST)
    @ResponseBody
    public String publishForm(@PathVariable("id") String id){
        formService.publishForm(id);
        return "Success";
    }
    @RequestMapping(value = "form/{id}/preview",method = RequestMethod.GET)
    public String previewForm(@PathVariable("id") String id,Model model){
        model.addAttribute("form",formService.getFormById(id));
        model.addAttribute("preview","yes");
        log.info("Rendering view-form page in preview mode");
        return "view-form";
    }

    @RequestMapping(value = "/form/{id}/add-widget",method = RequestMethod.POST)
    @ResponseBody
    public Form addWidgetToForm(@PathVariable ("id") String formId, HttpServletRequest httpServletRequest){
        AbstractWidget obj = widgetService.createNewWidget(httpServletRequest);
        return formService.addWidget(formId,obj);
    }

    @RequestMapping(value = "form/{formId}/widget/{widgetId}/delete",method = RequestMethod.POST)
    @ResponseBody
    public Form deleteWidgetInFormById(@PathVariable("formId") String formId,@PathVariable("widgetId") String widgetId){

        return formService.deleteWidgetById(formId,widgetId);
    }

    @RequestMapping(value = "form/{formId}/widget/{widgetId}/edit",method = RequestMethod.POST)
    @ResponseBody
    public Form UpdateWidgetInFormById(@PathVariable("formId") String formId,@PathVariable("widgetId") String widgetId,
                                       HttpServletRequest httpServletRequest){
        AbstractWidget obj = widgetService.createNewWidget(httpServletRequest);
        return formService.updateWidget(formId,obj);
    }
    @RequestMapping(value = "form/{formId}/widget/{widgetId}",method = RequestMethod.GET)
    public String getWidgetById(@PathVariable ("formId") String formId,@PathVariable("widgetId") String widgetId,Model model){

        model.addAttribute("widget",formService.getWidgetById(formId,widgetId));
        log.info("Rendering view-form-widget page");
        return "view-form-widget";
    }


}
