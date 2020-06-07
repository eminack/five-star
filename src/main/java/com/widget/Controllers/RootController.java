package com.widget.Controllers;

import com.widget.Entities.Form;
import com.widget.Entities.SingleWidget;
import com.widget.Services.FormService;
import com.widget.Services.WidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@CrossOrigin
public class RootController {
    @Autowired
    private FormService formService;
    @Autowired
    private WidgetService widgetService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String mainPage(Model model){
        List<Form> formList= formService.getAllForms();
        List<SingleWidget> widgetList = widgetService.getAllWidgets();
        model.addAttribute("forms",formList);
        model.addAttribute("widgets",widgetList);
        return "main-page";
    }

}
