package com.avangarde.citytravel.api.controller;


import com.avangarde.citytravel.api.service.CitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

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

    @GetMapping("/city")
    public String getCity(@RequestParam int id, HttpServletRequest request, Map<String, Object> model) {

        String cityName = request.getParameter("id");

        if (!(citiesService.getRoute().isEmpty())) {
            if ((id == citiesService.getRoute().get(citiesService.getRoute().size() - 1).getCity_id())) {
                citiesService.deleteLastCity();
            }
        }
        citiesService.addCityToRoute(id);

        model.put("city", this.citiesService.getCity(id));
        model.put("neighbours", this.citiesService.getNeighbours(citiesService.getCity(id)));
        return "neighbour";
    }

    @GetMapping("/route")
    public ModelAndView getRoute() {
        ModelAndView modelAndView = new ModelAndView("final");
        modelAndView.addObject("route", citiesService.getRoute());
        return modelAndView;
    }

    @GetMapping("/route/back")
    public String goBackOneCity(ModelMap model) {
        citiesService.deleteLastCity();
        model.addAttribute("id",
                citiesService.getRoute().get(citiesService.getRoute().size() - 1).getCity_id());

        return "redirect:/city?id=" + model.getAttribute("id");
    }

    @Autowired
    public GreetingController(CitiesService citiesService) {
        this.citiesService = citiesService;
    }
}
