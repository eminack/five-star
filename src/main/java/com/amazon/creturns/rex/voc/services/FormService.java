package com.amazon.creturns.rex.voc.services;
import com.amazon.creturns.rex.voc.form.Form;
import com.amazon.creturns.rex.voc.repositories.form.FormRepository;
import com.amazon.creturns.rex.voc.widget.AbstractWidget;
import com.amazon.creturns.rex.voc.widget.WidgetProcessorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class FormService {
    @Autowired
    private FormRepository formRepository;
    @Autowired
    private WidgetProcessorFactory widgetProcessorFactory;

    public Form createNewForm(HashMap<String,String> hmap) {
        Form form = new Form(hmap.get("formName"));
        form.getMetaData().setUserCreated(hmap.get("userCreated"));
        formRepository.save(form);
        return form;
    }

    public Form addWidget(String formId, AbstractWidget obj) {
        //validate the widget
        obj = widgetProcessorFactory.getWidgetProcessor(obj.getWidgetType()).process(obj);
        //get the form from db , add widget , save it back to db
        Form form = formRepository.getFormById(formId);
        form.getWidgets().add(obj);
        form.getMetaData().updateLastUpdateTime();
        formRepository.save(form);
        return form;
    }

    public void deleteFormById(String formId) {
        formRepository.deleteFormById(formId);
    }

    public Form getFormById(String id) {
        Form form = formRepository.getFormById(id);
        form.getMetaData().updateLastServeTime();
        formRepository.save(form);
        return form;
    }

    public List<Form> getAllForms() {
        return formRepository.getAllForms();
    }

    public Form deleteWidgetById(String formId, String widgetId) {
        Form form  = formRepository.getFormById(formId);
        form.getWidgets().removeIf(widget -> (widget.getWidgetId().compareTo(widgetId)==0));
        formRepository.save(form);
        return form;
    }

    public Form updateWidget(String formId, AbstractWidget obj) {
        obj = widgetProcessorFactory.getWidgetProcessor(obj.getWidgetType()).process(obj);

        Form form = formRepository.getFormById(formId);
        //find the widget to be updated and call update on its object
        for(int i=0;i<form.getWidgets().size();i++){
             if (form.getWidgets().get(i).getWidgetId().compareTo(obj.getWidgetId())==0){
                 form.getWidgets().get(i).updateDetails(obj);
                 break;
             }
        }
        formRepository.save(form);
        return form;

    }

    public AbstractWidget getWidgetById(String formId, String widgetId) {
        Form form = formRepository.getFormById(formId);
        AbstractWidget obj = form.getWidgets().stream().parallel().filter(widget -> widget.getWidgetId().compareTo(widgetId)==0)
                .findFirst().get();
        obj.getMetaData().updateLastServeTime();
        formRepository.save(form);
        return obj;

    }

    public void publishForm(String formId) {
        Form form = formRepository.getFormById(formId);
        form.setFormStatus("PUBLISHED");
        formRepository.save(form);
    }
}
