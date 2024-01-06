package cz.cvut.fit.tjv.client.service;

import cz.cvut.fit.tjv.client.api_client.GasStationClient;
import cz.cvut.fit.tjv.client.model.GasStationWithPriceDTO;

import java.util.Collection;
import java.util.Date;

public class GasStationService {

        private GasStationClient gasStationClient;

        public GasStationService(GasStationClient gasStationClient) {
            this.gasStationClient = gasStationClient;
        }

        public Collection<GasStationWithPriceDTO> getGasStationByCrirteria(Date start, Date end, String city) {
           return gasStationClient.getGasStationByCrirteria(start, end, city);
        }
    }
