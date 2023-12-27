package cz.cvut.fit.tjv.fuelapp.controler.converter;

import cz.cvut.fit.tjv.fuelapp.application.GasStationServiceInterface;
import cz.cvut.fit.tjv.fuelapp.application.RecordServiceInterface;
import cz.cvut.fit.tjv.fuelapp.controler.dto.FuelDTO;
import cz.cvut.fit.tjv.fuelapp.domain.Fuel;
import cz.cvut.fit.tjv.fuelapp.domain.GasStation;
import cz.cvut.fit.tjv.fuelapp.domain.Record;

public class FuelDTOConverter implements DTOConverter<FuelDTO, Fuel>{

    private final GasStationServiceInterface gasStationService;
    private final RecordServiceInterface recordService;

    public FuelDTOConverter(GasStationServiceInterface gasStationService, RecordServiceInterface recordService) {
        this.gasStationService = gasStationService;
        this.recordService = recordService;
    }


    @Override
    public FuelDTO toDTO(Fuel e) {
        return new FuelDTO(
                e.getId(),
                e.getName(),
                e.getSoldAt().stream().map(GasStation::getId).toList(),
                e.getFuelRecords().stream().map(Record::getId).toList()
        );
    }

    @Override
    public Fuel toEntity(FuelDTO fuelDTO) {
        return new Fuel(
                fuelDTO.getId(),
                fuelDTO.getName(),
                fuelDTO.getGasStationIds().stream().map(gasStationService::getGasStationById).toList(),
                fuelDTO.getRecordIds().stream().map(recordService::getRecordById).toList()
        );
    }
}
