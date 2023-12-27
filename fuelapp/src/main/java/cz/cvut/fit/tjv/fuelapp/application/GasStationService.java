package cz.cvut.fit.tjv.fuelapp.application;

import cz.cvut.fit.tjv.fuelapp.domain.Fuel;
import cz.cvut.fit.tjv.fuelapp.domain.GasStation;
import cz.cvut.fit.tjv.fuelapp.persistent.JPAFuelRepository;
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

    private JPAFuelRepository fuelRepository;

    public GasStationService(JPAGasStationRepository gasStationRepository, JPAFuelRepository fuelRepository){

        this.gasStationRepository = gasStationRepository;
        this.fuelRepository = fuelRepository;
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

    @Override
    public List<GasStation> getGasStationsByCriteria(Date startDate, Date endDate, String city) {
        return gasStationRepository.findByRecordsDateBetweenAndCity(startDate, endDate, city);
    }

    @Override
    public void addFuelToGasStation(Long fuelId, Long gasStationId) throws EntityNotFoundException {
        if(gasStationRepository.existsById((gasStationId))){
            if(fuelRepository.existsById(fuelId)){
                gasStationRepository.getReferenceById(gasStationId).getFuelsSold().add(fuelRepository.getReferenceById(fuelId));
                return;
            }
            throw new IllegalArgumentException("Fuel with given id: " + fuelId + " does not exist.");
        }
        throw new IllegalArgumentException("Gas Station with given id: " + gasStationId + " does not exist.");
    }

    @Override
    public void removeFuelFromGasStation(Long fuelId, Long gasStationId) throws EntityNotFoundException, IllegalArgumentException {
        if(gasStationRepository.existsById((gasStationId))){
            if(fuelRepository.existsById(fuelId)){
                GasStation gs1 = gasStationRepository.getReferenceById(gasStationId);
                Fuel f1 = fuelRepository.getReferenceById(fuelId);

                if(gs1.getFuelsSold().contains(f1))
                {
                    gs1.getFuelsSold().remove(f1);
                    return;
                }
               throw new IllegalArgumentException("Gas station with id: " + gasStationId + " does not contain gas with id: " + fuelId);
            }
            throw new IllegalArgumentException("Fuel with given id: " + fuelId + " does not exist.");
        }
        throw new IllegalArgumentException("Gas Station with given id: " + gasStationId + " does not exist.");
    }
}
