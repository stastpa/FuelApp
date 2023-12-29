package cz.cvut.fit.tjv.fuelapp.persistent;

import cz.cvut.fit.tjv.fuelapp.controler.dto.GasStationWithPriceDTO;
import cz.cvut.fit.tjv.fuelapp.domain.GasStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface JPAGasStationRepository extends JpaRepository<GasStation, Long> {
    @Query(value = "SELECT new cz.cvut.fit.tjv.fuelapp.controler.dto.GasStationWithPriceDTO(gs, f.name, r.price) " +
            "FROM GasStation gs " +
            "JOIN gs.records r " +
            "JOIN r.fuelRated f " +
            "WHERE r.date BETWEEN :startDate AND :endDate AND gs.city = :city " +
            "ORDER BY r.price ASC")
    List<GasStationWithPriceDTO> findByRecordsDateBetweenAndCityOrderByRecordsPriceAsc(
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate,
            @Param("city") String city);
}
