package cz.cvut.fit.tjv.fuelapp.application;

import cz.cvut.fit.tjv.fuelapp.domain.AppUser;
import cz.cvut.fit.tjv.fuelapp.domain.Record;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface AppUserServiceInterface {

    public AppUser getAppUserById(Long id) throws EntityNotFoundException;

    public List<AppUser> getAppUsers();

    public AppUser createAppUser(AppUser appuser);

    public AppUser updateAppUser(AppUser appuser) throws IllegalArgumentException;

    public void deleteAppUser(Long id) throws EntityNotFoundException;
}
