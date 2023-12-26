package cz.cvut.fit.tjv.fuelapp.application;

import cz.cvut.fit.tjv.fuelapp.domain.Fuel;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface FuelServiceInterface {
    public Fuel getFuelById(Long id) throws EntityNotFoundException;

    public List<Fuel> getFuels();

    public Fuel createFuel(Fuel fuel) throws IllegalArgumentException;

    public Fuel updateFuel(Fuel fuel) throws IllegalArgumentException;

    public void deleteFuel(Long id) throws EntityNotFoundException;
}
