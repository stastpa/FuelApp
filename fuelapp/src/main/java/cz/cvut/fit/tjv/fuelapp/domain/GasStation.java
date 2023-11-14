package cz.cvut.fit.tjv.fuelapp.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "GasStation")
public class GasStation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_gasStation")
    Long id;
    @Column(name = "name")
    String name;
    @Column(name = "country")
    String country;
    @Column(name = "psc")
    int psc;
    @Column(name = "city")
    String city;
    @Column(name = "street")
    String street;
    @Column(name = "number")
    String number;
    @Column(name = "phone_number")
    String phoneNumber;


    @OneToMany(targetEntity = Fuel.class, mappedBy = "soldAtGasStation", fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "fuelsSoldAtGasStation")
    List<Fuel> fuels;
}
