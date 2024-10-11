package fr.diginamic.hello.repository;

import fr.diginamic.hello.model.City;
import fr.diginamic.hello.model.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DepartementRepository extends JpaRepository<Departement, Long> {
    Optional<Departement> findByCode(String code);

    @Query("SELECT c FROM City c WHERE c.departement.id = :departementId ORDER BY c.nbInhabitants DESC limit :limit")
    List<City> findLargestCitiesByDepartement(@Param("departementId") Long departementId, @Param("limit") Integer limit);;
}
