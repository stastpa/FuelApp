package cz.cvut.fit.tjv.client.model;

import java.util.List;


public class AppUserDTO {
    private  Long id;
    private  String name;
    private  String surname;
    private  String email;
    private  Long rating;
    private  List<Long> recordIds;

    public AppUserDTO(Long id, String name, String surname, String email, Long rating, List<Long> recordIds) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.rating = rating;
        this.recordIds = recordIds;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public Long getRating() {
        return rating;
    }

    public List<Long> getRecordIds() {
        return recordIds;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public void setRecordIds(List<Long> recordIds) {
        this.recordIds = recordIds;
    }
}
