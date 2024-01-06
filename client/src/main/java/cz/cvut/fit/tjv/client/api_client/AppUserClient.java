package cz.cvut.fit.tjv.client.api_client;

import cz.cvut.fit.tjv.client.model.AppUserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Arrays;
import java.util.Collection;

@Component
public class AppUserClient {
    private RestClient appUserRestClient;

    public AppUserClient(@Value("${api.url}") String baseUrl) {
        appUserRestClient = RestClient.create(baseUrl + "/appUser");
    }

    public Collection<AppUserDTO> readAll() {
        return Arrays.asList(appUserRestClient.get()
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(AppUserDTO[].class)
                .getBody());
    }
}
