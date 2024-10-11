package fr.diginamic.hello.services;

import fr.diginamic.hello.dao.DepartementDao;
import fr.diginamic.hello.exceptions.CityNotFoundException;
import fr.diginamic.hello.exceptions.DepartementNotFoundException;
import fr.diginamic.hello.exceptions.FunctionalException;
import fr.diginamic.hello.model.City;
import fr.diginamic.hello.model.Departement;
import fr.diginamic.hello.repository.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartementService {

    @Autowired
    private DepartementDao departementDao;

    @Autowired
    private DepartementRepository departementRepository;

    public List<Departement> getAll() {
        return this.departementRepository.findAll();
    }

    public Departement findById(long id) {
        return this.departementRepository.findById(id).orElseThrow(() -> new DepartementNotFoundException("Departement not found"));
    }

    public ResponseEntity<?> delete(Departement dep) {
        this.departementRepository.delete(dep);
        return ResponseEntity.ok().build();
    }

    public Departement update(Departement dep) {
        return this.departementRepository.save(dep);
    }

    public Departement create(Departement dep) throws FunctionalException {
        if (dep.getCode().length() < 2 && dep.getName().length() > 3) {
            throw new FunctionalException("Departement code must be 2 or 3 characters");
        }
        if (dep.getName() == null || dep.getName().length() > 2) {
            throw new FunctionalException("Departement name must be superior than 2 characters");
        }

        return this.departementRepository.save(dep);
    }

    public List<City> getLargestCities(long id, int limit){
        return this.departementRepository.findLargestCitiesByDepartement(id, limit);
    }

    public List<City> getCitiesPopulationBetweenLimit(int id, int min, int max){
        return this.departementDao.getCitiesPopulationBetweenLimit(id, min, max);
    }

    public Departement findByCode(String code) {
        return this.departementRepository.findByCode(code).orElseThrow(() -> new DepartementNotFoundException("Not found"));
    }

    public boolean cityAlreadyExists(City city, Departement dept) {
        return city.getDepartement().getCities().stream().anyMatch(c -> c.getName().equals(city.getName()));
    }

}
