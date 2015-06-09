package br.com.ecoder.taxis.endpoint.driver;

import javax.validation.constraints.NotNull;

public class DriverStatusRequest {

    @NotNull
    private Long driverId;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    @NotNull
    private Boolean driverAvailable;

    public DriverStatusRequest() {}

    public DriverStatusRequest(Long driverId, Double latitude, Double longitude, Boolean driverAvailable) {
        this.driverId = driverId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.driverAvailable = driverAvailable;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
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
