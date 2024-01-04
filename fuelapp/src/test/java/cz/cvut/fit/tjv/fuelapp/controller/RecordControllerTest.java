package cz.cvut.fit.tjv.fuelapp.controller;

import cz.cvut.fit.tjv.fuelapp.application.RecordServiceInterface;
import cz.cvut.fit.tjv.fuelapp.controller.RecordController;
import cz.cvut.fit.tjv.fuelapp.controller.converter.DTOConverter;
import cz.cvut.fit.tjv.fuelapp.controller.dto.RecordDTO;
import cz.cvut.fit.tjv.fuelapp.domain.Record;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Date;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class RecordControllerTest {

    @MockBean
    private RecordServiceInterface recordService;

    @MockBean
    private DTOConverter<RecordDTO, Record> recordDTOConverter;

    @InjectMocks
    private RecordController recordController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetRecords() throws Exception {
        Record record1 = new Record(1L, 32.4f, new Date(), -4, null, null, null);
        Record record2 = new Record(2L, 32.7f, new Date(), 20, null, null, null);

        when(recordService.getRecords()).thenReturn(Arrays.asList(record1, record2));
        when(recordDTOConverter.toDTO(record1)).thenReturn(new RecordDTO(1L, 32.4f, new Date(), -4, null, null, null));
        when(recordDTOConverter.toDTO(record2)).thenReturn(new RecordDTO(2L, 32.7f, new Date(), 20, null, null, null));

        mockMvc.perform(get("/rest/api/record/records"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].rating").value(-4))
                .andExpect(jsonPath("$[0].price").value(32.4))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].rating").value(20))
                .andExpect(jsonPath("$[1].price").value(32.7));
    }

    @Test
    void testGetRecord() throws Exception {
        Record record = new Record(1L, 32.4f, new Date(), -4, null, null, null);
        when(recordService.getRecordById(1L)).thenReturn(record);
        when(recordDTOConverter.toDTO(record)).thenReturn(new RecordDTO(1L, 32.4f, new Date(), -4, null, null, null));

        mockMvc.perform(get("/rest/api/record/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.rating").value(-4))
                .andExpect(jsonPath("$.price").value(32.4));
    }

    @Test
    void testCreateRecord() throws Exception {
        Record record = new Record(1L, 50.f, new Date(), 4, null, null, null);
        RecordDTO dto = new RecordDTO(1L, 50.0f, new Date(), 4, null, null, null);

        when(recordService.createRecord(record)).thenReturn(record);
        when(recordDTOConverter.toDTO(record)).thenReturn(dto);
        when(recordDTOConverter.toEntity(dto)).thenReturn(record);

        mockMvc.perform(post("/rest/api/record")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"price\":50.0,\"date\":\"2024-01-02T18:08:16.530+01:00\",\"rating\":4,\"fuelRatedId\":null,\"userAuthorId\":null,\"gasStationRecordId\":null}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.price").value(50.0));
    }

    @Test
    void testDeleteRecord() throws Exception {
        Record record = new Record(1L, 50.f, new Date(), 4, null, null, null);
        RecordDTO dto = new RecordDTO(1L, 50.0f, new Date(), 4, null, null, null);

        when(recordService.createRecord(record)).thenReturn(record);
        when(recordDTOConverter.toDTO(record)).thenReturn(dto);
        when(recordDTOConverter.toEntity(dto)).thenReturn(record);

        mockMvc.perform(post("/rest/api/record")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"price\":50.0,\"date\":\"2024-01-02T18:08:16.530+01:00\",\"rating\":4,\"fuelRatedId\":null,\"userAuthorId\":null,\"gasStationRecordId\":null}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.price").value(50.0));

        when(recordService.getRecordById(1L)).thenReturn(record);
        mockMvc.perform(get("/rest/api/record/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.price").value(50.0));

        mockMvc.perform(delete("/rest/api/record/{id}", 1))
                .andExpect(status().isOk());
    }
}