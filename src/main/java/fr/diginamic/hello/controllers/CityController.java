package fr.diginamic.hello.controllers;

import fr.diginamic.hello.model.City;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    @GetMapping
    public List<City> getCities(){
        List<City> cities = new ArrayList<>();
        cities.add(new City("Avignon", 200000));
        cities.add(new City("Montpellier", 400000));

        return cities;
    }

}
