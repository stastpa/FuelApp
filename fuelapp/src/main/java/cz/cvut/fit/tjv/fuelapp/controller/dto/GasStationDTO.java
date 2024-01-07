package cz.cvut.fit.tjv.fuelapp.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GasStationDTO {
    private final Long id;
    private final String name;
    private final String country;
    private final Integer psc;
    private final String city;
    private final String street;
    private final String number;
    private final String phoneNumber;
    private final List<Long> fuelIds;
    private final List<Long> recordIds;
}
