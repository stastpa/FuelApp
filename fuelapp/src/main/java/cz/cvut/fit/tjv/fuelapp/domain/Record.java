package cz.cvut.fit.tjv.fuelapp.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Entity
@Table(name = "Record")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_record")
    private Long id;
    @Column(name = "price")
    private Float price;
    @Column(name = "date")
    private Date date;
    @Column(name = "rating")
    private Integer rating;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Fuel.class)
    @JoinColumn(name = "fuelRated")
    Fuel fuelRated;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = AppUser.class)
    @JoinColumn(name = "userAuthor")
    AppUser userAuthor;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = GasStation.class)
    @JoinColumn(name = "gasStationRecord")
    GasStation gasStationRecord;
}
