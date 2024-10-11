package fr.diginamic.hello.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CityDto {
    private long nbInhabitants;
    private String deptCode;
    private String deptName;
    private String cityCode;
}
