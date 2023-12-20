package cz.cvut.fit.tjv.fuelapp.domain;

import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table(name = "Record")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_record")
    Long id;
    @Column(name = "price")
    float price;
    @Column(name = "date")
    Date date;
    @Column(name = "rating")
    int rating;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = GasStation.class)
    @JoinColumn(name = "fuelRated")
    Fuel fuelRated;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = GasStation.class)
    @JoinColumn(name = "userAuthor")
    User userAuthor;
}
