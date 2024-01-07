package cz.cvut.fit.tjv.client.shellUI;

import cz.cvut.fit.tjv.client.model.AppUserDTO;
import cz.cvut.fit.tjv.client.model.GasStationWithPriceDTO;
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

@ShellComponent
public class AppUserUI {
    private AppUserService appUserService;
    private GasStationService gasStationService;
    private RecordService recordService;

    public AppUserUI(AppUserService appUserService, GasStationService gasStationService, RecordService recordService) {
        this.appUserService = appUserService;
        this.gasStationService = gasStationService;
        this.recordService = recordService;
    }

    @ShellMethod("displays all users")
    public Collection<AppUserDTO> readAllUsers() {
        return appUserService.readAll();
    }

    @ShellMethod("register new user")
    public String registerUser(String name, @ShellOption String surname, String email) {
        appUserService.create(new AppUserDTO(null, name, surname, email, 0L, new ArrayList<>()));
        return "appUser created";
    }

    @ShellMethod("Creates new record for an existing gas station and the fuel they are selling")
    public String createRecord(Float price, Long fuelId, Long gasStationId) {
        recordService.create(new RecordDTO(null, price, new Date(), 0, fuelId, readCurrentUser().getId(), gasStationId));
        return "Record created";
    }

    @ShellMethod("Displays current user")
    public AppUserDTO readCurrentUser() {
        return appUserService.readOne().get();
    }

    @ShellMethod("Sets current user")
    public void setCurrentUser(Long id) {
        appUserService.setCurrentUser(id);
    }

    @ShellMethod("Displays users records")
    public Collection<RecordDTO> getUsersRecords(Long id) {
        return appUserService.getUsersRecords(id);
    }

    @ShellMethod("Gives like to record")
    public String likeRecord(Long id) {
        recordService.updateRating(id, 1);
        return "Thank you for your feedback";
    }

    @ShellMethod("Gives dislike to record")
    public String dislikeRecord(Long id) {
         recordService.updateRating(id, -1);
        return "Thank you for your feedback";
    }



    @ShellMethod("filters cheapest gas station and fuel by city and date. Dates are in format yyyy-MM-dd") //search-by-date-and-city 2024-01-01 2024-01-10 Prague
    public Collection<GasStationWithPriceDTO> searchByDateAndCity(String start, String end, String city) {

        return gasStationService.getGasStationByCriteria(
                start,
                end,
                city
        );
    }


    @ShellMethodAvailability({"read-current-user", "dislikeRecord", "likeRecord"})
    public Availability isCurrentUser() {
        return appUserService.isCurrentUser()
                ? Availability.available()
                : Availability.unavailable("Current user is not set");
    }

    @ShellMethodAvailability("create-record")
    public Availability isCreateRecordAvailable() {
        if (!appUserService.isCurrentUser()) {
            return Availability.unavailable("Current user is not set");
        }

        if (appUserService.isBanned()) {
            return Availability.unavailable("The account has been temporarily suspended due to an excessive number of dislikes on their posted records.");
        }

        return Availability.available();
    }
}
