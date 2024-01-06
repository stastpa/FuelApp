package cz.cvut.fit.tjv.client.shellUI;

import cz.cvut.fit.tjv.client.model.AppUserDTO;
import cz.cvut.fit.tjv.client.service.AppUserService;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

import java.util.ArrayList;
import java.util.Collection;

@ShellComponent
public class AppUserUI {
    private AppUserService appUserService;

    public AppUserUI(AppUserService appUserService) {
        this.appUserService = appUserService;
    }


    @ShellMethod
    public Collection<AppUserDTO> readAllUsers() {
        return appUserService.readAll();
    }

    @ShellMethod("register new user")
    public String registerUser(String name, @ShellOption String surname, String email) {
        appUserService.create(new AppUserDTO(null, name, surname, email, 0L, new ArrayList<>()));
        return "appUser created";
    }

    @ShellMethod
    public AppUserDTO readCurrentUser() {
        return appUserService.readOne().get();
    }

    @ShellMethod
    public void setCurrentUser(Long id) {
        appUserService.setCurrentUser(id);
    }

    @ShellMethod
    public void getUsersRecords(Long id) {
        appUserService.getUsersRecords(id);
    }

    @ShellMethodAvailability({"read-current-user"})
    public Availability isCurrentUser() {
        return appUserService.isCurrentUser()
                ? Availability.available()
                : Availability.unavailable("Current user is not set");
    }
}
