package com.amazon.creturns.rex.voc.controllers;

import com.amazon.creturns.rex.voc.form.Form;
import com.amazon.creturns.rex.voc.language.LanguageEntity;
import com.amazon.creturns.rex.voc.repositories.LanguageRepository;
import com.amazon.creturns.rex.voc.services.FormService;
import com.amazon.creturns.rex.voc.services.WidgetService;
import com.amazon.creturns.rex.voc.widget.AbstractWidget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * This class handles the request for serving main page
 */
@Controller
@CrossOrigin
public class RootController {

    @Autowired
    private WidgetService widgetService;

    @Autowired
    private FormService formService;

    @Autowired
    private LanguageRepository languageRepository;

    /**
     * This method handles the request for serving main-page.html.
     * The widgetService  & formService fetch all the entries from 'widget' & 'form' table respectively
     * @param model
     * @return "main-page.html"
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainPage(Model model) {
        final List<AbstractWidget> widgetList = widgetService.getAllWidgets();
        final List<Form> formList = formService.getAllForms();

        model.addAttribute("forms", formList);
        model.addAttribute("widgets", widgetList);

        return "main-page";
    }

    /**
     * this method is just for testing purposes to save string ID for localization in DB , will be removed
     * in final versions
     * @param receivedList a list of LanguageEntity which is to be saved in 'language' table
     * @return A list of All the LanguageEntities which are saved into 'language' table
     */
    @RequestMapping(value = "/saveLanguageEntities", method = RequestMethod.POST)
    @ResponseBody
    public List<LanguageEntity> saveInDB(@RequestBody final List<LanguageEntity> receivedList) {
        final List<LanguageEntity> list = new ArrayList<>();

        for (LanguageEntity languageEntity : receivedList) {
            languageRepository.insertIntoDynamoDb(languageEntity);

            list.add(languageRepository.findByLocaleAndStringId(languageEntity.getLocale(),
                       languageEntity.getStringId()));

        }
        return list;
    }
}
