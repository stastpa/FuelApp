package cz.cvut.fit.tjv.client.model;

import java.util.List;


public class FuelDTO {
    private Long id;
    private String name;
    private List<Long> gasStationIds;
    private List<Long> recordIds;

    public FuelDTO(Long id, String name, List<Long> gasStationIds, List<Long> recordIds) {
        this.id = id;
        this.name = name;
        this.gasStationIds = gasStationIds;
        this.recordIds = recordIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getGasStationIds() {
        return gasStationIds;
    }

    public void setGasStationIds(List<Long> gasStationIds) {
        this.gasStationIds = gasStationIds;
    }

    public List<Long> getRecordIds() {
        return recordIds;
    }

    public void setRecordIds(List<Long> recordIds) {
        this.recordIds = recordIds;
    }

    @Override
    public String toString() {
        return "FuelDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gasStationIds=" + gasStationIds +
                ", recordIds=" + recordIds +
                '}' + "\n";
    }
}
