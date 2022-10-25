package com.example.KTS.Helper;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Location {
    @Id
    @GeneratedValue
    private Long Id;
    private Double longitude;
    private Double latitude;
    private String address;
    private String addressNumber;
    private String city;


    public Location(){

    }
    public Location(Long id, Double longitude, Double latitude, String address, String addressNumber, String city) {
        Id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
        this.addressNumber = addressNumber;
        this.city = city;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(String addressNumber) {
        this.addressNumber = addressNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
