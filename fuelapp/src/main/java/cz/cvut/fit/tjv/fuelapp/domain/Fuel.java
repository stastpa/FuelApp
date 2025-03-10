package cz.cvut.fit.tjv.fuelapp.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Fuel")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Fuel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_fuel")
    private Long id;
    @Column(name = "name")
    private String name;

    @ManyToMany(targetEntity = GasStation.class)
    @JoinTable(
                name = "fuel_sold_at",
                joinColumns = @JoinColumn(name = "id_fuel"),
                inverseJoinColumns = @JoinColumn(name = "id_gas_station")
    )
    List<GasStation> soldAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fuelRated", targetEntity = Record.class, orphanRemoval = true)
    List<Record> fuelRecords;
}
