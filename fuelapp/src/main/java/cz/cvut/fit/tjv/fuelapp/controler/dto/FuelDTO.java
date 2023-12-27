package cz.cvut.fit.tjv.fuelapp.controler.dto;

import cz.cvut.fit.tjv.fuelapp.domain.GasStation;
import cz.cvut.fit.tjv.fuelapp.domain.Record;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class FuelDTO {
    private final Long id;
    private final String name;
    private final List<Long> gasStationIds;
    private final List<Long> recordIds;
}
