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
    @Column(name = "id_gasStation")
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


    @OneToMany(targetEntity = Fuel.class, mappedBy = "soldAtGasStation", fetch = FetchType.LAZY, orphanRemoval = true)
    List<Fuel> fuels;

    @OneToMany(mappedBy = "soldAtGasStation", fetch = FetchType.LAZY, orphanRemoval = true)
    List<Record> records;
}
