package com.avangarde.citytravel.api.controller;


import com.avangarde.citytravel.api.entities.City;
import com.avangarde.citytravel.api.service.CitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GreetingController {
    private final CitiesService citiesService;

    @RequestMapping("/")
    public ModelAndView home(HttpServletRequest request) {
        request.getSession().setAttribute("route", new ArrayList<City>());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("cities", this.citiesService.getAllCities());
        modelAndView.setViewName("travel");
        return modelAndView;
    }

    @GetMapping("/city/{id}")
    public ModelAndView getCity(@PathVariable int id, HttpServletRequest request) {
        List<City> route = (List<City>) request.getSession().getAttribute("route");

        if (!(route.isEmpty())) {
            if ((id == route.get(route.size() - 1).getCity_id())) {
                route.remove(route.size() - 1);
            }
        }

        route.add(citiesService.getCity(id));

        ModelAndView modelAndView = new ModelAndView("neighbour");
        modelAndView.addObject("city", citiesService.getCity(id));
        modelAndView.addObject("neighbours", citiesService.getNeighbours(citiesService.getCity(id)));
        return modelAndView;
    }

    @GetMapping("/route")
    public ModelAndView getRoute(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("final");
        modelAndView.addObject("route", request.getSession().getAttribute("route"));
        return modelAndView;
    }

    @GetMapping("/route/back")
    public ModelAndView goBackOneCity(ModelMap model, HttpServletRequest request) {
        List<City> route = (List<City>) request.getSession().getAttribute("route");
        route.remove(route.size() - 1);

        City city = route.get(route.size() - 1);

        model.addAttribute("id", city.getCity_id());
        return new ModelAndView("redirect:/city/{id}", model);
    }

    @Autowired
    public GreetingController(CitiesService citiesService) {
        this.citiesService = citiesService;
    }
}
