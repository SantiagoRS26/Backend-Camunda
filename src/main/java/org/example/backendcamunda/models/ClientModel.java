package org.example.backendcamunda.models;

public class ClientModel {
    private String clientName;
    private Integer clientAge;
    private String brandVehicle;
    private String vehicleReference;
    private Integer numberAccidents;

    // Constructors
    public ClientModel() {}

    public ClientModel(String clientName, Integer clientAge, String brandVehicle, String vehicleReference, Integer numberAccidents) {
        this.clientName = clientName;
        this.clientAge = clientAge;
        this.brandVehicle = brandVehicle;
        this.vehicleReference = vehicleReference;
        this.numberAccidents = numberAccidents;
    }

    // Getters and setters
    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Integer getClientAge() {
        return clientAge;
    }

    public void setClientAge(Integer clientAge) {
        this.clientAge = clientAge;
    }

    public String getBrandVehicle() {
        return brandVehicle;
    }

    public void setBrandVehicle(String brandVehicle) {
        this.brandVehicle = brandVehicle;
    }

    public String getVehicleReference() {
        return vehicleReference;
    }

    public void setVehicleReference(String vehicleReference) {
        this.vehicleReference = vehicleReference;
    }

    public Integer getNumberAccidents() {
        return numberAccidents;
    }

    public void setNumberAccidents(Integer numberAccidents) {
        this.numberAccidents = numberAccidents;
    }
}
