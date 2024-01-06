package cz.cvut.fit.tjv.client.api_client;

import cz.cvut.fit.tjv.client.model.GasStationWithPriceDTO;
import cz.cvut.fit.tjv.client.model.RecordDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import static org.apache.tomcat.util.http.FastHttpDateFormat.formatDate;

public class GasStationClient {
    private String baseUrl;
    private RestClient gasStationRestClient;

    public GasStationClient(@Value("${api.url}") String baseUrl) {
        this.baseUrl = baseUrl;
        gasStationRestClient = RestClient.create(baseUrl + "/rest/api/gasStation");
    }

    public Collection<GasStationWithPriceDTO> getGasStationByCrirteria(Date start, Date end, String city) {
        return Arrays.asList(gasStationRestClient.get()
                .uri("/gasStationsByCrit?startD={start}&endD={end}&city={city}", start, end, city)
                .retrieve()
                .toEntity(GasStationWithPriceDTO[].class)
                .getBody());
    }
}
