package com.widget.Controllers;

import com.widget.Entities.SingleWidgetResponse;
import com.widget.Services.SingleWidgetResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
public class SingleWidgetResponseController {

    @Autowired
    private SingleWidgetResponseService responseService;

    @RequestMapping(value = "/SingleWidgetResponse",method = RequestMethod.POST)
    @ResponseBody
    public String saveResponse(@RequestBody SingleWidgetResponse response){
       SingleWidgetResponse singleWidgetResponse = responseService.createNewResponse(response);
       responseService.saveResponse(singleWidgetResponse);
       return "Successful";
    }
}
