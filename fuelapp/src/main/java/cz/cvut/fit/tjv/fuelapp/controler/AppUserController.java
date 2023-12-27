package cz.cvut.fit.tjv.fuelapp.controler;


import cz.cvut.fit.tjv.fuelapp.domain.AppUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest/api/appUser")
public class AppUserController {
    @GetMapping
    public List<AppUser> getAppUsers(){
        return new ArrayList<>();
    }

    @GetMapping(path = "{id}")
    public AppUser getAppUser(@PathVariable("id") Long id)
    {
        return new AppUser();
    }
}
