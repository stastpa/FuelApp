package cz.cvut.fit.tjv.fuelapp.controler.dto;

import cz.cvut.fit.tjv.fuelapp.domain.Fuel;
import cz.cvut.fit.tjv.fuelapp.domain.Record;

import java.util.List;

public class GasStationDTO {
    private Long id;
    private String name;
    private String country;
    private Integer psc;
    private String city;
    private String street;
    private String number;
    private String phoneNumber;
    List<Fuel> fuelsSold;
    List<Record> records;
}
