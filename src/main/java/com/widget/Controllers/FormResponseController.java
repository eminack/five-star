package com.widget.Controllers;

import com.widget.Entities.Form;
import com.widget.Entities.FormResponse;
import com.widget.Services.FormResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
public class FormResponseController {

    @Autowired
    private FormResponseService responseService;

    @RequestMapping(value = "/FormResponse",method = RequestMethod.POST)
    @ResponseBody
    public String saveResponse(@RequestBody FormResponse response){
        FormResponse formResponse = responseService.createNewResponse(response);
        responseService.saveResponse(formResponse);
        return "successful";
    }
}
