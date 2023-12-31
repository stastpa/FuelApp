package cz.cvut.fit.tjv.fuelapp.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


import java.util.List;

@Getter
@AllArgsConstructor
public class AppUserDTO {
    private final Long id;
    private final String name;
    private final String surname;
    private final String email;
    private final String password;
    private final List<Long> recordIds;
}
