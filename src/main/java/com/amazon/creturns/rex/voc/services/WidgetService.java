package com.amazon.creturns.rex.voc.services;

import com.amazon.creturns.rex.voc.HttpServletRequestProcessor;
import com.amazon.creturns.rex.voc.repositories.widget.WidgetRepository;
import com.amazon.creturns.rex.voc.widget.AbstractWidget;
import com.amazon.creturns.rex.voc.widget.WidgetDeserializer;
import com.amazon.creturns.rex.voc.widget.WidgetProcessorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Service
public class WidgetService {
    @Autowired
    private WidgetRepository widgetRepository;
    @Autowired
    private WidgetDeserializer widgetDeserializer;
    @Autowired
    private WidgetProcessorFactory widgetProcessorFactory;
    @Autowired
    private HttpServletRequestProcessor httpServletRequestProcessor;

    public AbstractWidget createNewWidget(HttpServletRequest httpServletRequest) {
        String jsonString = httpServletRequestProcessor.convertRequestBodyToJsonString(httpServletRequest);
        String type = widgetDeserializer.getWidgetType(jsonString);
        AbstractWidget obj =  widgetDeserializer.deserialize(jsonString,type);
        return obj;
    }

    public AbstractWidget save(AbstractWidget obj) {
        obj = widgetProcessorFactory.getWidgetProcessor(obj.getWidgetType()).process(obj);
        widgetRepository.save(obj);
        return obj;
    }

    public AbstractWidget getWidgetById(String id) {
        AbstractWidget obj = widgetRepository.getSingleWidgetById(id);
        obj.getMetaData().updateLastServeTime();
        widgetRepository.save(obj);
        return obj;
    }

    public List<AbstractWidget> getAllWidgets() {
        return widgetRepository.getAllWidgets();
    }

    public void deleteWidgetById(String id) {
        widgetRepository.deleteWidgetById(id);
    }

    public AbstractWidget updateWidget(String id, HttpServletRequest httpServletRequest) {
        //extracting the received object
        String jsonString  = httpServletRequestProcessor.convertRequestBodyToJsonString(httpServletRequest);
        String widgetType = widgetDeserializer.getWidgetType(jsonString);
        AbstractWidget newObj = widgetDeserializer.deserialize(jsonString,widgetType);
        //validating the deserialized object
        newObj = widgetProcessorFactory.getWidgetProcessor(widgetType).process(newObj);

        //get the object from DB & update its details with received object
        AbstractWidget oldWidget = widgetRepository.getSingleWidgetById(id);
        oldWidget.updateDetails(newObj);

        widgetRepository.save(oldWidget);
        return oldWidget;
    }
}
