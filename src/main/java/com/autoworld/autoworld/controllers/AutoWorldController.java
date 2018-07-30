package com.autoworld.autoworld.controllers;


import com.autoworld.autoworld.models.data.CarDao;
import com.autoworld.autoworld.models.data.TechDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("autoworld")
public class AutoWorldController {

    @Autowired
    private TechDao techDao;

    @Autowired
    private CarDao carDao;

    @RequestMapping(value = "")
    public String index(Model model) {

        return "index";
    }



    //@RequestMapping(value = "techs")
    //public String displayTechs(Model model) {
       // model.addAttribute("techs", techDao.findall());
        //model.addAttribute("title", "Current Technicians");

        //return "autoworld/techs";
    //}

}
