package cz.cvut.fit.tjv.fuelapp.application;

import cz.cvut.fit.tjv.fuelapp.domain.AppUser;
import cz.cvut.fit.tjv.fuelapp.persistent.JPAAppUserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
class AppUserServiceTest {

    @MockBean
    private JPAAppUserRepository appUserRepository;

    @Autowired
    private AppUserService appUserService;
    @Test
    void getAppUserById_ValidId_ReturnsAppUser() {
        Long validId = 1L;
        AppUser expectedAppUser = new AppUser();
        expectedAppUser.setId(validId);

        when(appUserRepository.findById(validId)).thenReturn(Optional.of(expectedAppUser));

        AppUser result = appUserService.getAppUserById(validId);

        assertEquals(expectedAppUser, result);
    }

    @Test
    void getAppUserById_InvalidId_ThrowsEntityNotFoundException() {
        Long invalidId = 999L;

        when(appUserRepository.findById(invalidId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> appUserService.getAppUserById(invalidId));
    }

    @Test
    void getAppUsers_ReturnsListOfAppUsers() {
        List<AppUser> expectedAppUsers = List.of(new AppUser(), new AppUser());
        when(appUserRepository.findAll()).thenReturn(expectedAppUsers);

        List<AppUser> result = appUserService.getAppUsers();

        assertEquals(expectedAppUsers, result);
    }

    @Test
    void createAppUser_ReturnsCreatedAppUser() {
        AppUser appUserToCreate = new AppUser();
        AppUser expectedCreatedAppUser = new AppUser();

        when(appUserRepository.save(appUserToCreate)).thenReturn(expectedCreatedAppUser);

        AppUser result = appUserService.createAppUser(appUserToCreate);

        assertEquals(expectedCreatedAppUser, result);
    }

    @Test
    void updateAppUser_ValidId_ReturnsUpdatedAppUser() {
        Long validId = 1L;
        AppUser appUserToUpdate = new AppUser();
        appUserToUpdate.setId(validId);

        when(appUserRepository.existsById(validId)).thenReturn(true);
        when(appUserRepository.save(appUserToUpdate)).thenReturn(appUserToUpdate);

        AppUser result = appUserService.updateAppUser(appUserToUpdate);

        assertEquals(appUserToUpdate, result);
    }

    @Test
    void updateAppUser_InvalidId_ThrowsIllegalArgumentException() {
        Long invalidId = 999L;
        AppUser appUserToUpdate = new AppUser();
        appUserToUpdate.setId(invalidId);

        when(appUserRepository.existsById(invalidId)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> appUserService.updateAppUser(appUserToUpdate));
    }

    @Test
    void deleteAppUser_ValidId_DeletesAppUser() {
        Long validId = 1L;

        when(appUserRepository.existsById(validId)).thenReturn(true);

        assertDoesNotThrow(() -> appUserService.deleteAppUser(validId));

        Mockito.verify(appUserRepository, Mockito.times(1)).deleteById(validId);
    }

    @Test
    void deleteAppUser_InvalidId_ThrowsEntityNotFoundException() {
        Long invalidId = 999L;

        when(appUserRepository.existsById(invalidId)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> appUserService.deleteAppUser(invalidId));
    }
}