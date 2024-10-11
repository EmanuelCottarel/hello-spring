package fr.diginamic.hello.mapper;

import fr.diginamic.hello.model.City;
import fr.diginamic.hello.dto.CityDto;
import fr.diginamic.hello.services.DepartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CityMapper {

    @Autowired
    DepartementService departementService;

    public CityDto toDto(City city) {
        CityDto dto = new CityDto();
        dto.setNbInhabitants(city.getNbInhabitants());
        dto.setDeptCode(city.getDepartement().getCode());
        dto.setDeptName(city.getDepartement().getName());
        dto.setCityCode(city.getName());
        return dto;
    }

    public City toEntity(CityDto dto) {
        City city = new City();
        city.setNbInhabitants(dto.getNbInhabitants());
        city.setDepartement(departementService.findByCode(dto.getDeptCode()));

        return city;
    }
}
