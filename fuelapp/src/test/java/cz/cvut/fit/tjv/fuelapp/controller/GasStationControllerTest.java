package cz.cvut.fit.tjv.fuelapp.controller;

import cz.cvut.fit.tjv.fuelapp.application.GasStationServiceInterface;
import cz.cvut.fit.tjv.fuelapp.controller.converter.DTOConverter;
import cz.cvut.fit.tjv.fuelapp.controller.dto.GasStationDTO;
import cz.cvut.fit.tjv.fuelapp.domain.GasStation;
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

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class GasStationControllerTest {

    @MockBean
    private GasStationServiceInterface gasStationService;

    @MockBean
    private DTOConverter<GasStationDTO, GasStation> gasStationDTOConverter;

    @InjectMocks
    private GasStationController gasStationController;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetGasStations() throws Exception {
        GasStation gasStation1 = new GasStation(1L, "Station1", "Country1", 12345, "City1", "Street1", "1A", "123456789", null, null);
        GasStation gasStation2 = new GasStation(2L, "Station2", "Country2", 67890, "City2", "Street2", "2B", "987654321", null, null);


        when(gasStationService.getGasStations()).thenReturn(Arrays.asList(gasStation1, gasStation2));
        when(gasStationDTOConverter.toDTO(gasStation1)).thenReturn(new GasStationDTO(1L, "Station1", "Country1", 12345, "City1", "Street1", "1A", "123456789", null, null));
        when(gasStationDTOConverter.toDTO(gasStation2)).thenReturn(new GasStationDTO(2L, "Station2", "Country2", 67890, "City2", "Street2", "2B", "987654321", null, null));

        mockMvc.perform(get("/rest/api/gasStation"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Station1"))
                .andExpect(jsonPath("$[0].city").value("City1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Station2"))
                .andExpect(jsonPath("$[1].city").value("City2"));
    }

    @Test
    void testGetGasStation() throws Exception {
        GasStation gasStation = new GasStation(1L, "Station1", "Country1", 12345, "City1", "Street1", "1A", "123456789", null, null);
        when(gasStationService.getGasStationById(1L)).thenReturn(gasStation);
        when(gasStationDTOConverter.toDTO(gasStation)).thenReturn(new GasStationDTO(1L, "Station1", "Country1", 12345, "City1", "Street1", "1A", "123456789", null, null));

        mockMvc.perform(get("/rest/api/gasStation/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Station1"))
                .andExpect(jsonPath("$.city").value("City1"));
    }

    @Test
    void testCreateGasStation() throws Exception {
        GasStation gasStation = new GasStation(1L, "Prague Station", "Czech Republic", 12345, "Prague", "Main Street", "1A", "123456789", new ArrayList<>(), new ArrayList<>());
        GasStationDTO dto = new GasStationDTO(1L, "Prague Station", "Czech Republic", 12345, "Prague", "Main Street", "1A", "123456789", new ArrayList<>(), new ArrayList<>());

        when(gasStationService.createGasStation(gasStation)).thenReturn(gasStation);
        when(gasStationDTOConverter.toDTO(gasStation)).thenReturn(dto);
        when(gasStationDTOConverter.toEntity(dto)).thenReturn(gasStation);

        mockMvc.perform(post("/rest/api/gasStation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"name\":\"Prague Station\",\"country\":\"Czech Republic\",\"psc\":12345,\"city\":\"Prague\",\"street\":\"Main Street\",\"number\":\"1A\",\"phoneNumber\":\"123456789\",\"fuelIds\":[],\"recordIds\":[]}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Prague Station"));
    }

    @Test
    void testDeleteGasStation() throws Exception {
        GasStation gasStation = new GasStation(1L, "Prague Station", "Czech Republic", 12345, "Prague", "Main Street", "1A", "123456789", new ArrayList<>(), new ArrayList<>());
        GasStationDTO dto = new GasStationDTO(1L, "Prague Station", "Czech Republic", 12345, "Prague", "Main Street", "1A", "123456789", new ArrayList<>(), new ArrayList<>());

        when(gasStationService.createGasStation(gasStation)).thenReturn(gasStation);
        when(gasStationDTOConverter.toDTO(gasStation)).thenReturn(dto);
        when(gasStationDTOConverter.toEntity(dto)).thenReturn(gasStation);

        mockMvc.perform(post("/rest/api/gasStation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"name\":\"Prague Station\",\"country\":\"Czech Republic\",\"psc\":12345,\"city\":\"Prague\",\"street\":\"Main Street\",\"number\":\"1A\",\"phoneNumber\":\"123456789\",\"fuelIds\":[],\"recordIds\":[]}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Prague Station"));

        when(gasStationService.getGasStationById(1L)).thenReturn(gasStation);
        mockMvc.perform(get("/rest/api/gasStation/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Prague Station"));

        mockMvc.perform(delete("/rest/api/gasStation/{id}", 1))
                .andExpect(status().isOk());
    }
}
