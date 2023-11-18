package cz.cvut.fit.tjv.fuelapp.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Entity
@Table(name = "Record")
@Getter
@Setter
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
}
