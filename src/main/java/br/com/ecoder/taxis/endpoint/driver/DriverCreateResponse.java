package br.com.ecoder.taxis.endpoint.driver;

public class DriverCreateResponse {

    private Long driverId;

    public DriverCreateResponse() {}

    public DriverCreateResponse(Long driverId) {
        this.driverId = driverId;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

}
