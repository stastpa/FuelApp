package cz.cvut.fit.tjv.fuelapp.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    @Column(name = "id_gas_station")
    private Long id;
    @Column(name = "name")
    @NotBlank
    private String name;
    @Column(name = "country")
    @NotBlank
    private String country;
    @Column(name = "psc")
    @NotBlank
    private Integer psc;
    @Column(name = "city")
    @NotBlank
    private String city;
    @Column(name = "street")
    @NotBlank
    private String street;
    @Column(name = "number")
    @NotBlank
    private String number;
    @Column(name = "phone_number")
    @NotBlank
    private String phoneNumber;


    @ManyToMany(mappedBy = "soldAt")
    List<Fuel> fuelsSold;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "gasStationRecord", targetEntity = Record.class, orphanRemoval = true)
    List<Record> records;


    public GasStation() {
    }
    public GasStation(String name, String country, Integer psc, String city, String street, String number, String phoneNumber) {
        this.name = name;
        this.country = country;
        this.psc = psc;
        this.city = city;
        this.street = street;
        this.number = number;
        this.phoneNumber = phoneNumber;
    }
}
