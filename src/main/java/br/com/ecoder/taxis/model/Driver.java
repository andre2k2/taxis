package br.com.ecoder.taxis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String name;

    @Column(length = 10)
    private String carPlate;

    @Column
    private Double latitude;

    @Column
    private Double longitude;

    @Column
    private Boolean driverAvailable;

    public Driver() {}

    public Driver(Long id, String name, String carPlate, Double latitude, Double longitude, Boolean driverAvailable) {
        this.id = id;
        this.name = name;
        this.carPlate = carPlate;
        this.latitude = latitude;
        this.longitude = longitude;
        this.driverAvailable = driverAvailable;
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

    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Boolean getDriverAvailable() {
        return driverAvailable;
    }

    public void setDriverAvailable(Boolean driverAvailable) {
        this.driverAvailable = driverAvailable;
    }

}
