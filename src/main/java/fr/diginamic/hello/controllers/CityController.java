package fr.diginamic.hello.controllers;

import fr.diginamic.hello.model.City;
import fr.diginamic.hello.services.CityService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        return this.cityService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCity(@PathVariable int id) {
        City existingCity = this.cityService.findById(id);
        if (existingCity == null) {
            return ResponseEntity.badRequest().body("Ressource not found");
        }
        return new ResponseEntity<City>(existingCity, HttpStatus.OK);
    }

    @GetMapping("/findby")
    public ResponseEntity<?> findBy(@RequestParam String name) {
        City existingCity = this.cityService.findByName(name);
        if (existingCity == null) {
            return ResponseEntity.badRequest().body("Ressource not found");
        }
        return new ResponseEntity<City>(existingCity, HttpStatus.OK);
    }

    @PostMapping
    public City create(@Valid @RequestBody City city) {
         return this.cityService.create(city);
    }

    @PutMapping
    public City update(
            @Valid @RequestBody City city) {
        return this.cityService.update(city);
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
