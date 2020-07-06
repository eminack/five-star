package com.amazon.creturns.rex.voc.services;

import com.amazon.creturns.rex.voc.HttpServletRequestProcessor;
import com.amazon.creturns.rex.voc.repositories.WidgetResponseRepository;
import com.amazon.creturns.rex.voc.user_response.widget.AbstractWidgetResponse;
import com.amazon.creturns.rex.voc.user_response.widget.WidgetResponseDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class WidgetResponseService {

    @Autowired
    private WidgetResponseDeserializer widgetResponseDeserializer;
    @Autowired
    private WidgetResponseRepository widgetResponseRepository;

    public AbstractWidgetResponse saveResponse(HttpServletRequest httpServletRequest) {
        String jsonBody = HttpServletRequestProcessor.convertRequestBodyToJsonString(httpServletRequest);
        String widgetType = widgetResponseDeserializer.getWidgetType(jsonBody);
        AbstractWidgetResponse obj = widgetResponseDeserializer.deserialize(jsonBody, widgetType);
        widgetResponseRepository.save(obj);
        return obj;
    }

    public List<AbstractWidgetResponse> getAllResponses() {
        return widgetResponseRepository.getAllResponses();
    }

    /* this is just for testing purposes , to clear all entries in widgetResponse table
    * will be removed in final version*/
    public void clearAllResponse() {
        widgetResponseRepository.clearAllResponse();
    }


}
