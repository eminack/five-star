package com.amazon.creturns.rex.voc.controllers;

import com.amazon.creturns.rex.voc.form.Form;
import com.amazon.creturns.rex.voc.language.LanguageEntity;
import com.amazon.creturns.rex.voc.repositories.language.LanguageRepository;
import com.amazon.creturns.rex.voc.services.FormService;
import com.amazon.creturns.rex.voc.services.WidgetService;
import com.amazon.creturns.rex.voc.widget.AbstractWidget;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin
public class RootController {
    @Autowired
    private WidgetService widgetService;
    @Autowired
    private FormService formService;
    @Autowired
    private LanguageRepository languageRepository;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String mainPage(Model model){
        List<AbstractWidget> widgetList = widgetService.getAllWidgets();
        List<Form> formList = formService.getAllForms();
        model.addAttribute("forms",formList);
        model.addAttribute("widgets",widgetList);
        return "main-page";
    }

    /* this controller is just for testing purposes to save string ID
      for localization in DB , will be removed in final versions
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public List<LanguageEntity> saveInDB(@RequestBody List<LanguageEntity> l)
    {
        ArrayList<LanguageEntity> list = new ArrayList<>();
           for(LanguageEntity ll:l) {
               languageRepository.insertIntoDynamoDb(ll);
               list.add(languageRepository.findByLocaleAndStringId(ll.getLocale(),ll.getStringId()));
           }
           return list;

    }
}
