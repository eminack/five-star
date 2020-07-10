package com.amazon.creturns.rex.voc.controllers;

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

/**
 * This class handles the incoming request for operations on Form
 */
@Log4j2
@Controller
@CrossOrigin
public class FormController {

    @Autowired
    private FormService formService;
    @Autowired
    private WidgetService widgetService;

    /**
     * This method handles the POST request to create a new Form
     * @param hashMap map containing the @{name} & @{userCreated} field
     * @return a object of created Form
     */
    @RequestMapping(value = "/form", method = RequestMethod.POST)
    @ResponseBody
    public Form createNewForm(@RequestBody final HashMap<String, String> hashMap) {
         return formService.createNewForm(hashMap);
    }

    /**
     * This method handles the request for deleting a particular form
     * @param formId ID of form to be deleted
     * @return a string "success"
     */
    @RequestMapping(value = "form/{id}/delete", method = RequestMethod.POST)
    @ResponseBody
    public String deleteFormById(@PathVariable("id") final String formId) {
        formService.deleteFormById(formId);
        return "success";
    }

    /**
     * This method handles the request to get the "edit-form.html" page for a particular form. The template is
     * loaded with form details at server side
     * @param id ID of Form whose details will be used to render the template
     * @param model
     * @return "edit-form.html" page
     */
    @RequestMapping(value = "form/{id}/edit", method = RequestMethod.GET)
    public String editFormPage(@PathVariable final String id, Model model) {
        model.addAttribute("form", formService.getFormById(id));

        log.info("Rendering Edit-form page");

        return "edit-form";
    }

    /**
     * This method handles the incoming GET request to view a form. We use a flag "preview" in model to know whether
     * to show "submit" button or not
     * @param id ID of form to be rendered on "view-form.html" template
     * @param model
     * @return "view-form".html page
     */
    @RequestMapping(value = "form/{id}", method = RequestMethod.GET)
    public String viewForm(@PathVariable("id") final String id, Model model) {
        model.addAttribute("form", formService.getFormById(id));
        model.addAttribute("preview", "no");

        log.info("Rendering view Form Page");

        return "view-form";
    }

    /**
     * This method handles the POST request to publish a particular Form
     * @param id ID of form which is to be published
     * @return string "success"
     */
    @RequestMapping(value = "form/{id}/publish", method = RequestMethod.POST)
    @ResponseBody
    public String publishForm(@PathVariable("id") final String id) {
        formService.publishForm(id);
        return "Success";
    }

    /**
     * This method handles the request to just view a form & not allow to submit any responses. "preview" flag is set
     * to 'yes' means that "submit" button will not be shown in "view-form.html" template
     * @param id ID of form which is to be previewed
     * @param model
     * @return "view-form.html" page
     */
    @RequestMapping(value = "form/{id}/preview", method = RequestMethod.GET)
    public String previewForm(@PathVariable("id") final String id, Model model) {
        model.addAttribute("form", formService.getFormById(id));
        model.addAttribute("preview", "yes");

        log.info("Rendering view-form page in preview mode");

        return "view-form";
    }

    /**
     * This method handles the request to add new widget to Form
     * @param formId ID of form in which the new widget needs to be added
     * @param httpServletRequest incoming request containing the details of widget to be added
     * @return the updated Form object
     */
    @RequestMapping(value = "/form/{id}/add-widget", method = RequestMethod.POST)
    @ResponseBody
    public Form addWidgetToForm(@PathVariable ("id") final String formId,
                                final HttpServletRequest httpServletRequest) {

        //deserialize the request body into AbstractWidget object
        final AbstractWidget obj = widgetService.createNewWidget(httpServletRequest);

        //add widget to form
        return formService.addWidget(formId, obj);
    }

    /**
     * This method handled the request to delete a widget from a Form
     * @param formId ID of form from which the widget needs to be deleted
     * @param widgetId ID of widget which needs to be deleted
     * @return the updated Form object
     */
    @RequestMapping(value = "form/{formId}/widget/{widgetId}/delete", method = RequestMethod.POST)
    @ResponseBody
    public Form deleteWidgetInFormById(@PathVariable("formId") final String formId,
                                       @PathVariable("widgetId") final String widgetId) {

        return formService.deleteWidgetById(formId, widgetId);
    }

    /**
     * This method handles the request for updating a particular widget in Form
     * @param formId ID of form which contains the widget to be updated
     * @param widgetId ID of widget which needs to be updated
     * @param httpServletRequest request containing the details with which the widget should be updated
     * @return the updated Form object
     */
    @RequestMapping(value = "form/{formId}/widget/{widgetId}/edit", method = RequestMethod.POST)
    @ResponseBody
    public Form UpdateWidgetInFormById(@PathVariable("formId") final String formId,
                                       @PathVariable("widgetId") final String widgetId,
                                       final HttpServletRequest httpServletRequest) {

        //deserialize the request body into AbstractWidget POJO
        final AbstractWidget obj = widgetService.createNewWidget(httpServletRequest);

        return formService.updateWidget(formId, widgetId, obj);
    }

    /**
     * This method handles the request to view a particular single widget in the form
     * @param formId ID of form containing the widget
     * @param widgetId ID of widget which is to be viewed
     * @param model
     * @return "view-form-widget.html" page with the widget rendered on it
     */
    @RequestMapping(value = "form/{formId}/widget/{widgetId}", method = RequestMethod.GET)
    public String getWidgetById(@PathVariable ("formId") final String formId,
                                @PathVariable("widgetId") final String widgetId, Model model) {

        model.addAttribute("widget", formService.getWidgetById(formId, widgetId));

        log.info("Rendering view-form-widget page");

        return "view-form-widget";
    }

    /**
     * This method handles the POST request to add constraints to a particular widget of form
     * @param formId ID of form containing the widget
     * @param widgetId ID of widget to which constraints need to be added
     * @param httpServletRequest request containing the constraint details
     * @return a string "success"
     */
    @RequestMapping(value = "/form/{formId}/widget/{widgetId}/add-constraint", method = RequestMethod.POST)
    @ResponseBody
    public String addConstraintsToWidget(@PathVariable("formId") final String formId,
                                        @PathVariable("widgetId") final String widgetId,
                                        final HttpServletRequest httpServletRequest) {

        formService.addConstraintsToWidget(formId, widgetId,  httpServletRequest);
        return "success";
    }

    /**
     * This method handled the POST request to delete constraints from a particular widget of form
     * @param formId ID of form containing the widget
     * @param widgetId ID of widget whose constraints need to be deleted
     * @return a string  "success"
     */
    @RequestMapping(value = "/form/{formId}/widget/{widgetId}/delete-constraint", method = RequestMethod.POST)
    @ResponseBody
    public String deleteConstraintsFromWidget(@PathVariable("formId") final String formId,
                                                @PathVariable("widgetId") final String widgetId) {

        formService.deleteAllConstraintsFromWidget(formId, widgetId);
        return "success";
    }


}
