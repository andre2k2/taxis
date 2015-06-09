package br.com.ecoder.taxis.endpoint.driver;

import javax.validation.constraints.NotNull;

public class DriverCreateRequest {

    @NotNull
    private String name;

    @NotNull
    private String carPlate;

    public DriverCreateRequest() {}

    public DriverCreateRequest(String name, String carPlate) {
        this.name = name;
        this.carPlate = carPlate;
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

}
