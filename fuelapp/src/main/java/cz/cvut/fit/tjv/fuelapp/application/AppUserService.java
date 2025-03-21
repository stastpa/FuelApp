package cz.cvut.fit.tjv.fuelapp.application;

import cz.cvut.fit.tjv.fuelapp.domain.AppUser;
import cz.cvut.fit.tjv.fuelapp.persistent.JPAAppUserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AppUserService implements AppUserServiceInterface{

    private final JPAAppUserRepository appUserRepository;

    public AppUserService(JPAAppUserRepository appUserRepository){

        this.appUserRepository = appUserRepository;
    }

    @Override
    public AppUser getAppUserById(Long id) throws EntityNotFoundException {
        return appUserRepository.findById(id).orElseThrow(()->new EntityNotFoundException("App User with given id: " + id + " not found!"));
    }

    @Override
    public List<AppUser> getAppUsers() {
        return appUserRepository.findAll();
    }

    @Override
    public AppUser createAppUser(AppUser appuser) {
        return appUserRepository.save(appuser);
    }

    @Override
    public AppUser updateAppUser(AppUser appuser) throws IllegalArgumentException {
        if(appUserRepository.existsById(appuser.getId()))
        {
            return appUserRepository.save(appuser);
        }
        throw new IllegalArgumentException("App user with given id: " + appuser.getId() + " does not exist.");
    }

    @Override
    public void deleteAppUser(Long id) throws EntityNotFoundException {
        if(appUserRepository.existsById(id))
        {
            appUserRepository.deleteById(id);
        }
        else {
            throw new EntityNotFoundException("Record with given id: " + id + " does not exist.");
        }
    }
}
