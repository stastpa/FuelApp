package cz.cvut.fit.tjv.fuelapp.application;

import cz.cvut.fit.tjv.fuelapp.domain.Fuel;
import cz.cvut.fit.tjv.fuelapp.domain.GasStation;
import jakarta.persistence.EntityNotFoundException;

import java.util.Date;
import java.util.List;

public interface GasStationServiceInterface {
    public GasStation getGasStationById(Long id) throws EntityNotFoundException;

    public List<GasStation> getGasStations();

    public List<GasStation> getGasStationsSellingFuel(Fuel fuel);

    public GasStation createGasStation(GasStation gasStation);

    public GasStation updateGasStation(GasStation gasStation) throws IllegalArgumentException;

    public void deleteGasStation(Long id) throws EntityNotFoundException;

    public List<GasStation> getGasStationsByCriteria(Date startDate, Date endDate, String city);

    public void addFuelToGasStation(Long fuelId, Long gasStationId) throws EntityNotFoundException;

    public void removeFuelFromGasStation(Long fuelId, Long gasStationId) throws EntityNotFoundException, IllegalArgumentException;
}
