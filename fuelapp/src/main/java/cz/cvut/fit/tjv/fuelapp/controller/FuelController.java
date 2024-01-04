package cz.cvut.fit.tjv.fuelapp.controller;


import cz.cvut.fit.tjv.fuelapp.application.*;
import cz.cvut.fit.tjv.fuelapp.controller.converter.DTOConverter;
import cz.cvut.fit.tjv.fuelapp.controller.dto.FuelDTO;
import cz.cvut.fit.tjv.fuelapp.controller.dto.GasStationDTO;
import cz.cvut.fit.tjv.fuelapp.controller.dto.RecordDTO;
import cz.cvut.fit.tjv.fuelapp.domain.Fuel;
import cz.cvut.fit.tjv.fuelapp.domain.GasStation;
import cz.cvut.fit.tjv.fuelapp.domain.Record;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/api/fuel")
public class FuelController {

    private final FuelServiceInterface fuelService;
    private final GasStationServiceInterface gasStationService;
    private final RecordServiceInterface recordService;
    private final DTOConverter<FuelDTO, Fuel> fuelDTOConverter;
    private final DTOConverter<GasStationDTO, GasStation> gasStationDTOConverter;
    private final DTOConverter<RecordDTO, Record> recordDTOConverter;

    public FuelController(FuelServiceInterface fuelService, GasStationServiceInterface gasStationService, RecordServiceInterface recordService, DTOConverter<FuelDTO, Fuel> fuelDTOConverter, DTOConverter<GasStationDTO, GasStation> gasStationDTOConverter, DTOConverter<RecordDTO, Record> recordDTOConverter) {
        this.fuelService = fuelService;
        this.gasStationService = gasStationService;
        this.recordService = recordService;
        this.fuelDTOConverter = fuelDTOConverter;
        this.gasStationDTOConverter = gasStationDTOConverter;
        this.recordDTOConverter = recordDTOConverter;
    }

    @GetMapping("/fuels")
    public List<FuelDTO> getFuels() {
        return fuelService.getFuels().stream().map(fuelDTOConverter::toDTO).toList();
    }

    @GetMapping(path = "{id}")
    public FuelDTO getFuel(@PathVariable("id") Long id) {
        return fuelDTOConverter.toDTO(fuelService.getFuelById(id));
    }

    @PostMapping
    public FuelDTO createFuel(@RequestBody FuelDTO fuelDTO) {
        fuelService.createFuel(fuelDTOConverter.toEntity(fuelDTO));
        return fuelDTO;
    }

    @PutMapping(path = "{id}")
    public FuelDTO updateFuel(@PathVariable Long id, @RequestBody FuelDTO fuelDTO) {
        Fuel entity = fuelDTOConverter.toEntity(fuelDTO);
        entity.setId(id);
        return fuelDTOConverter.toDTO(fuelService.updateFuel(entity));
    }

    @DeleteMapping(path = "{id}")
    public void deleteFuel(@PathVariable Long id) {
        fuelService.deleteFuel(id);
    }

    @GetMapping(path = "{id}/records")
    public List<RecordDTO> getRecordsFromFuel(@PathVariable("id") Long id){
        return fuelService.getFuelById(id).getFuelRecords().stream().map(recordDTOConverter::toDTO).toList();
    }
    @PostMapping(path = "{id}/records")
    public List<RecordDTO> addRecordToFuel(@PathVariable("id") Long fuelId, @RequestBody Long recordId){
        recordService.addRecordToAppuser(fuelId, recordId);
        return fuelService.getFuelById(fuelId).getFuelRecords().stream().map(recordDTOConverter::toDTO).toList();
    }
    @DeleteMapping(path = "{fuelId}/records/{recordId}")
    public List<RecordDTO> removeRecordFromFuel(@PathVariable("fuelId") Long fuelId, @PathVariable("recordId") Long recordId){
        recordService.removeRecordFromAppuser(fuelId, recordId);
        return fuelService.getFuelById(fuelId).getFuelRecords().stream().map(recordDTOConverter::toDTO).toList();
    }

    @GetMapping(path = "{id}/gasStations")
    public List<GasStationDTO> getGasStationsOfFuel(@PathVariable("id") Long id){
        return fuelService.getFuelById(id).getSoldAt().stream().map(gasStationDTOConverter::toDTO).toList();
    }
    @PostMapping(path = "{id}/gasStations")
    public List<GasStationDTO> addGasStationToFuel(@PathVariable("id") Long gasStationId, @RequestBody Long recordId){
        gasStationService.addFuelToGasStation(gasStationId, recordId);
        return fuelService.getFuelById(gasStationId).getSoldAt().stream().map(gasStationDTOConverter::toDTO).toList();
    }
    @DeleteMapping(path = "{fuelId}/gasStations/{gasStationId}")
    public List<GasStationDTO> removeGasStationFromFuel(@PathVariable("gasStationId") Long gasStationId, @PathVariable("fuelId") Long fuelId){
        gasStationService.removeFuelFromGasStation(fuelId, gasStationId);
        return fuelService.getFuelById(fuelId).getSoldAt().stream().map(gasStationDTOConverter::toDTO).toList();
    }
}
