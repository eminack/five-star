package com.widget.Services;

import com.widget.Entities.Form;
import com.widget.Entities.Widget;
import com.widget.Repositories.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormService {
    @Autowired
    private FormRepository formRepository;

    public FormService(FormRepository formRepository) {
        this.formRepository = formRepository;
    }

    public void deleteFormById(String id) {
        formRepository.deleteById(id);
    }

    public Form createNewForm(String name){
        return new Form(name);
    }
    public void saveForm(Form form){
        formRepository.save(form);
    }

    public List<Form> getAllForms() {
        return formRepository.findAll();
    }

    public Form getFormById(String id) {
        return formRepository.findById(id).get();
    }

    public void addWidget(String formId, Widget widget) {
        Form form = formRepository.findById(formId).get();
        form.addWidget(widget);
        formRepository.save(form);
    }

    public void deleteWidgetFromFormById(String formId, String widgetId) {
        Form form = formRepository.findById(formId).get();
        form.getWidgets().removeIf(widget -> (widget.getWidgetId()).compareTo(widgetId)==0);
        formRepository.save(form);
    }

    public Widget findWidgetInFormById(String formId, String widgetId) {
        Form form = formRepository.findById(formId).get();
        return form.getWidgets().stream().filter(p->((p.getWidgetId()).compareTo(widgetId))==0).findFirst().get();
    }
}
