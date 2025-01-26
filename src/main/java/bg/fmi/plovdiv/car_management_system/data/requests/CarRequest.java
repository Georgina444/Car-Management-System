package bg.fmi.plovdiv.car_management_system.data.requests;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public class CarRequest {

    @NotBlank(message = "Make is mandatory")
    @Size(min = 1, max = 100, message = "Make must be between 1 and 100 characters")
    private String make;
    @NotBlank(message = "Model is mandatory")
    @Size(min = 1, max = 100, message = "Model must be between 1 and 100 characters")
    private String model;
    @Min(value = 1900, message = "Production year must be between 1900 and 2100")
    @Max(value = 2100, message = "Production year must be between 1900 and 2100")
    private Integer productionYear;
    @NotBlank(message = "License plate is mandatory")
    @Size(min = 1, max = 10, message = "License plate must be between 1 and 10 characters")
    private String licensePlate;
    private List<Long> garageIds;

    public CarRequest() {
    }

    public CarRequest(String make, String model, Integer productionYear, String licensePlate, List<Long> garageIds) {
        this.make = make;
        this.model = model;
        this.productionYear = productionYear;
        this.licensePlate = licensePlate;
        this.garageIds = garageIds;
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

    public List<Long> getGarageIds() {
        return garageIds;
    }


}
