package cz.cvut.fit.tjv.fuelapp.application;

import cz.cvut.fit.tjv.fuelapp.domain.Record;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface RecordServiceInterface {
    Record getRecordById(Long id) throws EntityNotFoundException;

    List<Record> getRecords();

    Record createRecord(Record record) throws IllegalArgumentException;

    Record updateRecord(Record record) throws IllegalArgumentException;

    void deleteRecord(Long id) throws EntityNotFoundException;

    Record addRecordToGasStation(Long gasStationId, Long recordId) throws IllegalArgumentException;

    Record removeRecordFromGasStation(Long gasStationId, Long recordId) throws IllegalArgumentException;

    Record addRecordToAppuser(Long appUserId, Long recordId) throws IllegalArgumentException;

    Record removeRecordFromAppuser(Long appUserId, Long recordId) throws IllegalArgumentException;

    Record addRecordToFuel(Long fuelId, Long recordId) throws IllegalArgumentException;

    Record removeRecordFromFuel(Long fuelId, Long recordId) throws IllegalArgumentException;

}
