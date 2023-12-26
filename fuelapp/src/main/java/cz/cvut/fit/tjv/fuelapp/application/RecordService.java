package cz.cvut.fit.tjv.fuelapp.application;

import cz.cvut.fit.tjv.fuelapp.domain.Record;
import cz.cvut.fit.tjv.fuelapp.persistent.JPARecordRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class RecordService implements RecordServiceInterface {
    private JPARecordRepository recordRepository;

    public RecordService(JPARecordRepository recordRepository){

        this.recordRepository = recordRepository;
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
}
