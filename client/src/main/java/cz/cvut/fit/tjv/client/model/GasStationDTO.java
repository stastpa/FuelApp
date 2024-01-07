package cz.cvut.fit.tjv.client.model;

import java.util.List;

public class GasStationDTO {
    private Long id;
    private String name;
    private String country;
    private Integer psc;
    private String city;
    private String street;
    private String number;
    private String phoneNumber;
    private List<Long> fuelIds;
    private List<Long> recordIds;

    public GasStationDTO(Long id, String name, String country, Integer psc, String city, String street, String number, String phoneNumber, List<Long> fuelIds, List<Long> recordIds) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.psc = psc;
        this.city = city;
        this.street = street;
        this.number = number;
        this.phoneNumber = phoneNumber;
        this.fuelIds = fuelIds;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getPsc() {
        return psc;
    }

    public void setPsc(Integer psc) {
        this.psc = psc;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Long> getFuelIds() {
        return fuelIds;
    }

    public void setFuelIds(List<Long> fuelIds) {
        this.fuelIds = fuelIds;
    }

    public List<Long> getRecordIds() {
        return recordIds;
    }

    public void setRecordIds(List<Long> recordIds) {
        this.recordIds = recordIds;
    }

    @Override
    public String toString() {
        return "GasStationDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", psc=" + psc +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", number='" + number + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", fuelIds=" + fuelIds +
                ", recordIds=" + recordIds +
                '}' + "\n";
    }
}
