package com.example.wifidemo.service;

import com.example.wifidemo.model.Category;
import com.example.wifidemo.model.City;
import com.example.wifidemo.repository.CategoryRepository;
import com.example.wifidemo.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityandCategoryService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<City> findAllCities() {

        return cityRepository.findAll();
    }

    public City findCityByName(String name) {
        return cityRepository.findByName(name);
    }

    public List<Category> findAllCategories() {

        return categoryRepository.findAll();
    }

    public Category findCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }




}
