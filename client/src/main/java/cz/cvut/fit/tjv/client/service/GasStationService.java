package cz.cvut.fit.tjv.client.service;

import cz.cvut.fit.tjv.client.api_client.GasStationClient;
import cz.cvut.fit.tjv.client.model.GasStationWithPriceDTO;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class GasStationService {

    private GasStationClient gasStationClient;

    public GasStationService(GasStationClient gasStationClient) {
        this.gasStationClient = gasStationClient;
    }

    public Collection<GasStationWithPriceDTO> getGasStationByCriteria(String start, String end, String city) {


        return gasStationClient.getGasStationByCrirteria(start, end, city);
    }
}