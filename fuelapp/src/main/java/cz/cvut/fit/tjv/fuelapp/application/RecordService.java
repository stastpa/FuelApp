package cz.cvut.fit.tjv.fuelapp.application;

import cz.cvut.fit.tjv.fuelapp.domain.Record;
import cz.cvut.fit.tjv.fuelapp.persistent.JPAAppUserReporitory;
import cz.cvut.fit.tjv.fuelapp.persistent.JPAFuelRepository;
import cz.cvut.fit.tjv.fuelapp.persistent.JPAGasStationRepository;
import cz.cvut.fit.tjv.fuelapp.persistent.JPARecordRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class RecordService implements RecordServiceInterface {
    private JPARecordRepository recordRepository;
    private JPAGasStationRepository gasStationRepository;
    private JPAAppUserReporitory appUserReporitory;
    private JPAFuelRepository fuelRepository;

    public RecordService(JPARecordRepository recordRepository, JPAGasStationRepository gasStationRepository,
                         JPAAppUserReporitory appUserReporitory, JPAFuelRepository fuelRepository){

        this.recordRepository = recordRepository;
        this.gasStationRepository = gasStationRepository;
        this.appUserReporitory = appUserReporitory;
        this.fuelRepository = fuelRepository;
    }

    @Override
    public Record getRecordById(Long id) throws EntityNotFoundException {
        return recordRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Record with given id: " + id + " not found!"));
    }

    @Override
    public List<Record> getRecords() {
        return recordRepository.findAll();
    }

    @Override
    public Record createRecord(Record record) {
        return recordRepository.save(record);
    }

    @Override
    public Record updateRecord(Record record) throws IllegalArgumentException {
        if(recordRepository.existsById(record.getId()))
        {
            recordRepository.save(record);
        }
        throw new IllegalArgumentException("Record with given id: " + record.getId() + " does not exist.");
    }

    @Override
    public void deleteRecord(Long id) throws EntityNotFoundException {
        if(recordRepository.existsById(id))
        {
            recordRepository.deleteById(id);
        }
        throw new IllegalArgumentException("Record with given id: " + id + " does not exist.");
    }

    @Override
    public Record addRecordToGasStation(Long gasStationId, Long recordId) throws IllegalArgumentException {
        if(recordRepository.existsById(recordId)){
            if(gasStationRepository.existsById(gasStationId))
            {
                Record r1 = recordRepository.getReferenceById(recordId);
                r1.setGasStationRecord(gasStationRepository.getReferenceById(gasStationId));
                return r1;
            }
            throw new IllegalArgumentException("Gas station with given id: " + gasStationId + " does not exist.");
        }
        throw new IllegalArgumentException("Record with given id: " + recordId + " does not exist.");
    }

    @Override
    public Record removeRecordFromGasStation(Long gasStationId, Long recordId) throws IllegalArgumentException {
        if(recordRepository.existsById(recordId)){
                Record r1 = recordRepository.getReferenceById(recordId);
                r1.setGasStationRecord(null);
                return r1;
        }
        throw new IllegalArgumentException("Record with given id: " + recordId + " does not exist.");
    }

    @Override
    public Record addRecordToAppuser(Long appUserId, Long recordId) throws IllegalArgumentException {
        if(recordRepository.existsById(recordId)){
            if(appUserReporitory.existsById(appUserId))
            {
                Record r1 = recordRepository.getReferenceById(recordId);
                r1.setUserAuthor(appUserReporitory.getReferenceById(appUserId));
                return r1;
            }
            throw new IllegalArgumentException("Gas station with given id: " + appUserId + " does not exist.");
        }
        throw new IllegalArgumentException("Record with given id: " + recordId + " does not exist.");
    }

    @Override
    public Record removeRecordFromAppuser(Long appUserId, Long recordId) throws IllegalArgumentException {
        if(recordRepository.existsById(recordId)){
            Record r1 = recordRepository.getReferenceById(recordId);
            r1.setUserAuthor(null);
            return r1;
        }
        throw new IllegalArgumentException("Record with given id: " + recordId + " does not exist.");
    }

    @Override
    public Record addRecordToFuel(Long fuelId, Long recordId) throws IllegalArgumentException {
        if(recordRepository.existsById(recordId)){
            if(fuelRepository.existsById(fuelId))
            {
                Record r1 = recordRepository.getReferenceById(recordId);
                r1.setFuelRated(fuelRepository.getReferenceById(fuelId));
                return r1;
            }
            throw new IllegalArgumentException("Gas station with given id: " + fuelId + " does not exist.");
        }
        throw new IllegalArgumentException("Record with given id: " + recordId + " does not exist.");
    }

    @Override
    public Record removeRecordFromFuel(Long fuelId, Long recordId) throws IllegalArgumentException {
        if(recordRepository.existsById(recordId)){
            Record r1 = recordRepository.getReferenceById(recordId);
            r1.setFuelRated(null);
            return r1;
        }
        throw new IllegalArgumentException("Record with given id: " + recordId + " does not exist.");
    }
}
