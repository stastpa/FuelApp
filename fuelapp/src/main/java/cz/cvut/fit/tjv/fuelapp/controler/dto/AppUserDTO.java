package cz.cvut.fit.tjv.fuelapp.controler.dto;

import cz.cvut.fit.tjv.fuelapp.domain.Record;

import java.util.List;

public class AppUserDTO {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    List<Record> fuelRecords;

    public AppUserDTO(Long id, String name, String surname, String email, String password) {
    }
}
