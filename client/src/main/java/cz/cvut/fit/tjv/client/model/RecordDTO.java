package cz.cvut.fit.tjv.client.model;

import java.util.Date;

public class RecordDTO {
    private  Long id;
    private  Float price;
    private  Date date;
    private  Integer rating;
    private  Long fuelRatedId;
    private  Long userAuthorId;
    private  Long gasStationRecordId;


    public RecordDTO(Long id, Float price, Date date, Integer rating, Long fuelRatedId, Long userAuthorId, Long gasStationRecordId) {
        this.id = id;
        this.price = price;
        this.date = date;
        this.rating = rating;
        this.fuelRatedId = fuelRatedId;
        this.userAuthorId = userAuthorId;
        this.gasStationRecordId = gasStationRecordId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Long getFuelRatedId() {
        return fuelRatedId;
    }

    public void setFuelRatedId(Long fuelRatedId) {
        this.fuelRatedId = fuelRatedId;
    }

    public Long getUserAuthorId() {
        return userAuthorId;
    }

    public void setUserAuthorId(Long userAuthorId) {
        this.userAuthorId = userAuthorId;
    }

    public Long getGasStationRecordId() {
        return gasStationRecordId;
    }

    public void setGasStationRecordId(Long gasStationRecordId) {
        this.gasStationRecordId = gasStationRecordId;
    }

    @Override
    public String toString() {
        return "RecordDTO{" +
                "id=" + id +
                ", price=" + price +
                ", date=" + date +
                ", rating=" + rating +
                ", fuelRatedId=" + fuelRatedId +
                ", userAuthorId=" + userAuthorId +
                ", gasStationRecordId=" + gasStationRecordId +
                '}' + "\n";
    }
}
