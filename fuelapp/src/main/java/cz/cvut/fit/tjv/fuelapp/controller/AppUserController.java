package cz.cvut.fit.tjv.fuelapp.controller;


import cz.cvut.fit.tjv.fuelapp.application.*;
import cz.cvut.fit.tjv.fuelapp.controller.converter.DTOConverter;
import cz.cvut.fit.tjv.fuelapp.controller.dto.AppUserDTO;
import cz.cvut.fit.tjv.fuelapp.controller.dto.RecordDTO;
import cz.cvut.fit.tjv.fuelapp.domain.AppUser;
import cz.cvut.fit.tjv.fuelapp.domain.Record;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/api/appUser")
public class AppUserController {

    private final AppUserServiceInterface appUserService;
    private final RecordServiceInterface recordService;
    private final DTOConverter<AppUserDTO, AppUser> appUserDTOConverter;
    private final DTOConverter<RecordDTO, Record> recordDTOConverter;

    public AppUserController(AppUserServiceInterface appUserService, RecordServiceInterface recordService, DTOConverter<AppUserDTO, AppUser> appUserDTOConverter, DTOConverter<RecordDTO, Record> recordDTOConverter) {
        this.appUserService = appUserService;
        this.recordService = recordService;
        this.appUserDTOConverter = appUserDTOConverter;
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
        appUserService.createAppUser(appUserDTOConverter.toEntity(au));
        return au;
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
