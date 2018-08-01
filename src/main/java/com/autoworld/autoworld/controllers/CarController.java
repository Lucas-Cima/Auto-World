package com.autoworld.autoworld.controllers;


import com.autoworld.autoworld.models.Car;
import com.autoworld.autoworld.models.data.CarDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("cars")
public class CarController {

    @Autowired
    private CarDao carDao;




    @RequestMapping(value = "")
    public String displayCars(Model model) {
        model.addAttribute("cars", carDao.findAll());
        model.addAttribute("title", "Current Cars");

        return "cars/list";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addCarForm(Model model) {
        model.addAttribute("title", "Add a New Car");
        model.addAttribute(new Car());
        return "cars/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
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
