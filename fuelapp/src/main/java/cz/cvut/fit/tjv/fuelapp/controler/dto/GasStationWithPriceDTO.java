package cz.cvut.fit.tjv.fuelapp.controler.dto;

import cz.cvut.fit.tjv.fuelapp.domain.GasStation;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GasStationWithPriceDTO {
    private GasStation gasStation;
    private String fuelName;
    private Float price;
}