package com.autoworld.autoworld.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("autoworld")
public class AutoWorldController {


    @RequestMapping(value = "")
    public String index(Model model) {

        return "index";
    }

}
