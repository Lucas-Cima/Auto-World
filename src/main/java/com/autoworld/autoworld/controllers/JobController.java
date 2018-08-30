package com.autoworld.autoworld.controllers;

import com.autoworld.autoworld.models.Car;
import com.autoworld.autoworld.models.Job;
import com.autoworld.autoworld.models.Tech;
import com.autoworld.autoworld.models.data.CarDao;
import com.autoworld.autoworld.models.data.CustomerDao;
import com.autoworld.autoworld.models.data.JobDao;
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
@RequestMapping("jobs")
public class JobController {

    @Autowired
    private JobDao jobDao;

    @Autowired
    private CarDao carDao;

    @Autowired
    private TechDao techDao;

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
        model.addAttribute("cars", carDao.findAll());
        model.addAttribute("techs", techDao.findAll());
        return "jobs/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddJobForm(@ModelAttribute @Valid Job newJob, Errors errors,
                                    Model model, @RequestParam int carId, @RequestParam int techId) {
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

}