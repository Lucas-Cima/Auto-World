package com.autoworld.autoworld.controllers;
import com.autoworld.autoworld.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("autoworld")
public class AutoWorldController {

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "")
    public String index(Model model, @CookieValue(value = "user", defaultValue = "none") String username) {

        if(username.equals("none")) { return "redirect:/user/login"; }

        return "index";
    }

}
