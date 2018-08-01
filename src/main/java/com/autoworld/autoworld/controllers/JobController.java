package com.autoworld.autoworld.controllers;

import com.autoworld.autoworld.models.Job;
import com.autoworld.autoworld.models.data.JobDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;


@Controller
@RequestMapping("jobs")
public class JobController {

    @Autowired
    private JobDao jobDao;

    @RequestMapping(value = "")
    public String displayJobs(Model model) {
        model.addAttribute("jobs", jobDao.findAll());
        model.addAttribute("title", " List of Jobs");

        return "jobs/list";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addJobForm(Model model) {
        model.addAttribute("title", "Add a New Job");
        model.addAttribute(new Job());
        return "jobs/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                         Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add a New Job");
            return "jobs/add";
        }
        jobDao.save(newJob);
        return "redirect:";
    }

}