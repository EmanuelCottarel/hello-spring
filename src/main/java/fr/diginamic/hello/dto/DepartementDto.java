package fr.diginamic.hello.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartementDto {
    private String code;
    private String name;
    private long nbInhabitants;
}
