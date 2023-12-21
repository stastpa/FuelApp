package cz.cvut.fit.tjv.fuelapp.persistent;

import cz.cvut.fit.tjv.fuelapp.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPARecordRepository extends JpaRepository<Record, Long> {
}
