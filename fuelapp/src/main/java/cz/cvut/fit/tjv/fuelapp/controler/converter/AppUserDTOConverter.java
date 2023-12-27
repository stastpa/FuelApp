package cz.cvut.fit.tjv.fuelapp.controler.converter;
import cz.cvut.fit.tjv.fuelapp.application.RecordServiceInterface;
import cz.cvut.fit.tjv.fuelapp.controler.dto.AppUserDTO;
import cz.cvut.fit.tjv.fuelapp.domain.AppUser;
import cz.cvut.fit.tjv.fuelapp.domain.Record;
import org.springframework.stereotype.Component;

@Component
public class AppUserDTOConverter implements DTOConverter<AppUserDTO, AppUser>{

    private final RecordServiceInterface recordService;

    public AppUserDTOConverter(RecordServiceInterface recordService)
    {
        this.recordService = recordService;
    }
    @Override
    public AppUserDTO toDTO(AppUser a)
    {
        return new AppUserDTO(
                a.getId(),
                a.getName(),
                a.getSurname(),
                a.getEmail(),
                a.getPassword(),
                a.getFuelRecords().stream().map(Record::getId).toList());
    }
    @Override
    public AppUser toEntity(AppUserDTO dto)
    {
        return new AppUser(
                dto.getId(),
                dto.getName(),
                dto.getSurname(),
                dto.getEmail(),
                dto.getPassword(),
                dto.getRecordIds().stream().map(recordService::getRecordById).toList()
        );
    }
}
