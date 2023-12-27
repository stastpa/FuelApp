package cz.cvut.fit.tjv.fuelapp.controler.converter;

import cz.cvut.fit.tjv.fuelapp.application.FuelServiceInterface;
import cz.cvut.fit.tjv.fuelapp.application.RecordServiceInterface;
import cz.cvut.fit.tjv.fuelapp.controler.dto.GasStationDTO;
import cz.cvut.fit.tjv.fuelapp.domain.Fuel;
import cz.cvut.fit.tjv.fuelapp.domain.GasStation;
import cz.cvut.fit.tjv.fuelapp.domain.Record;


public class GasStationDTOConverter implements DTOConverter<GasStationDTO, GasStation>{

    private final RecordServiceInterface recordService;
    private final FuelServiceInterface fuelService;

    public GasStationDTOConverter (RecordServiceInterface recordService, FuelServiceInterface fuelService)
    {
        this.fuelService = fuelService;
        this.recordService = recordService;
    }
    @Override
    public GasStationDTO toDTO(GasStation gs) {
        return new GasStationDTO(
                gs.getId(),
                gs.getName(),
                gs.getCountry(),
                gs.getPsc(),
                gs.getCity(),
                gs.getStreet(),
                gs.getNumber(),
                gs.getPhoneNumber(),
                gs.getFuelsSold().stream().map(Fuel::getId).toList(),
                gs.getRecords().stream().map(Record::getId).toList());
    }

    @Override
    public GasStation toEntity(GasStationDTO gasStationDTO) {
        return new GasStation(
                gasStationDTO.getId(),
                gasStationDTO.getName(),
                gasStationDTO.getCountry(),
                gasStationDTO.getPsc(),
                gasStationDTO.getCity(),
                gasStationDTO.getStreet(),
                gasStationDTO.getNumber(),
                gasStationDTO.getPhoneNumber(),
                gasStationDTO.getRecordIds().stream().map(recordService::getRecordById).toList(),
                gasStationDTO.getFuelIds().stream().map(fuelService::getFuelById).toList()
        );
    }
}
