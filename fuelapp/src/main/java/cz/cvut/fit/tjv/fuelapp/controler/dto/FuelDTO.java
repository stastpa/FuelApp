package cz.cvut.fit.tjv.fuelapp.controler.dto;

import cz.cvut.fit.tjv.fuelapp.domain.GasStation;
import cz.cvut.fit.tjv.fuelapp.domain.Record;

import java.util.List;

public class FuelDTO {
    private Long id;
    private String name;
    List<GasStation> soldAt;
    List<Record> fuelRecords;
}
