package cz.cvut.fit.tjv.client.service;


import cz.cvut.fit.tjv.client.api_client.AppUserClient;
import cz.cvut.fit.tjv.client.api_client.RecordClient;
import cz.cvut.fit.tjv.client.model.AppUserDTO;
import cz.cvut.fit.tjv.client.model.RecordDTO;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class AppUserService {
    private AppUserClient appUserClient;
    private Long currentUser;

    public AppUserService(AppUserClient appUserClient) {
        this.appUserClient = appUserClient;
    }

    public boolean isCurrentUser()
    {
        return currentUser != null;
    }

    public void setCurrentUser(Long currentUser) {
        this.currentUser = currentUser;
        appUserClient.setCurrentUser(currentUser);
    }

    public Optional<AppUserDTO> readOne(){
        return appUserClient.readOne();
    }

    public Collection<AppUserDTO> readAll() {
        return appUserClient.readAll();
    }

    public void create(AppUserDTO data) {
        appUserClient.create(data);
    }

    public Collection<RecordDTO> getUsersRecords(Long id) {
        return appUserClient.getUsersRecords(id);
    }
}
