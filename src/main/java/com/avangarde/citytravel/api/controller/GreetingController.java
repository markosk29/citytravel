package com.avangarde.citytravel.api.controller;


import com.avangarde.citytravel.api.service.CitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;


@Controller
public class GreetingController {
    private final CitiesService citiesService;


//    @RequestMapping("/")
//    public ModelAndView home() {
//        citiesService.getRoute().clear();
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("cities", this.citiesService.getAllCities());
//        modelAndView.setViewName("travel");
//        return modelAndView;
//    }

    //new
    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("cities", citiesService.getAllCities());
        return "travel";
    }

//    @GetMapping("city/{name}")
//    public ModelAndView getCity(@PathVariable String name, HttpServletRequest request) {
//        if (!(citiesService.getRoute().isEmpty())) {
//            if ((name.equals(citiesService.getRoute().get(citiesService.getRoute().size() - 1).getName()))) {
//                citiesService.deleteLastCity();
//            }
//        }
//        citiesService.addCityToRoute(name);
//        ModelAndView modelAndView = new ModelAndView("neighbour");
//        modelAndView.addObject("city", citiesService.getCityJSONByName(name));
//        modelAndView.addObject("neighbours", citiesService.getNeighbours(citiesService.getCityJSONByName(name)));
//        return modelAndView;
//    }


    //new
    @GetMapping("/city/{name}")
    public String getCity(@PathVariable String name, Model model) {
        citiesService.addCityToRoute(name);
        model.addAttribute("city", citiesService.getCityByName(name));
        model.addAttribute("neighbours", citiesService.getNeighbours(citiesService.getCityByName(name)));
        return "neighbour";
    }


//    @GetMapping("/route")
//    public ModelAndView getRoute(HttpServletRequest request) {
//        ModelAndView modelAndView = new ModelAndView("final");
//        modelAndView.addObject("route", citiesService.getRoute());
//        return modelAndView;
//    }


    //new
    @GetMapping("route")
    public String getRoute(Model model) {
        model.addAttribute("route", citiesService.getRoute());
        return "final";
    }


//    @GetMapping("/route/back")
//    public ModelAndView goBackOneCity(ModelMap model) {
//        citiesService.deleteLastCity();
//        model.addAttribute("name", citiesService.getRoute().get(citiesService.getRoute().size() - 1).getName());
//        return new ModelAndView("redirect:/city/{name}", model);
//    }


    //new
    @GetMapping("/route/back")
    public RedirectView redirectWithRedirectAttributes(RedirectAttributes attributes) {
        citiesService.deleteLastCity();
        attributes.addAttribute("name", citiesService.getRoute().get(citiesService.getRoute().size() - 2).getName());
        return new RedirectView("/city/{name}");
    }


    @Autowired
    public GreetingController(CitiesService citiesService) {
        this.citiesService = citiesService;
    }
}
