package fr.diginamic.hello.services;

import fr.diginamic.hello.model.City;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityService {

    public List<City> cities = new ArrayList<>(List.of(
            new City(1, "Avignon", 200000),
            new City(2, "Montpellier", 400000)));

    public City findById(int id) {
        return this.cities.stream().filter(city1 -> city1.getId() == id).findFirst().orElse(null);
    }

    public ResponseEntity<?> delete(City city) {
        this.cities.removeIf(c -> c.getId() == city.getId());
        return ResponseEntity.accepted().body("City deleted successfully");
    }

}
