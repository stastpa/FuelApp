package cz.cvut.fit.tjv.client.api_client;

import cz.cvut.fit.tjv.client.model.AppUserDTO;
import cz.cvut.fit.tjv.client.model.RecordDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Component
public class AppUserClient {
    private String baseUrl;
    private RestClient appUserRestClient;
    private RestClient currentUserRestClient;

    public AppUserClient(@Value("${api.url}") String baseUrl) {
        this.baseUrl = baseUrl;
        appUserRestClient = RestClient.create(baseUrl + "/rest/api/appUser");
    }

    public void setCurrentUser(Long id) {
        currentUserRestClient = RestClient.builder()
                .baseUrl(baseUrl + "/rest/api/appUser/{id}")
                .defaultUriVariables(Map.of("id", id))
                .build();
    }

    public Collection<RecordDTO> getUsersRecords(Long id) {
        return Arrays.asList(appUserRestClient.get()
                .uri("/{id}/records", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(RecordDTO[].class)
                .getBody());
    }

    public Optional<AppUserDTO> readOne() {
        try {
            return Optional.of(currentUserRestClient.get()
                    .retrieve().toEntity(AppUserDTO.class).getBody());
        } catch (HttpClientErrorException.NotFound e) {
            return Optional.empty();
        }
    }

    public Collection<AppUserDTO> readAll() {
        return Arrays.asList(appUserRestClient.get()
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(AppUserDTO[].class)
                .getBody());
    }

    public void create(AppUserDTO data) {
        appUserRestClient.post()
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(data)
                .retrieve()
                .toBodilessEntity();
    }
}
