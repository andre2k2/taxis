package br.com.ecoder.taxis.endpoint.driver;

public class DriverStatusResponse {

    private Long driverId;

    private Double latitude;

    private Double longitude;

    private Boolean driverAvailable;

    public DriverStatusResponse() {}

    public DriverStatusResponse(Long driverId, Double latitude, Double longitude, Boolean driverAvailable) {
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
