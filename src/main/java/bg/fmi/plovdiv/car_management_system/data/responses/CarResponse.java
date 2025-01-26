package bg.fmi.plovdiv.car_management_system.data.responses;

import java.util.List;

public class CarResponse {
    private Long id;
    private String make;
    private String model;
    private Integer productionYear;
    private String licensePlate;
    private List<GarageResponse> garages;

    public CarResponse(Long id, String make, String model, Integer productionYear, String licensePlate, List<GarageResponse> garages) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.productionYear = productionYear;
        this.licensePlate = licensePlate;
        this.garages = garages;
    }

    public Long getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public Integer getProductionYear() {
        return productionYear;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public List<GarageResponse> getGarages() {
        return garages;
    }
}
