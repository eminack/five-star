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

@Service
public class FormService {
    @Autowired
    private FormRepository formRepository;
    @Autowired
    private WidgetProcessorFactory widgetProcessorFactory;

    public Form createNewForm(final HashMap<String, String> hashMap) {
        Form form = new Form(hashMap.get("formName"));
        form.getMetaData().setUserCreated(hashMap.get("userCreated"));
        formRepository.save(form);
        return form;
    }

    public Form addWidget(String formId, AbstractWidget obj) {
        //validate the widget
        obj = widgetProcessorFactory.getWidgetProcessor(obj.getWidgetType()).process(obj);
        //get the form from db , add widget , save it back to db
        Form form = formRepository.getFormById(formId);
        form.addWidget(obj);
        form.updateLastUpdateTime();

        formRepository.save(form);
        return form;
    }

    public void deleteFormById(final String formId) {
        formRepository.deleteFormById(formId);
    }

    public Form getFormById(final String id) {
        Form form = formRepository.getFormById(id);
        form.updateLastServeTime();
        formRepository.save(form);
        return form;
    }

    public List<Form> getAllForms() {
        return formRepository.getAllForms();
    }

    public Form deleteWidgetById(final String formId, final String widgetId) {
        Form form = formRepository.getFormById(formId);

        form.removeWidgetById(widgetId);
        /* whenever we delete a widget , remove constraints with this widget
            from rest of the widgets in Form */
        form.removeThisWidgetFromConstraintsOfAllWidgets(widgetId);
        form.updateLastUpdateTime();

        formRepository.save(form);
        return form;
    }

    @SuppressWarnings("unchecked")
    public Form updateWidget(final String formId, AbstractWidget newObj) {
        newObj = widgetProcessorFactory.getWidgetProcessor(newObj.getWidgetType()).process(newObj);

        Form form = formRepository.getFormById(formId);
        //find the widget to be updated and call update on its object
        form.updateWidgetById(newObj);
        form.updateLastUpdateTime();
        formRepository.save(form);
        return form;

    }

    public AbstractWidget getWidgetById(final String formId, final String widgetId) {
        Form form = formRepository.getFormById(formId);

        AbstractWidget obj = form.getWidgetById(widgetId);
        obj.updateLastServeTime();

        formRepository.save(form);
        return obj;

    }

    public void publishForm(final String formId) {
        Form form = formRepository.getFormById(formId);
        form.setFormStatus("PUBLISHED");
        form.updateLastUpdateTime();
        formRepository.save(form);
    }

    public void addConstraintsToWidget(final String formId, final String widgetId,
                                       HttpServletRequest httpServletRequest) {
        WidgetConstraintCollection constraintCollection = WidgetConstraintCollectionDeserializer.
                getConstraintCollectionObjectFromHttpRequest(httpServletRequest);

        /*find the widget and set it's constraints field
        * with the constraints received in httpRequestBody*/
        Form form = formRepository.getFormById(formId);
        form.addConstraintsToWidget(widgetId, constraintCollection);
        form.updateLastUpdateTime();

        formRepository.save(form);
    }

    public void deleteAllConstraintsFromWidget(String formId, String widgetId) {
        /* find the widget and set its constraint field to null*/
        Form form = formRepository.getFormById(formId);
        form.deleteAllConstraintsFromWidget(widgetId);
        form.updateLastUpdateTime();
        formRepository.save(form);
    }
}
