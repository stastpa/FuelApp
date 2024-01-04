package cz.cvut.fit.tjv.fuelapp.controller;

import cz.cvut.fit.tjv.fuelapp.application.RecordServiceInterface;
import cz.cvut.fit.tjv.fuelapp.controller.converter.DTOConverter;
import cz.cvut.fit.tjv.fuelapp.controller.dto.RecordDTO;
import cz.cvut.fit.tjv.fuelapp.domain.Record;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/api/record")
public class RecordController {

    private final RecordServiceInterface recordService;
    private final DTOConverter<RecordDTO, Record> recordDTOConverter;

    public RecordController(RecordServiceInterface recordService, DTOConverter<RecordDTO, Record> recordDTOConverter) {
        this.recordService = recordService;
        this.recordDTOConverter = recordDTOConverter;
    }

    @GetMapping("/records")
    public List<RecordDTO> getRecords() {
        return recordService.getRecords().stream().map(recordDTOConverter::toDTO).toList();
    }

    @GetMapping(path = "{id}")
    public RecordDTO getRecord(@PathVariable("id") Long id) {
        return recordDTOConverter.toDTO(recordService.getRecordById(id));
    }

    @PostMapping
    public RecordDTO createRecord(@RequestBody RecordDTO recordDTO) {
        recordService.createRecord(recordDTOConverter.toEntity(recordDTO));
        return recordDTO;
    }

    @PutMapping(path = "{id}")
    public RecordDTO updateRecord(@PathVariable Long id, @RequestBody RecordDTO recordDTO) {
        Record entity = recordDTOConverter.toEntity(recordDTO);
        entity.setId(id);
        return recordDTOConverter.toDTO(recordService.updateRecord(entity));
    }

    @DeleteMapping(path = "{id}")
    public void deleteRecord(@PathVariable Long id) {
        recordService.deleteRecord(id);
    }
}
