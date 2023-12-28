package cz.cvut.fit.tjv.fuelapp.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Entity
@Table(name = "Record")
@Getter
@Setter
@NoArgsConstructor
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_record")
    private Long id;
    @Column(name = "price")
    @NotBlank
    private Float price;
    @Column(name = "date")
    @NotBlank
    private Date date;
    @Column(name = "rating")
    @NotBlank
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

    public Record(Long id, Float price, Date date, Integer rating, Fuel fuelRated, AppUser userAuthor, GasStation gasStationRecord) {
        this.price = price;
        this.date = date;
        this.rating = rating;
        this.fuelRated = fuelRated;
        this.userAuthor = userAuthor;
        this.gasStationRecord = gasStationRecord;
    }
}
