package cz.cvut.fit.tjv.client.shellUI;

import cz.cvut.fit.tjv.client.model.AppUserDTO;
import cz.cvut.fit.tjv.client.service.AppUserService;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.Collection;

@ShellComponent
public class AppUserUI {
    private AppUserService appUserService;

    public AppUserUI(AppUserService appUserService) {
        this.appUserService = appUserService;
    }


    @ShellMethod
    public Collection<AppUserDTO> readAllRecords() {
        return appUserService.readAll();
    }
}
