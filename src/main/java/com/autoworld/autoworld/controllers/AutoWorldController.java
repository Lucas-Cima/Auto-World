package com.autoworld.autoworld.controllers;


import com.autoworld.autoworld.models.Car;
import com.autoworld.autoworld.models.Tech;
import com.autoworld.autoworld.models.data.CarDao;
import com.autoworld.autoworld.models.data.TechDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

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



    @RequestMapping(value = "techs")
    public String displayTechs(Model model) {
        model.addAttribute("techs", techDao.findAll());
        model.addAttribute("title", "Current Technicians");

        return "techs/list";
    }

    @RequestMapping(value = "techs/add", method = RequestMethod.GET)
    public String addTechForm(Model model) {
        model.addAttribute("title", "Add a New Technician");
        model.addAttribute(new Tech());
        return "techs/add";
    }

    @RequestMapping(value = "techs/add", method = RequestMethod.POST)
    public String processAddTechForm(@ModelAttribute @Valid Tech newTech,
                                     Errors errors, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("title", "Add a New Technician");
            return "techs/add";
        }
        techDao.save(newTech);
        return "redirect:";
    }


    @RequestMapping(value = "cars")
    public String displayCars(Model model) {
        model.addAttribute("cars", carDao.findAll());
        model.addAttribute("title", "Current Cars");

        return "cars/list";
    }

    @RequestMapping(value = "cars/add", method = RequestMethod.GET)
    public String addCarForm(Model model) {
        model.addAttribute("title", "Add a New Car");
        model.addAttribute(new Car());
        return "cars/add";
    }

    @RequestMapping(value = "cars/add", method = RequestMethod.POST)
    public String processAddCarForm(@ModelAttribute @Valid Car newCar,
                                     Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add a New Car");
            return "cars/add";
        }
        carDao.save(newCar);
        return "redirect:";
    }



}
