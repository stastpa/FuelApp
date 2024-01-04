package cz.cvut.fit.tjv.fuelapp.application;

import cz.cvut.fit.tjv.fuelapp.domain.AppUser;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface AppUserServiceInterface {

    AppUser getAppUserById(Long id) throws EntityNotFoundException;

    List<AppUser> getAppUsers();

    AppUser createAppUser(AppUser appuser);

    AppUser updateAppUser(AppUser appuser) throws IllegalArgumentException;

    void deleteAppUser(Long id) throws EntityNotFoundException;
}
