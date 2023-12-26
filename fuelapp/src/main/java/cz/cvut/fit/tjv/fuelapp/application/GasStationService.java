package cz.cvut.fit.tjv.fuelapp.application;

import cz.cvut.fit.tjv.fuelapp.domain.Fuel;
import cz.cvut.fit.tjv.fuelapp.domain.GasStation;
import cz.cvut.fit.tjv.fuelapp.persistent.JPAGasStationRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class GasStationService implements GasStationServiceInterface{

    private JPAGasStationRepository gasStationRepository;

    public GasStationService(JPAGasStationRepository gasStationRepository){

        this.gasStationRepository = gasStationRepository;
    }
    @Override
    public GasStation getGasStationById(Long id) throws EntityNotFoundException {
        return gasStationRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Gas Station with given id: " + id + " not found!"));
    }

    @Override
    public List<GasStation> getGasStations() {
        return gasStationRepository.findAll();
    }

    @Override
    public List<GasStation> getGasStationsSellingFuel(Fuel fuel) {
        return null;
    }

    @Override
    public GasStation createGasStation(GasStation gasStation) {
        return gasStationRepository.save(gasStation);
    }

    @Override
    public GasStation updateGasStation(GasStation gasStation) throws IllegalArgumentException {
        if(gasStationRepository.existsById(gasStation.getId()))
        {
            gasStationRepository.save(gasStation);
        }
        throw new IllegalArgumentException("Gas Station with given id: " + gasStation.getId() + " does not exist.");
    }

    @Override
    public void deleteGasStation(Long id) throws EntityNotFoundException {
        if(gasStationRepository.existsById(id))
        {
            gasStationRepository.deleteById(id);
        }
        throw new IllegalArgumentException("Gas Station with given id: " + id + " does not exist.");
    }

    public List<GasStation> getGasStationsByCriteria(Date startDate, Date endDate, String city) {
        return gasStationRepository.findByRecordsDateBetweenAndCity(startDate, endDate, city);
    }
}
