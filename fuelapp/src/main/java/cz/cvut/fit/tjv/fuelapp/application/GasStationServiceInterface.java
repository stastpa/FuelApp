package cz.cvut.fit.tjv.fuelapp.application;

import cz.cvut.fit.tjv.fuelapp.controller.dto.GasStationWithPriceDTO;
import cz.cvut.fit.tjv.fuelapp.domain.Fuel;
import cz.cvut.fit.tjv.fuelapp.domain.GasStation;
import jakarta.persistence.EntityNotFoundException;

import java.util.Date;
import java.util.List;

public interface GasStationServiceInterface {
    GasStation getGasStationById(Long id) throws EntityNotFoundException;

    List<GasStation> getGasStations();

    List<GasStation> getGasStationsSellingFuel(Fuel fuel);

    GasStation createGasStation(GasStation gasStation);

    GasStation updateGasStation(GasStation gasStation) throws IllegalArgumentException;

    void deleteGasStation(Long id) throws EntityNotFoundException;

    List<GasStationWithPriceDTO> getGasStationsByCriteria(Date startDate, Date endDate, String city);

    void addFuelToGasStation(Long fuelId, Long gasStationId) throws EntityNotFoundException;

    void removeFuelFromGasStation(Long fuelId, Long gasStationId) throws EntityNotFoundException, IllegalArgumentException;
}
