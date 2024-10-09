package fr.diginamic.hello.services;

import fr.diginamic.hello.dao.DepartementDao;
import fr.diginamic.hello.model.Departement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartementService {

    @Autowired
    private DepartementDao departementDao;

    public List<Departement> getAll() {
        return this.departementDao.findAll();
    }

    public Departement findById(int id) {
        return this.departementDao.findById(id);
    }

    public ResponseEntity<?> delete(Departement dep) {
        this.departementDao.delete(dep);
        return ResponseEntity.ok().build();
    }

    public Departement update(Departement dep) {
        return this.departementDao.update(dep);
    }

    public Departement create(Departement dep) {
        return this.departementDao.create(dep);
    }
}
