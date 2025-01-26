package bg.fmi.plovdiv.car_management_system.data.requests;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class MaintenanceRequest {


    private Long garageId;
    private Long carId;
    @NotBlank
    @Size(min = 1, max = 100, message = "Service type must be between 1 and 100 characters")
    private String serviceType;
    @FutureOrPresent(message = "Scheduled date must be in the future or present")
    private LocalDate scheduledDate;

    public MaintenanceRequest() {
    }

    public MaintenanceRequest(Long garageId, Long carId, String serviceType, LocalDate scheduledDate) {
        this.garageId = garageId;
        this.carId = carId;
        this.serviceType = serviceType;
        this.scheduledDate = scheduledDate;
    }

    public Long getGarageId() {
        return garageId;
    }

    public void setGarageId(Long garageId) {
        this.garageId = garageId;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public LocalDate getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(LocalDate scheduledDate) {
        this.scheduledDate = scheduledDate;
    }
}
