package com.avangarde.citytravel.api.controller;


import com.avangarde.citytravel.api.entities.City;
import com.avangarde.citytravel.api.service.CitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GreetingController {
    private final CitiesService citiesService;


    @RequestMapping("/mav/")
    public ModelAndView mav_home(HttpServletRequest request) {
        request.getSession().setAttribute("route", new ArrayList<City>());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("cities", this.citiesService.getAllCities());
        modelAndView.setViewName("travel");
        return modelAndView;
    }

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("cities", citiesService.getAllCities());
        return "travel";
    }

    @GetMapping("/mav/city/{id}")
    public ModelAndView mav_getCity(@PathVariable int id, HttpServletRequest request) {
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

    @GetMapping("/city/{name}")
    public String getCity(@PathVariable String name, Model model) {
        citiesService.addCityToRoute(name);
        model.addAttribute("city", citiesService.getCityByName(name));
        model.addAttribute("neighbours", citiesService.getNeighbours(citiesService.getCityByName(name)));
        return "neighbour";
    }


    @GetMapping("/mav/route")
    public ModelAndView mav_getRoute() {
        ModelAndView modelAndView = new ModelAndView("final");
        modelAndView.addObject("route", citiesService.getRoute());
        return modelAndView;
    }

    @GetMapping("/mav/route/back")
    public ModelAndView mav_goBackOneCity(ModelMap model, HttpServletRequest request) {
        List<City> route = (List<City>) request.getSession().getAttribute("route");
        route.remove(route.size() - 1);

        City city = route.get(route.size() - 1);

        model.addAttribute("id", city.getCity_id());
        return new ModelAndView("redirect:/city/{id}", model);
    }

    @GetMapping("route")
    public String getRoute(Model model) {
        model.addAttribute("route", citiesService.getRoute());
        return "final";
    }

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
