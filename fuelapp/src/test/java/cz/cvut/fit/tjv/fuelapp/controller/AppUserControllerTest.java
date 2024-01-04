package cz.cvut.fit.tjv.fuelapp.controller;

import cz.cvut.fit.tjv.fuelapp.application.AppUserServiceInterface;
import cz.cvut.fit.tjv.fuelapp.controller.converter.DTOConverter;
import cz.cvut.fit.tjv.fuelapp.controller.dto.AppUserDTO;
import cz.cvut.fit.tjv.fuelapp.domain.AppUser;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@AutoConfigureMockMvc
class AppUserControllerTest {

    @MockBean
    private AppUserServiceInterface appUserService;

    @MockBean
    private DTOConverter<AppUserDTO, AppUser> appUserDTOConverter;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetAppUsers() throws Exception {
        AppUser user1 = new AppUser(1L, "John", "Doe", "john@example.com", 0L, null);
        AppUser user2 = new AppUser(2L, "Jane", "Doe", "jane@example.com", 0L, null);

        when(appUserService.getAppUsers()).thenReturn(Arrays.asList(user1, user2));
        when(appUserDTOConverter.toDTO(user1)).thenReturn(new AppUserDTO(1L, "John", "Doe", "john@example.com", 0L, null));
        when(appUserDTOConverter.toDTO(user2)).thenReturn(new AppUserDTO(2L, "Jane", "Doe", "jane@example.com", 0L, null));

        mockMvc.perform(get("/rest/api/appUser/appUsers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("John"))
                .andExpect(jsonPath("$[0].surname").value("Doe"))
                .andExpect(jsonPath("$[0].email").value("john@example.com"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Jane"))
                .andExpect(jsonPath("$[1].surname").value("Doe"))
                .andExpect(jsonPath("$[1].email").value("jane@example.com"));
    }

    @Test
    void testGetAppUser() throws Exception {
        AppUser appUser = new AppUser(1L, "John", "Doe", "john@example.com", 0L, null);
        when(appUserService.getAppUserById(1L)).thenReturn(appUser);
        when(appUserDTOConverter.toDTO(appUser)).thenReturn(new AppUserDTO(1L, "John", "Doe", "john@example.com", 0L, null));

        mockMvc.perform(get("/rest/api/appUser/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John"))
                .andExpect(jsonPath("$.surname").value("Doe"))
                .andExpect(jsonPath("$.email").value("john@example.com"));
    }

    @Test
    void testCreateAppUser() throws Exception {


        AppUser appUser = new AppUser(1L, "John", "Doe", "john@example.com", 0L, new ArrayList<>());
        AppUserDTO dto = new AppUserDTO(1L, "John", "Doe", "john@example.com", 0L, new ArrayList<>());

        when(appUserService.createAppUser(appUser)).thenReturn(appUser);
        when(appUserDTOConverter.toDTO(appUser)).thenReturn(dto);
        when(appUserDTOConverter.toEntity(dto)).thenReturn(appUser);

        mockMvc.perform(MockMvcRequestBuilders.post("/rest/api/appUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1,\"name\": \"John\",\"surname\": \"Doe\",\"email\": \"john@example.com\",\"rating\": 0,\"recordIds\": []}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John"))
                .andExpect(jsonPath("$.surname").value("Doe"))
                .andExpect(jsonPath("$.email").value("john@example.com"));
    }

    @Test
    void testDeleteAppUser() throws Exception {
        AppUser appUser = new AppUser(1L, "John", "Doe", "john@example.com", 0L, new ArrayList<>());


        mockMvc.perform(MockMvcRequestBuilders.delete("/rest/api/appUser/{id}", 1))
                .andExpect(status().isOk());

        Mockito.verify(appUserService, Mockito.atLeastOnce()).deleteAppUser(appUser.getId());
    }
}