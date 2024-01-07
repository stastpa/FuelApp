package cz.cvut.fit.tjv.client.api_client;

import cz.cvut.fit.tjv.client.model.GasStationWithPriceDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Arrays;
import java.util.Collection;

@Component
public class GasStationClient {
    private String baseUrl;
    private RestClient gasStationRestClient;

    public GasStationClient(@Value("${api.url}") String baseUrl) {
        this.baseUrl = baseUrl;
        gasStationRestClient = RestClient.create(baseUrl + "/rest/api/gasStation");
    }

    public Collection<GasStationWithPriceDTO> getGasStationByCrirteria(String start, String end, String city) {

        return Arrays.asList(gasStationRestClient.get()
                .uri("/gasStationsByCrit?startD={start}&endD={end}&city={city}", start, end, city)
                .retrieve()
                .toEntity(GasStationWithPriceDTO[].class)
                .getBody());
    }
}
