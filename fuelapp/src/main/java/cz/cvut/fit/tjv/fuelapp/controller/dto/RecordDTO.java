package cz.cvut.fit.tjv.fuelapp.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class RecordDTO {
    private final Long id;
    private final Float price;
    private final Date date;
    private final Integer rating;
    private final Long fuelRatedId;
    private final Long userAuthorId;
    private final Long gasStationRecordId;
}
