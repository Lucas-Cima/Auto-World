package com.autoworld.autoworld.controllers;

import com.autoworld.autoworld.models.Car;
import com.autoworld.autoworld.models.Job;
import com.autoworld.autoworld.models.Tech;
import com.autoworld.autoworld.models.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("jobs")
public class JobController {

    @Autowired
    private JobDao jobDao;

    @Autowired
    private CarDao carDao;

    @Autowired
    private TechDao techDao;

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "")
    public String displayJobs(Model model, @CookieValue(value = "user", defaultValue = "none") String username) {
        if(username.equals("none")) { return "redirect:/user/login"; }
        model.addAttribute("jobs", jobDao.findAll());
        model.addAttribute("title", " List of Jobs");

        return "jobs/list";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addJobForm(Model model, @CookieValue(value = "user", defaultValue = "none") String username) {
        if(username.equals("none")) { return "redirect:/user/login"; }
        model.addAttribute("title", "Add a New Job");
        model.addAttribute(new Job());
        model.addAttribute("cars", carDao.findAll());
        model.addAttribute("techs", techDao.findAll());
        return "jobs/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddJobForm(@ModelAttribute @Valid Job newJob, Errors errors,
                                    Model model, @RequestParam int carId, @RequestParam int techId,
                                    @CookieValue(value = "user", defaultValue = "none") String username) {
        if(username.equals("none")) { return "redirect:/user/login"; }

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add a New Job");
            model.addAttribute("cars", carDao.findAll());
            model.addAttribute("techs", techDao.findAll());
            return "jobs/add";
        }

        Tech t = techDao.findOne(techId);
        newJob.setTech(t);
        Car c = carDao.findOne(carId);
        newJob.setCar(c);

        jobDao.save(newJob);
        return "redirect:";
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String displayDeleteJobForm(Model model, @CookieValue(value = "user", defaultValue = "none") String username) {
        if(username.equals("none")) { return "redirect:/user/login"; }
        model.addAttribute("jobs", jobDao.findAll());
        model.addAttribute("title", "Delete Job(s)");
        return "jobs/delete";
    }
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String processDeleteJobForm(@RequestParam int[] jobIds,
                                       @CookieValue(value = "user", defaultValue = "none") String username) {

        if(username.equals("none")) { return "redirect:/user/login"; }

        for (int jobId : jobIds) {
            jobDao.delete(jobId);
        }

        return "redirect:";
    }

}