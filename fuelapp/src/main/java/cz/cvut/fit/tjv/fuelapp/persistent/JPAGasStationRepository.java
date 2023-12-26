package cz.cvut.fit.tjv.fuelapp.persistent;

import cz.cvut.fit.tjv.fuelapp.domain.AppUser;
import cz.cvut.fit.tjv.fuelapp.domain.GasStation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface JPAGasStationRepository extends JpaRepository<GasStation, Long> {
    List<GasStation> findByRecordsDateBetweenAndCity(Date startDate, Date endDate, String city);
}
