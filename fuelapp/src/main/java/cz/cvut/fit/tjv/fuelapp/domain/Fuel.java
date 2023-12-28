package cz.cvut.fit.tjv.fuelapp.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Fuel")
@Getter
@Setter
@NoArgsConstructor
public class Fuel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_fuel")
    private Long id;
    @Column(name = "name")
    @NotBlank
    private String name;

    @ManyToMany(targetEntity = GasStation.class)
    @JoinTable(
                name = "fuel_sold_at",
                joinColumns = @JoinColumn(name = "id_gas_station"),
                inverseJoinColumns = @JoinColumn(name = "id_fuel")
    )
    List<GasStation> soldAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fuelRated", targetEntity = Record.class, orphanRemoval = true)
    List<Record> fuelRecords;


    public Fuel(Long id, String name, List<GasStation> list, List<Record> records) {
        this.name = name;
    }
}
