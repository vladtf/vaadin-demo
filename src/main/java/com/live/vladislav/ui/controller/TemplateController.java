package com.live.vladislav.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api")
public class TemplateController {

    @RequestMapping("calculator")
    public String getCalculatorView(){
        return "calculator";
    }
}
