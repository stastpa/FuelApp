package cz.cvut.fit.tjv.fuelapp.controler;


import cz.cvut.fit.tjv.fuelapp.application.*;
import cz.cvut.fit.tjv.fuelapp.controler.converter.DTOConverter;
import cz.cvut.fit.tjv.fuelapp.controler.dto.AppUserDTO;
import cz.cvut.fit.tjv.fuelapp.controler.dto.FuelDTO;
import cz.cvut.fit.tjv.fuelapp.controler.dto.GasStationDTO;
import cz.cvut.fit.tjv.fuelapp.controler.dto.RecordDTO;
import cz.cvut.fit.tjv.fuelapp.domain.AppUser;
import cz.cvut.fit.tjv.fuelapp.domain.Fuel;
import cz.cvut.fit.tjv.fuelapp.domain.GasStation;
import cz.cvut.fit.tjv.fuelapp.domain.Record;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/api/appUser")
public class FuelController {

    private final AppUserServiceInterface appUserService;
    private final FuelServiceInterface fuelService;
    private final GasStationServiceInterface gasStationService;
    private final RecordServiceInterface recordService;
    private final DTOConverter<AppUserDTO, AppUser> appUserDTOConverter;
    private final DTOConverter<FuelDTO, Fuel> fuelDTOConverter;
    private final DTOConverter<GasStationDTO, GasStation> gasStationDTOConverter;
    private final DTOConverter<RecordDTO, Record> recordDTOConverter;

    public FuelController(AppUserServiceInterface appUserService, FuelServiceInterface fuelService, GasStationServiceInterface gasStationService, RecordServiceInterface recordService, DTOConverter<AppUserDTO, AppUser> appUserDTOConverter, DTOConverter<FuelDTO, Fuel> fuelDTOConverter, DTOConverter<GasStationDTO, GasStation> gasStationDTOConverter, DTOConverter<RecordDTO, Record> recordDTOConverter) {
        this.appUserService = appUserService;
        this.fuelService = fuelService;
        this.gasStationService = gasStationService;
        this.recordService = recordService;
        this.appUserDTOConverter = appUserDTOConverter;
        this.fuelDTOConverter = fuelDTOConverter;
        this.gasStationDTOConverter = gasStationDTOConverter;
        this.recordDTOConverter = recordDTOConverter;
    }

    @GetMapping
    public List<FuelDTO> getFuels() {
        return fuelService.getFuels().stream().map(fuelDTOConverter::toDTO).toList();
    }

    @GetMapping(path = "{id}")
    public FuelDTO getFuel(@PathVariable("id") Long id) {
        return fuelDTOConverter.toDTO(fuelService.getFuelById(id));
    }

    @PostMapping
    public FuelDTO createFuel(@RequestBody FuelDTO fuelDTO) {
        return fuelDTOConverter.toDTO(fuelService.createFuel(fuelDTOConverter.toEntity(fuelDTO)));
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
}
