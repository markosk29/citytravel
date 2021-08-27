package com.avangarde.citytravel.api.controller;


import com.avangarde.citytravel.api.service.CitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GreetingController {
    private final CitiesService citiesService;


    @RequestMapping("/")
    public ModelAndView home() {
        citiesService.getRoute().clear();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("cities", this.citiesService.getAllCities());
        modelAndView.setViewName("travel");
        return modelAndView;
    }

    @GetMapping("/city/{id}")
    public ModelAndView getCity(@PathVariable int id) {
        if (!(citiesService.getRoute().isEmpty())) {
            if ((id == citiesService.getRoute().get(citiesService.getRoute().size() - 1).getCity_id())) {
                citiesService.deleteLastCity();
            }
        }
        citiesService.addCityToRoute(id);
        ModelAndView modelAndView = new ModelAndView("neighbour");
        modelAndView.addObject("city", citiesService.getCity(id));
        modelAndView.addObject("neighbours", citiesService.getNeighbours(citiesService.getCity(id)));
        return modelAndView;
    }


    @GetMapping("/route")
    public ModelAndView getRoute() {
        ModelAndView modelAndView = new ModelAndView("final");
        modelAndView.addObject("route", citiesService.getRoute());
        return modelAndView;
    }



    @GetMapping("/route/back")
    public ModelAndView goBackOneCity(ModelMap model) {
        citiesService.deleteLastCity();
        model.addAttribute("id",citiesService.getRoute().get(citiesService.getRoute().size() - 1).getCity_id());
        return new ModelAndView("redirect:/city/{id}", model);
    }


    @Autowired
    public GreetingController(CitiesService citiesService) {
        this.citiesService = citiesService;
    }
}
