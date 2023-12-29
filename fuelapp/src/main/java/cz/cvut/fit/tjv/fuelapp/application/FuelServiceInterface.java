package cz.cvut.fit.tjv.fuelapp.application;

import cz.cvut.fit.tjv.fuelapp.domain.Fuel;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface FuelServiceInterface {
    Fuel getFuelById(Long id) throws EntityNotFoundException;

    List<Fuel> getFuels();

    Fuel createFuel(Fuel fuel) throws IllegalArgumentException;

    Fuel updateFuel(Fuel fuel) throws IllegalArgumentException;

    void deleteFuel(Long id) throws EntityNotFoundException;
}
