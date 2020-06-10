package com.widget.Controllers;

import com.widget.Entities.Form;
import com.widget.Entities.Widget;
import com.widget.Services.FormService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@CrossOrigin
public class FormController {
    @Autowired
    private FormService formService;

    public FormController(FormService formService) {
        this.formService = formService;
    }


    @RequestMapping(value = "/form",method = RequestMethod.POST)
    @ResponseBody
    public Form createNewForm(Model model, @RequestParam String name){
        Form form = formService.createNewForm(name);
        formService.saveForm(form);
        return form;
    }

    @RequestMapping(value = "/form/{formId}/edit",method = RequestMethod.GET)
    public String displayForm(Model model,@PathVariable("formId") String id){
        model.addAttribute("form",formService.getFormById(id));
        return "edit-form";
    }
    @RequestMapping(value = "/form/{id}/delete",method = RequestMethod.GET)
    public String deleteFormById(@PathVariable("id") String id, Model model){
        formService.deleteFormById(id);
        model.addAttribute("forms",formService.getAllForms());
        return "redirect:/";
    }

    @RequestMapping(value = "/form/{id}/add-widget",method = RequestMethod.POST)
    @ResponseBody
    public Form addWidgetToForm(@PathVariable("id") String id,@RequestBody Widget widget){
        formService.addWidget(id,widget);
        return formService.getFormById(id);
    }
    @RequestMapping(value = "/form/{formId}/widget/{widgetId}/delete",method = RequestMethod.POST)
    @ResponseBody
    public Form deleteWidgetFromForm(@PathVariable("formId") String formId,@PathVariable("widgetId") String widgetId){
        formService.deleteWidgetFromFormById(formId,widgetId);
        return formService.getFormById(formId);
    }
    @RequestMapping(value = "/form/{formId}/widget/{widgetId}/edit",method = RequestMethod.POST)
    @ResponseBody
    public Form editWidgetFromForm(@PathVariable("formId") String formId,@PathVariable("widgetId") String widgetId,@RequestBody Widget widget){
        formService.replaceWidget(formId,widgetId,widget);
        return formService.getFormById(formId);
    }
    @RequestMapping(value = "/form/{id}",method = RequestMethod.GET)
    public String viewForm(@PathVariable("id") String id,Model model){
        model.addAttribute("form",formService.getFormById(id));
        model.addAttribute("preview","no");
        return "view-form";
    }
    @RequestMapping(value = "/form/{id}/preview",method = RequestMethod.GET)
    public String previewForm(@PathVariable("id") String id,Model model){
        model.addAttribute("form",formService.getFormById(id));
        model.addAttribute("preview","yes");
        return "view-form";
    }

    @RequestMapping(value = "/form/{formId}/widget/{widgetId}",method = RequestMethod.GET)
    public String viewWidgetOfForm(@PathVariable("formId") String formId,@PathVariable("widgetId") String widgetId,Model model){
        Widget widget = formService.findWidgetInFormById(formId,widgetId);
        model.addAttribute("widget",widget);
        return "view-form-widget";
    }

}
