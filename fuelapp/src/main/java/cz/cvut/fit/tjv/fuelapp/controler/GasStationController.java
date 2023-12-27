package cz.cvut.fit.tjv.fuelapp.controler;

import cz.cvut.fit.tjv.fuelapp.application.AppUserServiceInterface;
import cz.cvut.fit.tjv.fuelapp.application.FuelServiceInterface;
import cz.cvut.fit.tjv.fuelapp.application.GasStationServiceInterface;
import cz.cvut.fit.tjv.fuelapp.application.RecordServiceInterface;
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
@RequestMapping("/rest/api/record")
public class GasStationController {
    private final AppUserServiceInterface appUserService;
    private final FuelServiceInterface fuelService;
    private final GasStationServiceInterface gasStationService;
    private final RecordServiceInterface recordService;
    private final DTOConverter<AppUserDTO, AppUser> appUserDTOConverter;
    private final DTOConverter<FuelDTO, Fuel> fuelDTOConverter;
    private final DTOConverter<GasStationDTO, GasStation> gasStationDTOConverter;
    private final DTOConverter<RecordDTO, Record> recordDTOConverter;

    public GasStationController(AppUserServiceInterface appUserService, FuelServiceInterface fuelService, GasStationServiceInterface gasStationService, RecordServiceInterface recordService, DTOConverter<AppUserDTO, AppUser> appUserDTOConverter, DTOConverter<FuelDTO, Fuel> fuelDTOConverter, DTOConverter<GasStationDTO, GasStation> gasStationDTOConverter, DTOConverter<RecordDTO, Record> recordDTOConverter) {
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
    public List<GasStationDTO> getGasStations() {
        return gasStationService.getGasStations().stream().map(gasStationDTOConverter::toDTO).toList();
    }

    @GetMapping(path = "{id}")
    public GasStationDTO getGasStation(@PathVariable("id") Long id) {
        return gasStationDTOConverter.toDTO(gasStationService.getGasStationById(id));
    }

    @PostMapping
    public GasStationDTO createGasStation(@RequestBody GasStationDTO gasStationDTO) {
        return gasStationDTOConverter.toDTO(gasStationService.createGasStation(gasStationDTOConverter.toEntity(gasStationDTO)));
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

}
