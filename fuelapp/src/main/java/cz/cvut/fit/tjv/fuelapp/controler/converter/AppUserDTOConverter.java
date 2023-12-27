package cz.cvut.fit.tjv.fuelapp.controler.converter;

import cz.cvut.fit.tjv.fuelapp.controler.dto.AppUserDTO;
import cz.cvut.fit.tjv.fuelapp.domain.AppUser;

public class AppUserDTOConverter {
    public AppUserDTO toDTO(AppUser a)
    {
        return new AppUserDTO(a.getId(),a.getName(),a.getSurname(),a.getEmail(), a.getPassword());
    }
    public AppUser toEntity(AppUserDTO dto)
    {
        return new AppUser()
    }
}
