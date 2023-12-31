package cz.cvut.fit.tjv.fuelapp.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "AppUser")
@Getter
@Setter
@NoArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_user")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;

    @OneToMany(targetEntity = Record.class, mappedBy = "userAuthor", fetch = FetchType.LAZY, orphanRemoval = true)
    List<Record> fuelRecords;

    public AppUser(Long id, String name, String surname, String email, String password, List<Record> list) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }
}
