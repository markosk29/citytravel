package com.avangarde.citytravel.api.controller;

import com.avangarde.citytravel.api.output.CityJSON;
import com.avangarde.citytravel.api.service.CitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GreetingController {
    private final CitiesService citiesService;

    @Autowired
    public GreetingController(CitiesService citiesService) {
        this.citiesService = citiesService;
    }

    @RequestMapping("/")
    public String home(Model model, HttpServletRequest request) {
        request.getSession().setAttribute("route", new ArrayList<CityJSON>());
        model.addAttribute("cities", citiesService.getAllCities());
        return "travel";
    }

    @GetMapping("/city")
    public String getCity(@RequestParam String name, Model model, HttpServletRequest request) {
        List<CityJSON> route = (List<CityJSON>) request.getSession().getAttribute("route");

        if (!(route.isEmpty())) {
            if ((name.equals(route.get(route.size() - 1).getName()))) {
                route.remove(route.size() - 1);
            }
        }

        route.add(citiesService.getCityByName(name));

        model.addAttribute("city", citiesService.getCityByName(name));
        model.addAttribute("neighbours", citiesService.getNeighbours(citiesService.getCityByName(name)));
        return "neighbour";
    }

    @GetMapping("route")
    public String getRoute(Model model, HttpServletRequest request) {
        List<CityJSON> route = (List<CityJSON>) request.getSession().getAttribute("route");
        model.addAttribute("route", route);
        return "final";
    }

    @GetMapping("/route/back")
    public RedirectView redirectWithRedirectAttributes(RedirectAttributes attributes,
                                                       HttpServletRequest request) {
        List<CityJSON> route = (List<CityJSON>) request.getSession().getAttribute("route");
        route.remove(route.size() - 1);
        attributes.addAttribute("name", route.get(route.size() - 1).getName());
        return new RedirectView("/city");
    }

    //Model and View methods

    @RequestMapping("/mav/")
    public ModelAndView mav_home(HttpServletRequest request) {
        request.getSession().setAttribute("route", new ArrayList<CityJSON>());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("cities", this.citiesService.getAllCities());
        modelAndView.setViewName("travel");
        return modelAndView;
    }

    @GetMapping("/mav/city")
    public ModelAndView mav_getCity(@RequestParam String name, HttpServletRequest request) {
        List<CityJSON> route = (List<CityJSON>) request.getSession().getAttribute("route");

        if (!(route.isEmpty())) {
            if ((name.equals(route.get(route.size() - 1).getName()))) {
                route.remove(route.size() - 1);
            }
        }

        route.add(citiesService.getCityByName(name));

        ModelAndView modelAndView = new ModelAndView("neighbour");
        modelAndView.addObject("city", citiesService.getCityByName(name));
        modelAndView.addObject("neighbours", citiesService
                .getNeighbours(citiesService
                        .getCityByName(name)));
        return modelAndView;
    }

    @GetMapping("/mav/route")
    public ModelAndView mav_getRoute(HttpServletRequest request) {
        List<CityJSON> route = (List<CityJSON>) request.getSession().getAttribute("route");
        ModelAndView modelAndView = new ModelAndView("final");
        modelAndView.addObject("route", route);
        return modelAndView;
    }

    @GetMapping("/mav/route/back")
    public ModelAndView mav_goBackOneCity(ModelMap model, HttpServletRequest request) {
        List<CityJSON> route = (List<CityJSON>) request.getSession().getAttribute("route");
        route.remove(route.size() - 1);

        CityJSON city = route.get(route.size() - 1);

        model.addAttribute("name", city.getName());
        return new ModelAndView("redirect:/city?name=" + city.getName(), model);
    }
}
