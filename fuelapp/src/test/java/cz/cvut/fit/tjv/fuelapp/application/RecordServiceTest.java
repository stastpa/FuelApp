package cz.cvut.fit.tjv.fuelapp.application;

import cz.cvut.fit.tjv.fuelapp.domain.AppUser;
import cz.cvut.fit.tjv.fuelapp.domain.Fuel;
import cz.cvut.fit.tjv.fuelapp.domain.GasStation;
import cz.cvut.fit.tjv.fuelapp.domain.Record;
import cz.cvut.fit.tjv.fuelapp.persistent.JPAAppUserReporitory;
import cz.cvut.fit.tjv.fuelapp.persistent.JPAFuelRepository;
import cz.cvut.fit.tjv.fuelapp.persistent.JPAGasStationRepository;
import cz.cvut.fit.tjv.fuelapp.persistent.JPARecordRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class RecordServiceTest {

    @Mock
    private JPARecordRepository recordRepository;

    @Mock
    private JPAGasStationRepository gasStationRepository;

    @Mock
    private JPAAppUserReporitory appUserRepository;

    @Mock
    private JPAFuelRepository fuelRepository;

    @InjectMocks
    private RecordService recordService;

    public RecordServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getRecordById_ExistingId_ReturnsRecord() {
        Long recordId = 1L;
        Record expectedRecord = new Record();
        expectedRecord.setId(recordId);

        when(recordRepository.findById(recordId)).thenReturn(Optional.of(expectedRecord));

        Record result = recordService.getRecordById(recordId);

        assertEquals(expectedRecord, result);
    }

    @Test
    void getRecordById_NonexistentId_ThrowsEntityNotFoundException() {
        Long recordId = 1L;

        when(recordRepository.findById(recordId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> recordService.getRecordById(recordId));
    }

    @Test
    void getRecords_ReturnsListOfRecords() {
        when(recordRepository.findAll()).thenReturn(List.of(new Record(), new Record()));

        List<Record> result = recordService.getRecords();

        assertEquals(2, result.size());
    }

    @Test
    void createRecord_ReturnsCreatedRecord() {
        Record recordToCreate = new Record();
        when(recordRepository.save(any(Record.class))).thenReturn(recordToCreate);

        Record result = recordService.createRecord(recordToCreate);

        assertEquals(recordToCreate, result);
    }

    @Test
    void updateRecord_ExistingId_ReturnsUpdatedRecord() {
        Long recordId = 1L;
        Record existingRecord = new Record();
        existingRecord.setId(recordId);

        Record updatedRecord = new Record();
        updatedRecord.setId(recordId);

        when(recordRepository.existsById(recordId)).thenReturn(true);
        when(recordRepository.save(any(Record.class))).thenReturn(updatedRecord);

        Record result = recordService.updateRecord(updatedRecord);

        assertEquals(updatedRecord, result);
    }

    @Test
    void updateRecord_NonexistentId_ThrowsEntityNotFoundException() {
        Record recordToUpdate = new Record();
        recordToUpdate.setId(1L);

        when(recordRepository.existsById(recordToUpdate.getId())).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> recordService.updateRecord(recordToUpdate));
    }

    @Test
    void deleteRecord_ExistingId_DeletesRecord() {
        Long recordId = 1L;

        when(recordRepository.existsById(recordId)).thenReturn(true);

        assertDoesNotThrow(() -> recordService.deleteRecord(recordId));
        Mockito.verify(recordRepository, Mockito.times(1)).deleteById(recordId);
    }

    @Test
    void deleteRecord_NonexistentId_ThrowsEntityNotFoundException() {
        Long recordId = 1L;

        when(recordRepository.existsById(recordId)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> recordService.deleteRecord(recordId));
    }

    @Test
    void addRecordToGasStation_ValidGasStationAndRecord_AddsRecordToGasStation() {
        Long gasStationId = 1L;
        Long recordId = 1L;

        GasStation gasStation = new GasStation();
        gasStation.setId(gasStationId);

        Record record = new Record();
        record.setId(recordId);

        when(recordRepository.existsById(recordId)).thenReturn(true);
        when(gasStationRepository.existsById(gasStationId)).thenReturn(true);
        when(recordRepository.getReferenceById(recordId)).thenReturn(record);
        when(gasStationRepository.getReferenceById(gasStationId)).thenReturn(gasStation);

        assertDoesNotThrow(() -> recordService.addRecordToGasStation(gasStationId, recordId));

        assertEquals(gasStation, record.getGasStationRecord());
    }

    @Test
    void addRecordToGasStation_NonexistentGasStation_ThrowsEntityNotFoundException() {
        Long gasStationId = 1L;
        Long recordId = 1L;

        when(gasStationRepository.existsById(gasStationId)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> recordService.addRecordToGasStation(gasStationId, recordId));
    }

    @Test
    void addRecordToGasStation_NonexistentRecord_ThrowsEntityNotFoundException() {
        Long gasStationId = 1L;
        Long recordId = 1L;

        when(recordRepository.existsById(recordId)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> recordService.addRecordToGasStation(gasStationId, recordId));
    }

    @Test
    void removeRecordFromGasStation_RecordInGasStation_RemovesRecordFromGasStation() {
        Long gasStationId = 1L;
        Long recordId = 1L;

        GasStation gasStation = new GasStation();
        gasStation.setId(gasStationId);

        Record record = new Record();
        record.setId(recordId);
        record.setGasStationRecord(gasStation);

        when(recordRepository.existsById(recordId)).thenReturn(true);
        when(gasStationRepository.existsById(gasStationId)).thenReturn(true);
        when(recordRepository.getReferenceById(recordId)).thenReturn(record);
        when(gasStationRepository.getReferenceById(gasStationId)).thenReturn(gasStation);

        assertDoesNotThrow(() -> recordService.removeRecordFromGasStation(gasStationId, recordId));

        assertNull(record.getGasStationRecord());
    }


    @Test
    void removeRecordFromGasStation_NonexistentGasStation_ThrowsEntityNotFoundException() {
        Long gasStationId = 1L;
        Long recordId = 1L;

        when(gasStationRepository.existsById(gasStationId)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> recordService.removeRecordFromGasStation(gasStationId, recordId));
    }

    @Test
    void removeRecordFromGasStation_NonexistentRecord_ThrowsEntityNotFoundException() {
        Long gasStationId = 1L;
        Long recordId = 1L;

        when(recordRepository.existsById(recordId)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> recordService.removeRecordFromGasStation(gasStationId, recordId));
    }

    @Test
    void addRecordToAppuser_ValidAppuserAndRecord_AddsRecordToAppuser() {
        Long appUserId = 1L;
        Long recordId = 1L;

        AppUser appUser = new AppUser();
        appUser.setId(appUserId);

        Record record = new Record();
        record.setId(recordId);

        when(recordRepository.existsById(recordId)).thenReturn(true);
        when(appUserRepository.existsById(appUserId)).thenReturn(true);
        when(recordRepository.getReferenceById(recordId)).thenReturn(record);
        when(appUserRepository.getReferenceById(appUserId)).thenReturn(appUser);

        assertDoesNotThrow(() -> recordService.addRecordToAppuser(appUserId, recordId));

        assertEquals(appUser, record.getUserAuthor());
    }

    @Test
    void addRecordToAppuser_NonexistentAppuser_ThrowsEntityNotFoundException() {
        Long appUserId = 1L;
        Long recordId = 1L;

        when(appUserRepository.existsById(appUserId)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> recordService.addRecordToAppuser(appUserId, recordId));
    }

    @Test
    void addRecordToAppuser_NonexistentRecord_ThrowsEntityNotFoundException() {
        Long appUserId = 1L;
        Long recordId = 1L;

        when(recordRepository.existsById(recordId)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> recordService.addRecordToAppuser(appUserId, recordId));
    }

    @Test
    void removeRecordFromAppuser_RecordInAppuser_RemovesRecordFromAppuser() {
        Long appUserId = 1L;
        Long recordId = 1L;

        AppUser appUser = new AppUser();
        appUser.setId(appUserId);

        Record record = new Record();
        record.setId(recordId);
        record.setUserAuthor(appUser);

        when(recordRepository.existsById(recordId)).thenReturn(true);
        when(appUserRepository.existsById(appUserId)).thenReturn(true);
        when(recordRepository.getReferenceById(recordId)).thenReturn(record);
        when(appUserRepository.getReferenceById(appUserId)).thenReturn(appUser);

        assertDoesNotThrow(() -> recordService.removeRecordFromAppuser(appUserId, recordId));

        assertNull(record.getUserAuthor());
    }


    @Test
    void removeRecordFromAppuser_NonexistentRecord_ThrowsEntityNotFoundException() {
        Long appUserId = 1L;
        Long recordId = 1L;

        when(recordRepository.existsById(recordId)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> recordService.removeRecordFromAppuser(appUserId, recordId));
    }

    @Test
    void addRecordToFuel_ValidFuelAndRecord_AddsRecordToFuel() {
        Long fuelId = 1L;
        Long recordId = 1L;

        Fuel fuel = new Fuel();
        fuel.setId(fuelId);

        Record record = new Record();
        record.setId(recordId);

        when(recordRepository.existsById(recordId)).thenReturn(true);
        when(fuelRepository.existsById(fuelId)).thenReturn(true);
        when(recordRepository.getReferenceById(recordId)).thenReturn(record);
        when(fuelRepository.getReferenceById(fuelId)).thenReturn(fuel);

        assertDoesNotThrow(() -> recordService.addRecordToFuel(fuelId, recordId));

        assertEquals(fuel, record.getFuelRated());
    }

    @Test
    void addRecordToFuel_NonexistentFuel_ThrowsEntityNotFoundException() {
        Long fuelId = 1L;
        Long recordId = 1L;

        when(fuelRepository.existsById(fuelId)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> recordService.addRecordToFuel(fuelId, recordId));
    }

    @Test
    void addRecordToFuel_NonexistentRecord_ThrowsEntityNotFoundException() {
        Long fuelId = 1L;
        Long recordId = 1L;

        when(recordRepository.existsById(recordId)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> recordService.addRecordToFuel(fuelId, recordId));
    }

    @Test
    void removeRecordFromFuel_RecordInFuel_RemovesRecordFromFuel() {
        Long fuelId = 1L;
        Long recordId = 1L;

        Fuel fuel = new Fuel();
        fuel.setId(fuelId);

        Record record = new Record();
        record.setId(recordId);
        record.setFuelRated(fuel);

        when(recordRepository.existsById(recordId)).thenReturn(true);
        when(fuelRepository.existsById(fuelId)).thenReturn(true);
        when(recordRepository.getReferenceById(recordId)).thenReturn(record);
        when(fuelRepository.getReferenceById(fuelId)).thenReturn(fuel);

        assertDoesNotThrow(() -> recordService.removeRecordFromFuel(fuelId, recordId));

        assertNull(record.getFuelRated());
    }

    @Test
    void removeRecordFromFuel_NonexistentRecord_ThrowsEntityNotFoundException() {
        Long fuelId = 1L;
        Long recordId = 1L;

        when(recordRepository.existsById(recordId)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> recordService.removeRecordFromFuel(fuelId, recordId));
    }
}