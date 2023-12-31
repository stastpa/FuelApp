package cz.cvut.fit.tjv.fuelapp.controller.converter;

import cz.cvut.fit.tjv.fuelapp.application.AppUserServiceInterface;
import cz.cvut.fit.tjv.fuelapp.application.FuelServiceInterface;
import cz.cvut.fit.tjv.fuelapp.application.GasStationServiceInterface;
import cz.cvut.fit.tjv.fuelapp.controller.dto.RecordDTO;
import cz.cvut.fit.tjv.fuelapp.domain.Record;
import org.springframework.stereotype.Component;

@Component
public class RecordDTOConverter implements DTOConverter<RecordDTO, Record>{

    private final AppUserServiceInterface appUserService;
    private final GasStationServiceInterface gasStationService;
    private final FuelServiceInterface fuelService;

    public RecordDTOConverter(AppUserServiceInterface appUserService, GasStationServiceInterface gasStationService, FuelServiceInterface fuelService) {
        this.appUserService = appUserService;
        this.gasStationService = gasStationService;
        this.fuelService = fuelService;
    }

    @Override
    public RecordDTO toDTO(Record r)
    {
        return new RecordDTO(r.getId(),r.getPrice(),r.getDate(),r.getRating(),r.getFuelRated().getId(),r.getUserAuthor().getId(),r.getGasStationRecord().getId());
    }
    @Override
    public Record toEntity(RecordDTO dto)
    {
        return new Record(dto.getId(),dto.getPrice(),dto.getDate(),dto.getRating(),
                        fuelService.getFuelById(dto.getFuelRatedId()),
                        appUserService.getAppUserById(dto.getUserAuthorId()),
                        gasStationService.getGasStationById(dto.getGasStationRecordId())
                        );
    }
}
