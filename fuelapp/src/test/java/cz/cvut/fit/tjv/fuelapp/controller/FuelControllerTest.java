package cz.cvut.fit.tjv.fuelapp.controller;

import cz.cvut.fit.tjv.fuelapp.application.FuelServiceInterface;
import cz.cvut.fit.tjv.fuelapp.controller.converter.DTOConverter;
import cz.cvut.fit.tjv.fuelapp.controller.dto.FuelDTO;
import cz.cvut.fit.tjv.fuelapp.domain.Fuel;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class FuelControllerTest {

    @MockBean
    private FuelServiceInterface fuelService;

    @MockBean
    private DTOConverter<FuelDTO, Fuel> fuelDTOConverter;

    private FuelController fuelController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetFuels() throws Exception {
        Fuel fuel1 = new Fuel(1L, "Petrol", null, null);
        Fuel fuel2 = new Fuel(2L, "Diesel", null, null);

        when(fuelService.getFuels()).thenReturn(Arrays.asList(fuel1, fuel2));
        when(fuelDTOConverter.toDTO(fuel1)).thenReturn(new FuelDTO(1L, "Petrol", null,null));
        when(fuelDTOConverter.toDTO(fuel2)).thenReturn(new FuelDTO(2L, "Diesel", null, null));

        mockMvc.perform(get("/rest/api/fuel/fuels"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Petrol"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Diesel"));
    }

    @Test
    void testGetFuel() throws Exception {
        Fuel fuel = new Fuel(1L, "Petrol", null, null);
        when(fuelService.getFuelById(1L)).thenReturn(fuel);
        when(fuelDTOConverter.toDTO(fuel)).thenReturn(new FuelDTO(1L, "Petrol", null, null));

        mockMvc.perform(get("/rest/api/fuel/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Petrol"));
    }


    @Test
    void testCreateFuel() throws Exception {
        Fuel fuel = new Fuel(1L,"gas", new ArrayList<>(), new ArrayList<>());
        FuelDTO dto = new FuelDTO(1L,"gas", new ArrayList<>(), new ArrayList<>());

        when(fuelService.createFuel(fuel)).thenReturn(fuel);
        when(fuelDTOConverter.toDTO(fuel)).thenReturn(dto);
        when(fuelDTOConverter.toEntity(dto)).thenReturn(fuel);

        mockMvc.perform(MockMvcRequestBuilders.post("/rest/api/fuel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"name\":\"gas\",\"gasStationIds\":[],\"recordIds\":[]}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("gas"));
    }

    @Test
    void testDeleteFuel() throws Exception {
        Fuel fuel = new Fuel(1L,"gas", new ArrayList<>(), new ArrayList<>());

        mockMvc.perform(MockMvcRequestBuilders.delete("/rest/api/fuel/{id}", 1))
                .andExpect(status().isOk());

        Mockito.verify(fuelService, Mockito.atLeastOnce()).deleteFuel(fuel.getId());
    }
}
