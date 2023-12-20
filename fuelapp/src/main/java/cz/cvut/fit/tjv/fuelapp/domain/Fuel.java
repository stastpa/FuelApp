package cz.cvut.fit.tjv.fuelapp.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Fuel")
public class Fuel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_fuel")
    Long id;
    @Column(name = "name")
    String name;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = GasStation.class)
    @JoinColumn(name = "soldAtGasStation")
    GasStation soldAtGasStation;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fuelRecords", targetEntity = Record.class, orphanRemoval = true)
    @JoinColumn(name = "fuelRecords")
    List<Record> fuelRecords;
}

