package com.widget.Controllers;

import com.widget.Entities.Form;
import com.widget.Entities.Widget;
import com.widget.Services.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@CrossOrigin
public class FormController {
    @Autowired
    private FormService formService;

    public FormController(FormService formService) {
        this.formService = formService;
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String mainPage(Model model){
        List<Form> formList= formService.getAllForms();
        model.addAttribute("forms",formList);
        return "main";
    }

    @RequestMapping(value = "/form",method = RequestMethod.POST)
    @ResponseBody
    public Form createNewForm(Model model, @RequestParam String name){
        Form form = formService.createNewForm(name);
        formService.saveForm(form);
        return form;
    }

    @RequestMapping(value = "/update-form",method = RequestMethod.GET)
    public String displayForm(Model model,@RequestParam String id){
        model.addAttribute("form",formService.getFormById(id));
        return "form";
    }
    @RequestMapping(value = "/delete-form",method = RequestMethod.GET)
    public String deleteFormById(@RequestParam("id") String id, Model model){
        formService.deleteFormById(id);
        model.addAttribute("forms",formService.getAllForms());
        return "redirect:";
    }

    @RequestMapping(value = "/add-widget",method = RequestMethod.POST)
    @ResponseBody
    public Form addWidgetToForm(@RequestParam("id") String id,Model model,@RequestBody Widget widget){
        formService.addWidget(id,widget);
        return formService.getFormById(id);
    }
    @RequestMapping(value = "/delete-widget",method = RequestMethod.POST)
    @ResponseBody
    public Form deleteWidgetFromForm(@RequestParam("formId") String formId,@RequestParam("widgetId") String widgetId){
        formService.deleteWidgetFromFormById(formId,widgetId);
        //model.addAttribute("form",formService.getFormById(formId));
        return formService.getFormById(formId);
    }
    @RequestMapping(value = "/view-form",method = RequestMethod.GET)
    public String viewForm(@RequestParam("id") String id,Model model){
        model.addAttribute("form",formService.getFormById(id));
        return "view-form";
    }

}
