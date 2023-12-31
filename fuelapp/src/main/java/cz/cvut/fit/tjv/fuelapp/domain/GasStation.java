package cz.cvut.fit.tjv.fuelapp.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "GasStation")
@Getter
@Setter
@NoArgsConstructor
public class GasStation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_gas_station")
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


    @ManyToMany(mappedBy = "soldAt")
    List<Fuel> fuelsSold = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "gasStationRecord", targetEntity = Record.class, orphanRemoval = true)
    List<Record> records = new ArrayList<>();
    public GasStation(Long id, String name, String country, Integer psc, String city, String street, String number, String phoneNumber, List<Fuel> list, List<Record> records) {
        this.name = name;
        this.country = country;
        this.psc = psc;
        this.city = city;
        this.street = street;
        this.number = number;
        this.phoneNumber = phoneNumber;
    }
}
