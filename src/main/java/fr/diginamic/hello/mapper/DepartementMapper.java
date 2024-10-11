package fr.diginamic.hello.mapper;

import fr.diginamic.hello.dto.DepartementDto;
import fr.diginamic.hello.model.City;
import fr.diginamic.hello.model.Departement;
import org.springframework.stereotype.Component;

@Component
public class DepartementMapper {
    public DepartementDto toDto(Departement departement) {
        DepartementDto departementDto = new DepartementDto();
        departementDto.setCode(departement.getCode());
        departementDto.setName(departement.getName());
        departementDto.setNbInhabitants(this.getDepartementPopulation(departement));
        return departementDto;
    }

    public Departement toEntity(DepartementDto departementDto) {
        Departement departement = new Departement();
        departement.setCode(departementDto.getCode());
        departement.setName(departementDto.getName());
        return departement;
    }

    private long getDepartementPopulation(Departement departement){
        long departementPopulation = 0;
        if(departement != null){
            departementPopulation = departement.getCities().stream().mapToLong(City::getNbInhabitants).sum();
        }
        return departementPopulation;
    }
}
