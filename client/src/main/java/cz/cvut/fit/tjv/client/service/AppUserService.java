package cz.cvut.fit.tjv.client.service;


import cz.cvut.fit.tjv.client.api_client.AppUserClient;
import cz.cvut.fit.tjv.client.model.AppUserDTO;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AppUserService {
    private AppUserClient appUserClient;

    public AppUserService(AppUserClient appUserClient) {
        this.appUserClient = appUserClient;
    }

    public Collection<AppUserDTO> readAll() {
        return appUserClient.readAll();
    }
}
