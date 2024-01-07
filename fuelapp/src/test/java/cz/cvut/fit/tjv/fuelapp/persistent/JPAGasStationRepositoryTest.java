package cz.cvut.fit.tjv.fuelapp.persistent;

import cz.cvut.fit.tjv.fuelapp.controller.dto.GasStationWithPriceDTO;
import cz.cvut.fit.tjv.fuelapp.domain.Fuel;
import cz.cvut.fit.tjv.fuelapp.domain.GasStation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringJUnitConfig
@SpringBootTest
public class JPAGasStationRepositoryTest {

    @Autowired
    private JPAGasStationRepository gasStationRepository;
    @Autowired
    private JPAFuelRepository fuelRepository;

    @Test
    public void testFindByRecordsDateBetweenAndCityOrderByRecordsPriceAsc() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date startDate = dateFormat.parse("2024-01-02");
        Date endDate = dateFormat.parse("2024-01-09");
        String city = "Prague";

        List<GasStationWithPriceDTO> result = gasStationRepository
                .findByRecordsDateBetweenAndCityOrderByRecordsPriceAsc(startDate, endDate, city);

        assertFalse(result.isEmpty());
        assertTrue(result.stream().allMatch(Objects::nonNull));

        GasStationWithPriceDTO firstElement = result.get(0);
        System.out.println("Gas Station: " + firstElement.getGasStationName());
        System.out.println("Fuel: " + firstElement.getFuelName());
        System.out.println("Price: " + firstElement.getPrice());
    }

    @Test
    public void testFindByFuelsSoldContaining() {
        Fuel fuel = fuelRepository.getReferenceById(1L);
        List<GasStation> result = gasStationRepository.findByFuelsSoldContaining(fuel);
        assertFalse(result.isEmpty());
        assertTrue(result.stream().allMatch(Objects::nonNull));
    }
}
