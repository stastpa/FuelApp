package cz.cvut.fit.tjv.fuelapp.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "GasStation")
@Getter
@Setter
public class GasStation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_gasStation")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "country")
    private String country;
    @Column(name = "psc")
    private Integer psc;
    @Column(name = "city")
    private String city;
    @Column(name = "street")
    private String street;
    @Column(name = "number")
    private String number;
    @Column(name = "phone_number")
    private String phoneNumber;


    @OneToMany(targetEntity = Fuel.class, mappedBy = "soldAtGasStation", fetch = FetchType.LAZY, orphanRemoval = true)
    List<Fuel> fuels;
}
