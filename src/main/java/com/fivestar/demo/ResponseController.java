package com.fivestar.demo;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@CrossOrigin
public class ResponseController {

    private final ResponseRepository repository;

    public ResponseController(ResponseRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public List<Response> getAllResponse(){
        return repository.findAll();
    }
    @GetMapping("/{id}")
    public Response findById(@PathVariable String id){
        return repository.findByUserId(id);
    }
    @PostMapping("/post")
    public Response saveResponse(@RequestBody Response response){
        return repository.save(response);
    }
    @GetMapping("/widget_1")
    public ModelAndView showWidget(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        return modelAndView;
    }
}
