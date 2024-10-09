package fr.diginamic.hello.repository;

import fr.diginamic.hello.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {

}
