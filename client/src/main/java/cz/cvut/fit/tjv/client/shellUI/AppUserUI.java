package cz.cvut.fit.tjv.client.shellUI;

import cz.cvut.fit.tjv.client.model.AppUserDTO;
import cz.cvut.fit.tjv.client.model.RecordDTO;
import cz.cvut.fit.tjv.client.service.AppUserService;
import cz.cvut.fit.tjv.client.service.GasStationService;
import cz.cvut.fit.tjv.client.service.RecordService;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;

@ShellComponent
public class AppUserUI {
    private AppUserService appUserService;
    private GasStationService gasStationService;

    public AppUserUI(AppUserService appUserService, GasStationService gasStationService) {
        this.appUserService = appUserService;
        this.gasStationService = gasStationService;
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

//    @ShellMethod
//    public void createRecord(float price, Long fuelId, Long gasStationId) {
//        Calendar calendar = Calendar.getInstance();
//        Date currentDate = calendar.getTime();
//        recordService.create(new RecordDTO(null, price, currentDate, 0, fuelId ,readCurrentUser().getId(), gasStationId));
//    }

    @ShellMethod
    public void getGasStationByCrirteria(String start, String end, String city) throws Exception{
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = dateFormat.parse(start);
        gasStationService.getGasStationByCrirteria(startDate, dateFormat.parse(end), city);
    }

    @ShellMethodAvailability({"read-current-user"})
    public Availability isCurrentUser() {
        return appUserService.isCurrentUser()
                ? Availability.available()
                : Availability.unavailable("Current user is not set");
    }
}
