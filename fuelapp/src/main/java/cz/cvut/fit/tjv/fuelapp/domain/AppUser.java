package cz.cvut.fit.tjv.fuelapp.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "AppUser")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_user")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "email")
    private String email;
    @Column(name = "rating")
    private Long rating;

    @OneToMany(targetEntity = Record.class, mappedBy = "userAuthor", fetch = FetchType.LAZY, orphanRemoval = true)
    List<Record> fuelRecords;

    public void updateRating() {
        if (fuelRecords != null) {
            this.rating = fuelRecords.stream()
                    .mapToLong(Record::getRating)
                    .sum();
        } else {
            this.rating = 0L;
        }
    }
}
