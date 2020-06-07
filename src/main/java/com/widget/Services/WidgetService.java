package com.widget.Services;

import com.widget.Entities.SingleWidget;
import com.widget.Entities.Widget;
import com.widget.Repositories.WidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class WidgetService {
    @Autowired
    private WidgetRepository widgetRepository;

    public void saveWidget(SingleWidget widget) {
        widgetRepository.save(widget);
    }


    public SingleWidget createNewWidget(SingleWidget widget) {
        SingleWidget singleWidget = new SingleWidget(widget.getWidgetName(),widget.getWidgetType(),widget.getWidgetVersion(),
                widget.getTemplateId(),widget.getUserCreated(),widget.getData());
        return singleWidget;
    }

    public List<SingleWidget> getAllWidgets() {
        List<SingleWidget> widgetList = widgetRepository.findAll();
        return  widgetList;
    }

    public void deleteWidgetById(String id) {
        widgetRepository.deleteById(id);
    }

    public SingleWidget getWidgetById(String id) {
        return widgetRepository.findById(id).get();
    }
}
