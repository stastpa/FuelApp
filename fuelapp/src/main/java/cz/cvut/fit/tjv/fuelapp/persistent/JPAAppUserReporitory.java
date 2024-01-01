package cz.cvut.fit.tjv.fuelapp.persistent;

import cz.cvut.fit.tjv.fuelapp.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface JPAAppUserReporitory extends JpaRepository<AppUser, Long> {
}
