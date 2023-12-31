package cz.cvut.fit.tjv.fuelapp.application;
import cz.cvut.fit.tjv.fuelapp.domain.Fuel;
import cz.cvut.fit.tjv.fuelapp.persistent.JPAFuelRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FuelServiceTest {

    @Mock
    private JPAFuelRepository fuelRepository;

    @InjectMocks
    private FuelService fuelService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getFuelById_ValidId_ReturnsFuel() {
        Long fuelId = 1L;
        Fuel expectedFuel = new Fuel(fuelId, "Gasoline", null, null);

        when(fuelRepository.findById(fuelId)).thenReturn(Optional.of(expectedFuel));

        Fuel resultFuel = fuelService.getFuelById(fuelId);

        assertEquals(expectedFuel, resultFuel);
    }

    @Test
    void getFuelById_InvalidId_ThrowsEntityNotFoundException() {
        Long fuelId = 1L;

        when(fuelRepository.findById(fuelId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> fuelService.getFuelById(fuelId));
    }

    @Test
    void getFuels_ReturnsListOfFuels() {
        List<Fuel> expectedFuels = Arrays.asList(new Fuel(1L, "Gasoline", null, null),
                new Fuel(2L, "Diesel", null, null));

        when(fuelRepository.findAll()).thenReturn(expectedFuels);

        List<Fuel> resultFuels = fuelService.getFuels();

        assertEquals(expectedFuels, resultFuels);
    }

    @Test
    void createFuel_ReturnsCreatedFuel() {
        Fuel fuelToCreate = new Fuel(null, "Gasoline", null, null);
        Fuel expectedFuel = new Fuel(1L, "Gasoline", null, null);

        when(fuelRepository.save(fuelToCreate)).thenReturn(expectedFuel);

        Fuel resultFuel = fuelService.createFuel(fuelToCreate);

        assertEquals(expectedFuel, resultFuel);
    }

    @Test
    void updateFuel_ValidId_ReturnsUpdatedAppUser() {
        Fuel existingFuel = new Fuel(1L, "Gasoline", null, null);
        Fuel updatedFuel = new Fuel(1L, "Premium Gasoline", null, null);

        when(fuelRepository.existsById(existingFuel.getId())).thenReturn(true);
        when(fuelRepository.save(updatedFuel)).thenReturn(updatedFuel);

        Fuel resultFuel = fuelService.updateFuel(updatedFuel);

        assertEquals(updatedFuel, resultFuel);
    }

    @Test
    void updateFuel_InvalidId_ThrowsIllegalArgumentException() {
        Fuel nonExistingFuel = new Fuel(1L, "Gasoline", null, null);

        when(fuelRepository.existsById(nonExistingFuel.getId())).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> fuelService.updateFuel(nonExistingFuel));
    }

    @Test
    void deleteFuel_ValidId_DeletesFuel() {
        Long fuelId = 1L;

        when(fuelRepository.existsById(fuelId)).thenReturn(true);

        assertDoesNotThrow(() -> fuelService.deleteFuel(fuelId));

        verify(fuelRepository, times(1)).deleteById(fuelId);
    }

    @Test
    void deleteFuel_InvalidId_ThrowsEntityNotFoundException() {
        Long nonExistingFuelId = 1L;

        when(fuelRepository.existsById(nonExistingFuelId)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> fuelService.deleteFuel(nonExistingFuelId));
    }
}


