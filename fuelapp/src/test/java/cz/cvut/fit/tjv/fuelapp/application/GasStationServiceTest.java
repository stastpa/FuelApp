package cz.cvut.fit.tjv.fuelapp.application;
import cz.cvut.fit.tjv.fuelapp.controller.dto.GasStationWithPriceDTO;
import cz.cvut.fit.tjv.fuelapp.domain.Fuel;
import cz.cvut.fit.tjv.fuelapp.domain.GasStation;
import cz.cvut.fit.tjv.fuelapp.persistent.JPAFuelRepository;
import cz.cvut.fit.tjv.fuelapp.persistent.JPAGasStationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class GasStationServiceTest {

    @Mock
    private JPAGasStationRepository gasStationRepository;

    @Mock
    private JPAFuelRepository fuelRepository;

    @InjectMocks
    private GasStationService gasStationService;

    public GasStationServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getGasStationById_ExistingId_ReturnsGasStation() {
        Long gasStationId = 1L;
        GasStation expectedGasStation = new GasStation();
        expectedGasStation.setId(gasStationId);

        when(gasStationRepository.findById(gasStationId)).thenReturn(Optional.of(expectedGasStation));

        GasStation result = gasStationService.getGasStationById(gasStationId);

        assertEquals(expectedGasStation, result);
    }

    @Test
    void getGasStationById_NonexistentId_ThrowsEntityNotFoundException() {
        Long gasStationId = 1L;

        when(gasStationRepository.findById(gasStationId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> gasStationService.getGasStationById(gasStationId));
    }

    @Test
    void getGasStations_ReturnsListOfGasStations() {
        List<GasStation> expectedGasStations = new ArrayList<>();
        expectedGasStations.add(new GasStation());
        expectedGasStations.add(new GasStation());

        when(gasStationRepository.findAll()).thenReturn(expectedGasStations);

        List<GasStation> result = gasStationService.getGasStations();

        assertEquals(expectedGasStations, result);
    }

    @Test
    void getGasStationsSellingFuel_ReturnsListOfGasStations() {
        Fuel fuel = new Fuel();
        fuel.setId(1L);

        List<GasStation> expectedGasStations = new ArrayList<>();
        expectedGasStations.add(new GasStation());
        expectedGasStations.add(new GasStation());

        when(gasStationRepository.findByFuelsSoldContaining(eq(fuel))).thenReturn(expectedGasStations);

        List<GasStation> result = gasStationService.getGasStationsSellingFuel(fuel);

        assertEquals(expectedGasStations, result);
    }

    @Test
    void createGasStation_ReturnsCreatedGasStation() {
        GasStation gasStationToCreate = new GasStation();
        gasStationToCreate.setName("Test Gas Station");

        when(gasStationRepository.save(any(GasStation.class))).thenReturn(gasStationToCreate);

        GasStation result = gasStationService.createGasStation(gasStationToCreate);

        assertEquals(gasStationToCreate, result);
    }

    @Test
    void updateGasStation_ExistingId_ReturnsUpdatedGasStation() {
        Long gasStationId = 1L;
        GasStation existingGasStation = new GasStation();
        existingGasStation.setId(gasStationId);

        GasStation updatedGasStation = new GasStation();
        updatedGasStation.setId(gasStationId);
        updatedGasStation.setName("Updated Gas Station");

        when(gasStationRepository.existsById(gasStationId)).thenReturn(true);
        when(gasStationRepository.save(any(GasStation.class))).thenReturn(updatedGasStation);

        GasStation result = gasStationService.updateGasStation(updatedGasStation);

        assertEquals(updatedGasStation, result);
    }

    @Test
    void updateGasStation_NonexistentId_ThrowsEntityNotFoundException() {
        GasStation gasStationToUpdate = new GasStation();
        gasStationToUpdate.setId(1L);

        when(gasStationRepository.existsById(gasStationToUpdate.getId())).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> gasStationService.updateGasStation(gasStationToUpdate));
    }

    @Test
    void deleteGasStation_ExistingId_DeletesGasStation() {
        Long gasStationId = 1L;

        when(gasStationRepository.existsById(gasStationId)).thenReturn(true);

        assertDoesNotThrow(() -> gasStationService.deleteGasStation(gasStationId));
        Mockito.verify(gasStationRepository, Mockito.times(1)).deleteById(gasStationId);
    }

    @Test
    void deleteGasStation_NonexistentId_ThrowsEntityNotFoundException() {
        Long gasStationId = 1L;

        when(gasStationRepository.existsById(gasStationId)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> gasStationService.deleteGasStation(gasStationId));
    }

    @Test
    void getGasStationsByCriteria_ReturnsListOfGasStationsWithPriceDTO() {
        Date startDate = new Date();
        Date endDate = new Date();
        String city = "Test City";

        List<GasStationWithPriceDTO> expectedGasStationsWithPriceDTO = new ArrayList<>();
        expectedGasStationsWithPriceDTO.add(new GasStationWithPriceDTO("1L", "Fuel1", 10.0f));
        expectedGasStationsWithPriceDTO.add(new GasStationWithPriceDTO("2L", "Fuel2", 12.0f));

        when(gasStationRepository.findByRecordsDateBetweenAndCityOrderByRecordsPriceAsc(startDate, endDate, city))
                .thenReturn(expectedGasStationsWithPriceDTO);

        List<GasStationWithPriceDTO> result = gasStationService.getGasStationsByCriteria(startDate, endDate, city);

        assertEquals(expectedGasStationsWithPriceDTO, result);
    }

    @Test
    void addFuelToGasStation_ValidFuelAndGasStation_AddsFuelToGasStation() {
        Long fuelId = 1L;
        Long gasStationId = 1L;

        Fuel fuel = new Fuel();
        fuel.setId(fuelId);

        GasStation gasStation = new GasStation();
        gasStation.setId(gasStationId);

        when(gasStationRepository.existsById(gasStationId)).thenReturn(true);
        when(fuelRepository.existsById(fuelId)).thenReturn(true);
        when(gasStationRepository.getReferenceById(gasStationId)).thenReturn(gasStation);
        when(fuelRepository.getReferenceById(fuelId)).thenReturn(fuel);

        assertDoesNotThrow(() -> gasStationService.addFuelToGasStation(fuelId, gasStationId));

        assertTrue(gasStation.getFuelsSold().contains(fuel));
    }

    @Test
    void addFuelToGasStation_NonexistentGasStation_ThrowsEntityNotFoundException() {
        Long fuelId = 1L;
        Long gasStationId = 1L;

        when(gasStationRepository.existsById(gasStationId)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> gasStationService.addFuelToGasStation(fuelId, gasStationId));
    }

    @Test
    void addFuelToGasStation_NonexistentFuel_ThrowsEntityNotFoundException() {
        Long fuelId = 1L;
        Long gasStationId = 1L;

        when(gasStationRepository.existsById(gasStationId)).thenReturn(true);
        when(fuelRepository.existsById(fuelId)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> gasStationService.addFuelToGasStation(fuelId, gasStationId));
    }

    @Test
    void removeFuelFromGasStation_FuelInGasStation_RemovesFuelFromGasStation() {
        Long fuelId = 1L;
        Long gasStationId = 1L;

        Fuel fuel = new Fuel();
        fuel.setId(fuelId);

        GasStation gasStation = new GasStation();
        gasStation.setId(gasStationId);
        gasStation.getFuelsSold().add(fuel);

        when(gasStationRepository.existsById(gasStationId)).thenReturn(true);
        when(fuelRepository.existsById(fuelId)).thenReturn(true);
        when(gasStationRepository.getReferenceById(gasStationId)).thenReturn(gasStation);
        when(fuelRepository.getReferenceById(fuelId)).thenReturn(fuel);

        assertDoesNotThrow(() -> gasStationService.removeFuelFromGasStation(fuelId, gasStationId));

        assertFalse(gasStation.getFuelsSold().contains(fuel));
    }

    @Test
    void removeFuelFromGasStation_FuelNotInGasStation_ThrowsIllegalArgumentException() {
        Long fuelId = 1L;
        Long gasStationId = 1L;

        Fuel fuel = new Fuel();
        fuel.setId(fuelId);

        GasStation gasStation = new GasStation();
        gasStation.setId(gasStationId);

        when(gasStationRepository.existsById(gasStationId)).thenReturn(true);
        when(fuelRepository.existsById(fuelId)).thenReturn(true);
        when(gasStationRepository.getReferenceById(gasStationId)).thenReturn(gasStation);
        when(fuelRepository.getReferenceById(fuelId)).thenReturn(fuel);

        assertThrows(IllegalArgumentException.class, () -> gasStationService.removeFuelFromGasStation(fuelId, gasStationId));
    }

    @Test
    void removeFuelFromGasStation_NonexistentGasStation_ThrowsEntityNotFoundException() {
        Long fuelId = 1L;
        Long gasStationId = 1L;

        when(gasStationRepository.existsById(gasStationId)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> gasStationService.removeFuelFromGasStation(fuelId, gasStationId));
    }

    @Test
    void removeFuelFromGasStation_NonexistentFuel_ThrowsEntityNotFoundException() {
        Long fuelId = 1L;
        Long gasStationId = 1L;

        when(gasStationRepository.existsById(gasStationId)).thenReturn(true);
        when(fuelRepository.existsById(fuelId)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> gasStationService.removeFuelFromGasStation(fuelId, gasStationId));
    }
}