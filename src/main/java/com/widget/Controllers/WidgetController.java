package com.widget.Controllers;

import com.widget.Entities.SingleWidget;
import com.widget.Services.WidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
public class WidgetController {
    @Autowired
    private WidgetService widgetService;

    @RequestMapping(value = "/widget",method = RequestMethod.POST)
    @ResponseBody
    public SingleWidget CreateWidget(@RequestBody SingleWidget widget){
        SingleWidget singleWidget = widgetService.createNewWidget(widget);
        widgetService.saveWidget(singleWidget);
        return singleWidget;
    }
    @RequestMapping(value = "/widget/{id}",method = RequestMethod.GET)
    public String viewWidget(@PathVariable("id") String id,Model model){
        model.addAttribute("widget",widgetService.getWidgetById(id));
        return "view-single-widget";
    }

    @RequestMapping(value = "/widget/{id}/delete",method = RequestMethod.POST)
    @ResponseBody
    public String deleteWidget(@PathVariable("id") String id){
        widgetService.deleteWidgetById(id);
        return "Successful";
    }
}
