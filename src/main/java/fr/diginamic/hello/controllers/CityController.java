package fr.diginamic.hello.controllers;

import fr.diginamic.hello.model.City;
import fr.diginamic.hello.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping
    public List<City> getCities(){
        return this.cityService.cities;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCity(@PathVariable int id) {
        City existingCity = this.cityService.cities.stream().filter(city -> city.getId() == id).findFirst().orElse(null);
        if (existingCity == null) {
            return ResponseEntity.badRequest().body("Ressource not found");
        }
        return ResponseEntity.accepted().body(existingCity);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody City city) {
        if (this.cityService.cities.stream().anyMatch(c -> c.getName().equals(city.getName()))){
            return ResponseEntity.badRequest().body("City already exists");
        }
        if (this.cityService.cities.stream().anyMatch(city1 -> city1.getId() == city.getId())){
            return ResponseEntity.badRequest().body("City already exists");
        }
        city.setId(this.cityService.cities.size() + 1);
        this.cityService.cities.add(city);
        return ResponseEntity.accepted().body(city);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable int id,
            @RequestBody City city) {

        City existingCity = this.cityService.cities.stream().filter(city1 -> city1.getId() == id).findFirst().orElse(null);
        if (existingCity != null) {
            existingCity.setName(city.getName());
            existingCity.setNbInhabitants(city.getNbInhabitants());
        }

        return ResponseEntity.accepted().body(city);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        City existingCity = this.cityService.findById(id);
        if (existingCity == null) {
            return ResponseEntity.badRequest().body("Ressource not found");
        }
        return this.cityService.delete(existingCity);
    }

}
