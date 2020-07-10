package com.amazon.creturns.rex.voc.controllers;

import com.amazon.creturns.rex.voc.services.WidgetService;
import com.amazon.creturns.rex.voc.widget.AbstractWidget;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * This class deals with all incoming request for stand alone widgets
 */
@Log4j2
@Controller
@CrossOrigin
public class WidgetController {

    @Autowired
    private WidgetService widgetService;

    /**
     * this method handled the request to create a new widget
     * @param httpServletRequest request containing details of widget
     * @return the created widget object
     */
    @RequestMapping(value = "/widget", method = RequestMethod.POST)
    @ResponseBody
    public AbstractWidget createWidget(final HttpServletRequest httpServletRequest) {
        final AbstractWidget obj =  widgetService.createNewWidget(httpServletRequest);
        return widgetService.save(obj);
    }

    /**
     * This method fetches a particular widget from 'widget' table using it's ID
     * @param id ID of widget to be fetched
     * @param model
     * @return the page "view-single-widget.html'
     */
    @RequestMapping(value = "/widget/{id}", method = RequestMethod.GET)
    public String getWidgetById(@PathVariable("id") final String id, Model model) {
        model.addAttribute("widget", widgetService.getWidgetById(id));
        log.info("Rendering view-single-widget page");
        return "view-single-widget";
    }

    /**
     * This method deletes a particular widget from 'widget' table using it's ID
     * @param id ID of the widget to be deleted
     * @return a String "success"
     */
    @RequestMapping(value = "/widget/{id}/delete", method = RequestMethod.POST)
    @ResponseBody
    public String deleteWidgetById(@PathVariable final String id) {
        widgetService.deleteWidgetById(id);
        return "success";
    }

    /**
     * This method handles request for updating a widget in 'widget' table
     * @param id ID of widget to be updated
     * @param httpServletRequest request whose body contains the details with which the widget will be updated
     * @return the updated AbstractWidget object
     */
    @RequestMapping(value = "/widget/{id}/edit", method = RequestMethod.POST)
    @ResponseBody
    public AbstractWidget updateWidget(@PathVariable final String id, final HttpServletRequest httpServletRequest) {
        return widgetService.updateWidget(id, httpServletRequest);
    }

}
