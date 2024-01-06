package cz.cvut.fit.tjv.client.model;

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
    private List<Long> fuelIds;
    private List<Long> recordIds;
}
