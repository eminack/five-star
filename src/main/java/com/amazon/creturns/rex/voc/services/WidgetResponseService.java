package com.amazon.creturns.rex.voc.services;

import com.amazon.creturns.rex.voc.HttpServletRequestProcessor;
import com.amazon.creturns.rex.voc.repositories.WidgetResponseRepository;
import com.amazon.creturns.rex.voc.user_response.widget.AbstractWidgetResponse;
import com.amazon.creturns.rex.voc.user_response.widget.WidgetResponseDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * This service class contains all the methods for widget response
 */
@Service
public class WidgetResponseService {

    @Autowired
    private WidgetResponseDeserializer widgetResponseDeserializer;

    @Autowired
    private WidgetResponseRepository widgetResponseRepository;

    /**
     * create an object of subclass of AbstractWidgetResponse from httpServletRequest , save it to widgetResponse Table
     * in DB & return the created object. The subclass depends on the key 'widgetType' of JSON Body
     * @param httpServletRequest the servletRequest containing the JSON String of object of subclass
     *                           of AbstractWidgetResponse
     * @return the created java object
     */
    public AbstractWidgetResponse saveResponse(final HttpServletRequest httpServletRequest) {
        final String jsonBody = HttpServletRequestProcessor.convertRequestBodyToJsonString(httpServletRequest);

        final String widgetType = widgetResponseDeserializer.getValueForKeyInJSONString("widgetType", jsonBody);

        final AbstractWidgetResponse obj = widgetResponseDeserializer.deserialize(jsonBody, widgetType);

        widgetResponseRepository.save(obj);
        return obj;
    }

    /**
     * fetch all widget Responses from widgetResponse Table
     * @return a list of AbstractWidgetResponse object
     */
    public List<AbstractWidgetResponse> getAllResponses() {
        return widgetResponseRepository.getAllResponses();
    }

    /**
     * this is just for testing purposes , to clear all entries in widgetResponse table
     * will be removed in final version */
    public void clearAllResponse() {
        widgetResponseRepository.clearAllResponse();
    }


}
