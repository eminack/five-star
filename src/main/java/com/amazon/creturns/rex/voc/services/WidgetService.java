package com.amazon.creturns.rex.voc.services;

import com.amazon.creturns.rex.voc.HttpServletRequestProcessor;
import com.amazon.creturns.rex.voc.repositories.WidgetRepository;
import com.amazon.creturns.rex.voc.widget.AbstractWidget;
import com.amazon.creturns.rex.voc.widget.WidgetDeserializer;
import com.amazon.creturns.rex.voc.widget.WidgetProcessorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * This service class deals with all the methods for widgets
 */
@Service
public class WidgetService {

    @Autowired
    private WidgetRepository widgetRepository;

    @Autowired
    private WidgetDeserializer widgetDeserializer;

    @Autowired
    private WidgetProcessorFactory widgetProcessorFactory;

    /**
     * Creates java object of subclass of AbstractWidget from HttpServletRequest Body
     * @param httpServletRequest ServletRequest whose body contains JSON String of subclass of AbstractWidget
     * @return the created java object
     */
    public AbstractWidget createNewWidget(final HttpServletRequest httpServletRequest) {

        final String jsonString = HttpServletRequestProcessor.convertRequestBodyToJsonString(httpServletRequest);
        final String type = widgetDeserializer.getValueForKeyInJSONString("widgetType", jsonString);

        return widgetDeserializer.deserialize(jsonString, type);
    }

    /**
     * process the object means to set the default to null fields & then save the Abstract Widget into widget Table in DB
     * @param obj java object of subclass of AbstractWidget which is to be saved in DB table
     * @return return the object
     */
    public AbstractWidget save(final AbstractWidget obj) {
        final AbstractWidget widget = widgetProcessorFactory.getWidgetProcessor(obj.getWidgetType()).process(obj);

        widgetRepository.save(widget);
        return widget;
    }

    /**
     * fetch widget from widget Table by widgetID , update it's lastServeTime , save back to DB, and return the fetched
     * widget object
     * @param id : id of widget to be fetched
     * @return the fetched java object
     */
    public AbstractWidget getWidgetById(final String id) {
        final AbstractWidget obj = widgetRepository.getSingleWidgetById(id);

        obj.updateLastServeTime();
        widgetRepository.save(obj);

        return obj;
    }

    /**
     * get All AbstractWidget object as list from widget Table in DB
     * @return : list of all widget saved in widget Table
     */
    public List<AbstractWidget> getAllWidgets() {
        return widgetRepository.getAllWidgets();
    }

    /**
     * delete widget from widget Table by widgetId
     * @param id the Id of widget to be deleted
     */
    public void deleteWidgetById(final String id) {
        widgetRepository.deleteWidgetById(id);
    }

    /**
     * update the widget in widget Table with the new details
     * @param id  ID of the widget to be updated
     * @param httpServletRequest request containing the AbstractWidget object , using which the widget
     *                           stored in Widget table is updated.
     * @return the updated widget
     */
    public AbstractWidget updateWidget(final String id, final HttpServletRequest httpServletRequest) {

        /* extract the body from HttpServletRequest and deserialize it into a subclass of AbstractWidget*/
        final String jsonString  = HttpServletRequestProcessor.convertRequestBodyToJsonString(httpServletRequest);
        final String widgetType = widgetDeserializer.getValueForKeyInJSONString("widgetType", jsonString);

        /* deserialize the jsonString into object*/
        AbstractWidget newObj = widgetDeserializer.deserialize(jsonString, widgetType);

        /* processing the deserialized object */
        newObj = widgetProcessorFactory.getWidgetProcessor(widgetType).process(newObj);

        /* get the object from DB & update its details with newObj */
        final AbstractWidget oldWidget = widgetRepository.getSingleWidgetById(id);
        oldWidget.updateDetails(newObj);

        widgetRepository.save(oldWidget);
        return oldWidget;
    }
}
