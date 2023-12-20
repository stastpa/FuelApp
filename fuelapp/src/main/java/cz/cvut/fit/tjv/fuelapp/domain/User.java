package cz.cvut.fit.tjv.fuelapp.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_user")
    Long id;
    @Column(name = "name")
    String name;
    @Column(name = "surname")
    String surname;
    @Column(name = "email")
    String email;
    @Column(name = "password")
    String password;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Record.class, orphanRemoval = true)
    @JoinColumn(name = "fuelRecords")
    List<Record> fuelRecords;
} // TODO: Vazby... vsude...
