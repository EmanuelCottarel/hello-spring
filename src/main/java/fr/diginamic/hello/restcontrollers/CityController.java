package fr.diginamic.hello.restcontrollers;

import fr.diginamic.hello.dto.CityDto;
import fr.diginamic.hello.exceptions.CityNotFoundException;
import fr.diginamic.hello.exceptions.FunctionalException;
import fr.diginamic.hello.mapper.CityMapper;
import fr.diginamic.hello.model.City;
import fr.diginamic.hello.services.CityService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @Autowired
    CityMapper cityMapper;

    @GetMapping
    public Page<CityDto> getCities(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return this.cityService.findAllPageable(pageable);
    }

    @GetMapping("/findall")
    public List<CityDto> findAll(){
        return this.cityService.findAll().stream().map(cityMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityDto> getCity(@PathVariable int id) {
        try {
            City city = cityService.findById(id);
            return new ResponseEntity<>(this.cityMapper.toDto(city), HttpStatus.OK);
        } catch (CityNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/findByName")
    public ResponseEntity<CityDto> findByName(@RequestParam String name) {
        City city = this.cityService.findByName(name);
        return new ResponseEntity<>(this.cityMapper.toDto(city), HttpStatus.OK);
    }

    @PostMapping
    public City create(@Valid @RequestBody City city) throws FunctionalException {
        return this.cityService.create(city);
    }

    @PutMapping
    public City update(
            @Valid @RequestBody City city) throws FunctionalException {
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

    @GetMapping("/findby-name-starting-with")
    public List<CityDto> findByNameStartingWith(@RequestParam String prefix) throws FunctionalException {
        List<City> cities = this.cityService.findByNameStartingWith(prefix);

        return cities.stream()
                .map(cityMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/findby-nb-inhabitants-after")
    public List<CityDto> findByNbInhabitantsAfter(@RequestParam long min) throws FunctionalException {
        List<City> cities = this.cityService.findByNbInhabitantsAfter(min);
        return cities.stream()
                .map(cityMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/findby-nb-inhabitants-between")
    public List<CityDto> findByNbInhabitantsBetween(
            @RequestParam long min,
            @RequestParam long max
    ) throws FunctionalException {

        List<City> cities = this.cityService.findByNbInhabitantsBetween(min, max);
        return cities.stream()
                .map(cityMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/findby-departement_id-and-nb-inhabitants-after")
    public List<CityDto> findByDepartement_IdAndNbInhabitantsAfter(
            @RequestParam String codeDept,
            @RequestParam long min
    ) throws FunctionalException {

        List<City> cities = this.cityService.findByDepartement_IdAndNbInhabitantsAfter(codeDept, min);
        return cities.stream()
                .map(cityMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/findby-departement-and-nbinhabitants-between")
    public List<CityDto> findByDepartement_IdAndNbInhabitantsBetween(
            @RequestParam String codeDept,
            @RequestParam long min,
            @RequestParam long max
    ) throws FunctionalException {

        List<City> cities = this.cityService.findByDepartementAndNbInhabitantsBetween(codeDept, min, max);
        return cities.stream()
                .map(cityMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/find-departement-biggest-cities")
    public Page<CityDto> findDepartementBiggestCities(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam String codeDept
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return this.cityService.findDepartementBiggestCities(pageable, codeDept);
    }

    @GetMapping("/getcsv")
    public void getCitiesCSV(
            HttpServletResponse response,
            @RequestParam long minInhabitants
    ) throws IOException, FunctionalException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment;filename=cities.csv");
        List<City> cities = this.cityService.findByNbInhabitantsAfter(minInhabitants);
        PrintWriter writer = response.getWriter();
        writer.println("name,population,department_code, department_name");
        for (City city : cities) {
            writer.printf("%s,%d,%s%n",
                    city.getName(),
                    city.getNbInhabitants(),
                    city.getDepartement().getCode());
        }

        writer.flush();
        writer.close();
    }




}
