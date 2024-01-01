package cz.cvut.fit.tjv.fuelapp.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GasStationWithPriceDTO {
    private String gasStationName;
    private String fuelName;
    private Float price;
}