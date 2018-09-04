package com.autoworld.autoworld.controllers;


import com.autoworld.autoworld.models.Customer;
import com.autoworld.autoworld.models.data.CustomerDao;
import com.autoworld.autoworld.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("customers")
public class CustomerController {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private UserDao userDao;


    @RequestMapping(value = "")
    public String displayCustomers(Model model, @CookieValue(value = "user", defaultValue = "none") String username) {
        if(username.equals("none")) { return "redirect:/user/login"; }
        model.addAttribute("customers", customerDao.findAll());
        model.addAttribute("title", "Current Customers");

        return "customers/list";
    }


    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addCustomerForm(Model model, @CookieValue(value = "user", defaultValue = "none") String username) {
        if(username.equals("none")) { return "redirect:/user/login"; }
        model.addAttribute("title", "Add a New Customer");
        model.addAttribute(new Customer());
        return "customers/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCustomerForm(@ModelAttribute @Valid Customer newCustomer,
                                         Errors errors, Model model,
                                         @CookieValue(value = "user", defaultValue = "none") String username) {
        if(username.equals("none")) { return "redirect:/user/login"; }

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add a New Car");
            return "customers/add";
        }
        customerDao.save(newCustomer);
        return "redirect:";
    }
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String displayDeleteCustomerForm(Model model, @CookieValue(value = "user", defaultValue = "none") String username) {
        if(username.equals("none")) { return "redirect:/user/login"; }
        model.addAttribute("customers", customerDao.findAll());
        model.addAttribute("title", "Delete Customer(s)");
        return "customers/delete";
    }
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String processDeleteCustomerForm(@RequestParam int[] customerIds,
                                            @CookieValue(value = "user", defaultValue = "none") String username) {

        if(username.equals("none")) { return "redirect:/user/login"; }

        for (int customerId : customerIds) {
            customerDao.delete(customerId);
        }

        return "redirect:";
    }
}
