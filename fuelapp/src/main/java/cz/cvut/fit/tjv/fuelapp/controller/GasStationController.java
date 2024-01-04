package cz.cvut.fit.tjv.fuelapp.controller;

import cz.cvut.fit.tjv.fuelapp.application.GasStationServiceInterface;
import cz.cvut.fit.tjv.fuelapp.application.RecordServiceInterface;
import cz.cvut.fit.tjv.fuelapp.controller.converter.DTOConverter;
import cz.cvut.fit.tjv.fuelapp.controller.dto.FuelDTO;
import cz.cvut.fit.tjv.fuelapp.controller.dto.GasStationDTO;
import cz.cvut.fit.tjv.fuelapp.controller.dto.GasStationWithPriceDTO;
import cz.cvut.fit.tjv.fuelapp.controller.dto.RecordDTO;
import cz.cvut.fit.tjv.fuelapp.domain.Fuel;
import cz.cvut.fit.tjv.fuelapp.domain.GasStation;
import cz.cvut.fit.tjv.fuelapp.domain.Record;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/rest/api/gasStation")
public class GasStationController {

    private final GasStationServiceInterface gasStationService;
    private final RecordServiceInterface recordService;
    private final DTOConverter<FuelDTO, Fuel> fuelDTOConverter;
    private final DTOConverter<GasStationDTO, GasStation> gasStationDTOConverter;
    private final DTOConverter<RecordDTO, Record> recordDTOConverter;

    public GasStationController(GasStationServiceInterface gasStationService, RecordServiceInterface recordService, DTOConverter<FuelDTO, Fuel> fuelDTOConverter, DTOConverter<GasStationDTO, GasStation> gasStationDTOConverter, DTOConverter<RecordDTO, Record> recordDTOConverter) {
        this.gasStationService = gasStationService;
        this.recordService = recordService;
        this.fuelDTOConverter = fuelDTOConverter;
        this.gasStationDTOConverter = gasStationDTOConverter;
        this.recordDTOConverter = recordDTOConverter;
    }

    @GetMapping("/gasStations")
    public List<GasStationDTO> getGasStations() {
        return gasStationService.getGasStations().stream().map(gasStationDTOConverter::toDTO).toList();
    }

    @GetMapping(path = "{id}")
    public GasStationDTO getGasStation(@PathVariable("id") Long id) {
        return gasStationDTOConverter.toDTO(gasStationService.getGasStationById(id));
    }

    @PostMapping
    public GasStationDTO createGasStation(@RequestBody GasStationDTO gasStationDTO) {
        gasStationService.createGasStation(gasStationDTOConverter.toEntity(gasStationDTO));
        return gasStationDTO;
    }

    @PutMapping(path = "{id}")
    public GasStationDTO updateGasStation(@PathVariable Long id, @RequestBody GasStationDTO gasStationDTO) {
        GasStation entity = gasStationDTOConverter.toEntity(gasStationDTO);
        entity.setId(id);
        return gasStationDTOConverter.toDTO(gasStationService.updateGasStation(entity));
    }

    @DeleteMapping(path = "{id}")
    public void deleteGasStation(@PathVariable Long id) {
        gasStationService.deleteGasStation(id);
    }

    @GetMapping(path = "{id}/records")
    public List<RecordDTO> getRecordsFromGasStation(@PathVariable("id") Long id){
        return gasStationService.getGasStationById(id).getRecords().stream().map(recordDTOConverter::toDTO).toList();
    }
    @PostMapping(path = "{id}/records")
    public List<RecordDTO> addRecordToGasStation(@PathVariable("id") Long gasStationId, @RequestBody Long recordId){
        recordService.addRecordToAppuser(gasStationId, recordId);
        return gasStationService.getGasStationById(gasStationId).getRecords().stream().map(recordDTOConverter::toDTO).toList();
    }
    @DeleteMapping(path = "{gasStationId}/records/{recordId}")
    public List<RecordDTO> removeRecordFromGasStation(@PathVariable("gasStationId") Long gasStationId, @PathVariable("recordId") Long recordId){
        recordService.removeRecordFromAppuser(gasStationId, recordId);
        return gasStationService.getGasStationById(gasStationId).getRecords().stream().map(recordDTOConverter::toDTO).toList();
    }

    @GetMapping(path = "{id}/fuels")
    public List<FuelDTO> getFuelsSold(@PathVariable("id") Long id){
        return gasStationService.getGasStationById(id).getFuelsSold().stream().map(fuelDTOConverter::toDTO).toList();
    }
    @PostMapping(path = "{id}/fuels")
    public List<FuelDTO> addFuelSold(@PathVariable("id") Long gasStationId, @RequestBody Long fuelId){
        gasStationService.addFuelToGasStation(gasStationId, fuelId);
        return gasStationService.getGasStationById(gasStationId).getFuelsSold().stream().map(fuelDTOConverter::toDTO).toList();
    }
    @DeleteMapping(path = "{gasStationId}/fuels/{fuelId}")
    public List<FuelDTO> removeFuelSold(@PathVariable("gasStationId") Long gasStationId, @PathVariable("fuelId") Long fuelId){
        gasStationService.removeFuelFromGasStation(gasStationId, fuelId);
        return gasStationService.getGasStationById(gasStationId).getFuelsSold().stream().map(fuelDTOConverter::toDTO).toList();
    }
    @Operation(summary = "Gets gas stations wtih the cheapest fuel by Date and Location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found gas stations")
    })
    @GetMapping(path = "/gasStationsByCrit")
    public List<GasStationWithPriceDTO> getGasStationsByCriteria(@RequestParam ("startD") Date startDate, @RequestParam ("endD") Date endDate, @RequestParam ("city") String city){
        return gasStationService.getGasStationsByCriteria(startDate, endDate, city)
                .stream()
                .map(gs -> new GasStationWithPriceDTO(gs.getGasStationName(), gs.getFuelName(), gs.getPrice()))
                .toList();
    }
}
