package cz.cvut.fit.tjv.fuelapp.persistent;

import cz.cvut.fit.tjv.fuelapp.domain.AppUser;
import cz.cvut.fit.tjv.fuelapp.domain.GasStation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPAGasStationRepository extends JpaRepository<GasStation, Long> {
}
