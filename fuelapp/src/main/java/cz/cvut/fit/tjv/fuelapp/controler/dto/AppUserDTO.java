package cz.cvut.fit.tjv.fuelapp.controler.dto;

import cz.cvut.fit.tjv.fuelapp.domain.Record;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
