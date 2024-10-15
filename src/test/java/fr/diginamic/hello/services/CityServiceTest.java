package fr.diginamic.hello.services;

import fr.diginamic.hello.model.City;
import fr.diginamic.hello.repository.CityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class CityServiceTest {

    @Mock
    private CityRepository cityRepository;

    private City city;

    @BeforeEach
    void setup(){
        city = new City();
        city.setId(-1l);
        city.setName("Montpellier");
    }

    @Test
    void findById() {
        Mockito.when(cityRepository.findById(-1l)).thenReturn(Optional.of(city));
        Optional<City> result = cityRepository.findById(-1l);
        assertTrue(result.isPresent());
    }

    @Test
    void update() {
    }

    @Test
    void create() {
    }

    @Test
    void findByNameStartingWith() {
    }
}