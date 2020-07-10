package com.amazon.creturns.rex.voc.services;

import com.amazon.creturns.rex.voc.form.Form;
import com.amazon.creturns.rex.voc.repositories.FormRepository;
import com.amazon.creturns.rex.voc.widget.AbstractWidget;
import com.amazon.creturns.rex.voc.widget.WidgetProcessorFactory;
import com.amazon.creturns.rex.voc.widget.widgetConstraint.WidgetConstraintCollection;
import com.amazon.creturns.rex.voc.widget.widgetConstraint.WidgetConstraintCollectionDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * This service class deals with all the methods for Form
 */
@Service
public class FormService {

    @Autowired
    private FormRepository formRepository;

    @Autowired
    private WidgetProcessorFactory widgetProcessorFactory;

    /**
     * create a Form object , save it to DB , & return the object
     * @param hashMap hashMap containing the values for keys 'formName' & 'userCreated'
     * @return the created object for form
     */
    public Form createNewForm(final HashMap<String, String> hashMap) {
        final Form form = new Form(hashMap.get("formName"));
        form.getMetaData().setUserCreated(hashMap.get("userCreated"));
        formRepository.save(form);
        return form;
    }

    /**
     * Process the Widget to be added , fetch the Form from DB using formID, add the Widget , update lastUpdateTime,
     * save it back to DB & return the fetched Form
     * @param formId ID of form in which the widget needs to be added
     * @param obj object of widget to be added to form
     * @return the updated Form object
     */
    public Form addWidget(final String formId, final AbstractWidget obj) {
        /* process  the widget */
        final AbstractWidget processedWidget = widgetProcessorFactory.getWidgetProcessor(obj.getWidgetType()).process(obj);

        /* get the form from db , add widget , save it back to db*/
        final Form form = formRepository.getFormById(formId);
        form.addWidget(processedWidget);
        form.updateLastUpdateTime();

        formRepository.save(form);
        return form;
    }

    /**
     * delete Form in form table using formId
     * @param formId the ID of form to be deleted in DB table
     */
    public void deleteFormById(final String formId) {
        formRepository.deleteFormById(formId);
    }

    /**
     * fetch Form from DB
     * @param id the ID of form to be fetched
     * @return the fetched Form object
     */
    public Form getFormById(final String id) {
        final Form form = formRepository.getFormById(id);
        form.updateLastServeTime();

        formRepository.save(form);
        return form;
    }

    /**
     * fetch all Form from DB
     * @return the list of Form object
     */
    public List<Form> getAllForms() {
        return formRepository.getAllForms();
    }

    /**
     * Method to delete Widget from Form : fetch Form from form Table , remove a particular widget from widget list
     * of Form using it's widgetId, update constraints of rest of the widget of Form , set the lastUpdate time to
     * current time, save it back to form Table & also return the Form object
     * @param formId Id of form which contains the Widget
     * @param widgetId Id of widget which is to be deleted
     * @return the updated Form object
     */
    public Form deleteWidgetById(final String formId, final String widgetId) {
        final Form form = formRepository.getFormById(formId);

        form.removeWidgetById(widgetId);

        /* whenever we delete a widget , remove constraints with this widget
            from rest of the widgets in Form */
        form.removeThisWidgetFromConstraintsOfAllWidgets(widgetId);

        form.updateLastUpdateTime();
        formRepository.save(form);

        return form;
    }

    /**
     * Update a particular Widget's field in Form. The widget to be updated is found by widgetId & it's fields are
     * updated by using the fields from newObj
     * @param formId ID of form which contains the widget to be updated
     * @param widgetId ID of widget to be updated
     * @param newObj the fields of this object , will be used to update the fields of widget with 'widgetId'
     * @return the updated Form object
     */
    public Form updateWidget(final String formId, final String widgetId, final AbstractWidget newObj) {
        /*process the new widget*/
        final AbstractWidget processedWidget = widgetProcessorFactory.getWidgetProcessor(newObj.getWidgetType()).process(newObj);

        final Form form = formRepository.getFormById(formId);

        //find the widget to be updated and call update on its object
        form.updateWidgetById(widgetId, processedWidget);

        form.updateLastUpdateTime();
        formRepository.save(form);

        return form;

    }

    /**
     * fetch widget from widgetList of Form object
     * @param formId ID of form which contains the widget
     * @param widgetId ID of widget which needs to be fetched
     * @return the fetched widget object
     */
    public AbstractWidget getWidgetById(final String formId, final String widgetId) {
        final Form form = formRepository.getFormById(formId);

        final AbstractWidget obj = form.getWidgetById(widgetId);
        obj.updateLastServeTime();

        formRepository.save(form);
        return obj;
    }

    /**
     * sets the status of Form object as published. For this 1st fetch the object from form Table ,update it's status &
     * audit info then save it back to form table
     * @param formId ID of form whose status is to be published
     */
    public void publishForm(final String formId) {
        final Form form = formRepository.getFormById(formId);

        form.setFormStatus("PUBLISHED");
        form.updateLastUpdateTime();

        formRepository.save(form);
    }

    /**
     * Add constraints to a particular widget of Form.
     *
     * @param formId ID of form containing the widget
     * @param widgetId ID of form whose constraints field needs to be set
     * @param httpServletRequest httpRequest containing the JSON String of WidgetConstraintCollection , basically the
     *                           constraints field of that widget will be set to object of this JSON
     */
    public void addConstraintsToWidget(final String formId, final String widgetId,
                                       final HttpServletRequest httpServletRequest) {

        final WidgetConstraintCollection constraintCollection = WidgetConstraintCollectionDeserializer.
                getConstraintCollectionObjectFromHttpRequest(httpServletRequest);

        /*find the widget and set it's constraints field
        * with the constraints received in httpRequestBody*/
        final Form form = formRepository.getFormById(formId);
        form.addConstraintsToWidget(widgetId, constraintCollection);

        form.updateLastUpdateTime();
        formRepository.save(form);
    }

    /**
     * set the constraints field of a particular widget of form to null
     *
     * @param formId ID of form containing the widget
     * @param widgetId ID of widget whose constraints need to be deleted
     */
    public void deleteAllConstraintsFromWidget(final String formId, final String widgetId) {
        /* find the widget and set its constraint field to null*/
        final Form form = formRepository.getFormById(formId);
        form.deleteAllConstraintsFromWidget(widgetId);

        /*update the Audit info of form & save it back to form Table in DB*/
        form.updateLastUpdateTime();
        formRepository.save(form);
    }
}
