package cz.cvut.fit.tjv.fuelapp.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "AppUser")
@Getter
@Setter
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_user")
    private Long id;
    @Column(name = "name")
    @NotBlank
    private String name;
    @Column(name = "surname")
    @NotBlank
    private String surname;
    @Column(name = "email")
    @NotBlank
    private String email;
    @Column(name = "password")
    @NotBlank
    private String password;

    @OneToMany(targetEntity = Record.class, mappedBy = "userAuthor", fetch = FetchType.LAZY, orphanRemoval = true)
    List<Record> fuelRecords;

    // Default constructor (required by JPA)
    public AppUser(Long id, String name, String surname, String email, String password, List<Record> list) {
    }

    // Constructor with parameters
    public AppUser(String name, String surname, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }
}
