package com.example.wifidemo.controller;

import com.example.wifidemo.model.*;
import com.example.wifidemo.service.CityandCategoryService;
import com.example.wifidemo.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class WelcomeController {

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private CityandCategoryService cityandCategoryService;


    //give the city to the model
    @RequestMapping("/")
    public String findAll(Model model) {

        List<City> cities = cityandCategoryService.findAllCities();
        List<Category> categories = cityandCategoryService.findAllCategories();

        model.addAttribute("cities", cities);
        model.addAttribute("categories", categories);
        return "welcome";
    }

    //get the selected city and category from the view
    @RequestMapping("/select")
    public String select(@ModelAttribute("selected") Selected selected, Model model) throws Exception {

        int cityId = findCityIdByName(selected.getCity());
        int categoryId = findCategoryIdByName(selected.getCategory());

        List<Service> services = serviceService.findByCityIdAndCategoryId(cityId, categoryId);

        model.addAttribute("selected", selected);
        model.addAttribute("services", services);

        return "index";
    }
    //Get the city id from database
    public int findCityIdByName(String name) {
        City city = cityandCategoryService.findCityByName(name);
        return city.getId();
    }
    //get the category id from database
    public int findCategoryIdByName(String name) {
        Category category = cityandCategoryService.findCategoryByName(name);
        return category.getId();
    }


}
