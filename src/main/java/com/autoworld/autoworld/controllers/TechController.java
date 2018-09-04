package com.autoworld.autoworld.controllers;


import com.autoworld.autoworld.models.Tech;
import com.autoworld.autoworld.models.data.TechDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("techs")
public class TechController {


    @Autowired
    private TechDao techDao;


    @RequestMapping(value = "")
    public String displayTechs(Model model) {
        model.addAttribute("techs", techDao.findAll());
        model.addAttribute("title", "Current Technicians");

        return "techs/list";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addTechForm(Model model) {
        model.addAttribute("title", "Add a New Technician");
        model.addAttribute(new Tech());
        return "techs/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddTechForm(@ModelAttribute @Valid Tech newTech,
                                     Errors errors, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("title", "Add a New Technician");
            return "techs/add";
        }
        techDao.save(newTech);
        return "redirect:";
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String displayDeleteTechForm(Model model) {
        model.addAttribute("techs", techDao.findAll());
        model.addAttribute("title", "Delete Tech(s)");
        return "techs/delete";
    }
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String processDeleteTechForm(@RequestParam int[] techIds) {

        for (int techId : techIds) {
            techDao.delete(techId);
        }

        return "redirect:";
    }
}
