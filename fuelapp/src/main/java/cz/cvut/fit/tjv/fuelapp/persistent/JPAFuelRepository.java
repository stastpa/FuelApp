package cz.cvut.fit.tjv.fuelapp.persistent;

import cz.cvut.fit.tjv.fuelapp.domain.AppUser;
import cz.cvut.fit.tjv.fuelapp.domain.Fuel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPAFuelRepository extends JpaRepository<Fuel, Long> {
}
