package fr.diginamic.hello.services;

import fr.diginamic.hello.dao.CityDao;
import fr.diginamic.hello.model.City;
import fr.diginamic.hello.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CityDao cityDao;

    public List<City> getAll() {
//        return this.cityRepository.findAll();
        return this.cityDao.findAll();
    }

    public City findById(int id) {
        return this.cityDao.findById(id);
    }

    public City findByName(String name) {
        return this.cityDao.findByName(name);
    }

    public ResponseEntity<?> delete(City city) {
        this.cityDao.delete(city);
        return ResponseEntity.ok().build();
    }

    public City update(City city) {
        return this.cityDao.update(city);
    }

    public City create(City city) {
//      return this.cityRepository.save(city);
        return this.cityDao.create(city);
    }

}
