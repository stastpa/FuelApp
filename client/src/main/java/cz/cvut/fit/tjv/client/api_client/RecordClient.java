package cz.cvut.fit.tjv.client.api_client;

import cz.cvut.fit.tjv.client.model.RecordDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class RecordClient {
    private String baseUrl;
    private RestClient recordRestClient;

    public RecordClient(@Value("${api.url}") String baseUrl) {
        this.baseUrl = baseUrl;
        this.recordRestClient = RestClient.create(baseUrl + "/rest/api/record");
    }

    public void create(RecordDTO data) {
        recordRestClient.post()
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(data)
                .retrieve()
                .toBodilessEntity();
    }

    public void update(Long id, RecordDTO data) {
        recordRestClient.put()
                .uri("/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(data)
                .retrieve()
                .toBodilessEntity();
    }

    public RecordDTO getRecordById(Long id) {
        return recordRestClient.get()
                .uri("/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(RecordDTO.class)
                .getBody();
    }
}
