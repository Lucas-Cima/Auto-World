package com.autoworld.autoworld.controllers;


import com.autoworld.autoworld.models.Car;
import com.autoworld.autoworld.models.Customer;
import com.autoworld.autoworld.models.User;
import com.autoworld.autoworld.models.data.CarDao;
import com.autoworld.autoworld.models.data.CustomerDao;
import com.autoworld.autoworld.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("cars")
public class CarController {

    @Autowired
    private CarDao carDao;

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private UserDao userDao;




    @RequestMapping(value = "")
    public String displayCars(Model model, @CookieValue(value = "user", defaultValue = "none") String username) {

        if(username.equals("none")) { return "redirect:/user/login"; }
        model.addAttribute("cars", carDao.findAll());
        model.addAttribute("title", "Current Cars");

        return "cars/list";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addCarForm(Model model, @CookieValue(value = "user", defaultValue = "none") String username) {
        if(username.equals("none")) { return "redirect:/user/login"; }
        model.addAttribute("title", "Add a New Car");
        model.addAttribute(new Car());
        model.addAttribute("customers", customerDao.findAll());
        return "cars/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCarForm(@ModelAttribute @Valid Car newCar,
                                    Errors errors, Model model, @RequestParam int customerId,
                                    @CookieValue(value = "user", defaultValue = "none") String username) {
        if(username.equals("none")) { return "redirect:/user/login"; }

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add a New Car");
            model.addAttribute("title", customerDao.findAll());
            return "cars/add";
        }
        Customer cus = customerDao.findOne(customerId);
        newCar.setCustomer(cus);
        carDao.save(newCar);
        return "redirect:";
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String displayDeleteCarForm(Model model, @CookieValue(value = "user", defaultValue = "none") String username) {
        if(username.equals("none")) { return "redirect:/user/login"; }
        model.addAttribute("cars", carDao.findAll());
        model.addAttribute("title", "Delete Car(s)");
        return "cars/delete";
    }
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String processDeleteCarForm(@RequestParam int[] carIds, @CookieValue(value = "user", defaultValue = "none") String username) {
        if(username.equals("none")) { return "redirect:/user/login"; }

        for (int carId : carIds) {
            carDao.delete(carId);
        }

        return "redirect:";
    }
}
