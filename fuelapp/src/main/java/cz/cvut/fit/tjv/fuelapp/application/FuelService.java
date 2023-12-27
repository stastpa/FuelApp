package cz.cvut.fit.tjv.fuelapp.application;

import cz.cvut.fit.tjv.fuelapp.domain.Fuel;
import cz.cvut.fit.tjv.fuelapp.persistent.JPAFuelRepository;
import cz.cvut.fit.tjv.fuelapp.persistent.JPAGasStationRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class FuelService implements FuelServiceInterface{

    private JPAFuelRepository fuelRepository;
    private JPAGasStationRepository gasStationRepository;

    public FuelService(JPAFuelRepository fuelRepository, JPAGasStationRepository gasStationRepository){

        this.fuelRepository = fuelRepository;
        this.gasStationRepository = gasStationRepository;
    }
    @Override
    public Fuel getFuelById(Long id) throws EntityNotFoundException {
        return fuelRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Fuel with given id: " + id + " not found!"));
    }

    @Override
    public List<Fuel> getFuels() {
        return fuelRepository.findAll();
    }

    @Override
    public Fuel createFuel(Fuel fuel) throws IllegalArgumentException {
        return fuelRepository.save(fuel);
    }

    @Override
    public Fuel updateFuel(Fuel fuel) throws IllegalArgumentException {
        if(fuelRepository.existsById(fuel.getId()))
        {
            fuelRepository.save(fuel);
        }
        throw new IllegalArgumentException("Fuel with given id: " + fuel.getId() + " does not exist.");
    }

    @Override
    public void deleteFuel(Long id) throws EntityNotFoundException {
        if(fuelRepository.existsById(id))
        {
            fuelRepository.deleteById(id);
        }
        throw new IllegalArgumentException("Fuel with given id: " + id + " does not exist.");
    }
}
