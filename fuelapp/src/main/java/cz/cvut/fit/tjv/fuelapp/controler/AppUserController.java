package cz.cvut.fit.tjv.fuelapp.controler;


import cz.cvut.fit.tjv.fuelapp.application.*;
import cz.cvut.fit.tjv.fuelapp.controler.converter.DTOConverter;
import cz.cvut.fit.tjv.fuelapp.controler.converter.RecordDTOConverter;
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
public class AppUserController {

    private final AppUserServiceInterface appUserService;
    private final FuelServiceInterface fuelService;
    private final GasStationServiceInterface gasStationService;
    private final RecordServiceInterface recordService;
    private final DTOConverter<AppUserDTO, AppUser> appUserDTOConverter;
    private final DTOConverter<FuelDTO, Fuel> fuelDTOConverter;
    private final DTOConverter<GasStationDTO,GasStation> gasStationDTOConverter;
    private final DTOConverter<RecordDTO, Record> recordDTOConverter;

    public AppUserController(AppUserServiceInterface appUserService, FuelServiceInterface fuelService, GasStationServiceInterface gasStationService, RecordServiceInterface recordService, DTOConverter<AppUserDTO, AppUser> appUserDTOConverter, DTOConverter<FuelDTO, Fuel> fuelDTOConverter, DTOConverter<GasStationDTO, GasStation> gasStationDTOConverter, DTOConverter<RecordDTO, Record> recordDTOConverter) {
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
    public List<AppUserDTO> getAppUsers(){

        return appUserService.getAppUsers().stream().map(appUserDTOConverter::toDTO).toList();
    }

    @GetMapping(path = "{id}")
    public AppUserDTO getAppUser(@PathVariable("id") Long id)
    {
        return appUserDTOConverter.toDTO(appUserService.getAppUserById(id));
    }

    @PostMapping
    public AppUserDTO createAppUser(@RequestBody AppUserDTO au)
    {
        return appUserDTOConverter.toDTO(appUserService.createAppUser(appUserDTOConverter.toEntity(au)));
    }

    @PutMapping(path = "{id}")
    public AppUserDTO updateAppUser(@PathVariable Long id, @RequestBody AppUserDTO au)
    {
        AppUser entity = appUserDTOConverter.toEntity(au);
        entity.setId(id);
        return appUserDTOConverter.toDTO(appUserService.updateAppUser(entity));
    }

    @DeleteMapping(path = "{id}")
    public void deleteAppUser(@PathVariable Long id)
    {
        appUserService.deleteAppUser(id);
    }
    @GetMapping(path = "{id}/records")
    public List<RecordDTO> getRecordsFromUser(@PathVariable("id") Long id){
        return appUserService.getAppUserById(id).getFuelRecords().stream().map(recordDTOConverter::toDTO).toList();
    }
    @PostMapping(path = "{id}/records")
    public List<RecordDTO> addRecordToUser(@PathVariable("id") Long appUserId, @RequestBody Long recordId){
        recordService.addRecordToAppuser(appUserId, recordId);
        return appUserService.getAppUserById(appUserId).getFuelRecords().stream().map(recordDTOConverter::toDTO).toList();
    }
    @DeleteMapping(path = "{appUserId}/records/{recordId}")
    public List<RecordDTO> removeRecordFromUser(@PathVariable("appUserId") Long appUserId, @PathVariable("recordId") Long recordId){
        recordService.removeRecordFromAppuser(appUserId, recordId);
        return appUserService.getAppUserById(appUserId).getFuelRecords().stream().map(recordDTOConverter::toDTO).toList();
    }
}
