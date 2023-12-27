package cz.cvut.fit.tjv.fuelapp.application;

import cz.cvut.fit.tjv.fuelapp.domain.Record;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface RecordServiceInterface {
    public Record getRecordById(Long id) throws EntityNotFoundException;

    public List<Record> getRecords();

    public Record createRecord(Record record) throws IllegalArgumentException;

    public Record updateRecord(Record record) throws IllegalArgumentException;

    public void deleteRecord(Long id) throws EntityNotFoundException;

    public Record addRecordToGasStation(Long gasStationId, Long recordId) throws IllegalArgumentException;

    public Record removeRecordFromGasStation(Long gasStationId, Long recordId) throws IllegalArgumentException;

    public Record addRecordToAppuser(Long appUserId, Long recordId) throws IllegalArgumentException;

    public Record removeRecordFromAppuser(Long appUserId, Long recordId) throws IllegalArgumentException;

    public Record addRecordToFuel(Long fuelId, Long recordId) throws IllegalArgumentException;

    public Record removeRecordFromFuel(Long fuelId, Long recordId) throws IllegalArgumentException;

}
