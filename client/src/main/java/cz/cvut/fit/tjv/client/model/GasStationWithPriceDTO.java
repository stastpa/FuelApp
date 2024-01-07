package cz.cvut.fit.tjv.client.model;

public class GasStationWithPriceDTO {
    private String gasStationName;
    private String fuelName;
    private Float price;

    public GasStationWithPriceDTO(String gasStationName, String fuelName, Float price) {
        this.gasStationName = gasStationName;
        this.fuelName = fuelName;
        this.price = price;
    }

    public String getGasStationName() {
        return gasStationName;
    }

    public void setGasStationName(String gasStationName) {
        this.gasStationName = gasStationName;
    }

    public String getFuelName() {
        return fuelName;
    }

    public void setFuelName(String fuelName) {
        this.fuelName = fuelName;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "GasStationWithPriceDTO{" +
                "gasStationName='" + gasStationName + '\'' +
                ", fuelName='" + fuelName + '\'' +
                ", price=" + price +
                '}' + "\n";
    }
}