package cz.cvut.fit.tjv.fuelapp.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Fuel")
@Getter
@Setter
public class Fuel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_fuel")
    private Long id;
    @Column(name = "name")
    @NotBlank
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = GasStation.class)
    @JoinColumn(name = "soldAtGasStation")
    GasStation soldAtGasStation;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fuelRated", targetEntity = Record.class, orphanRemoval = true)
    List<Record> fuelRecords;
}
