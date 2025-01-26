package bg.fmi.plovdiv.car_management_system.data.responses;

import java.time.LocalDate;

public class MaintenanceResponse {

    private Long id;
    private Long carId;
    private String carName;
    private String serviceType;
    private LocalDate scheduledDate;
    private Long garageId;
    private String garageName;

    public MaintenanceResponse(Long id, Long carId, String carName, String serviceType, LocalDate scheduledDate, Long garageId, String garageName) {
        this.id = id;
        this.carId = carId;
        this.carName = carName;
        this.serviceType = serviceType;
        this.scheduledDate = scheduledDate;
        this.garageId = garageId;
        this.garageName = garageName;
    }

    public Long getId() {
        return id;
    }

    public Long getCarId() {
        return carId;
    }

    public String getCarName() {
        return carName;
    }

    public String getServiceType() {
        return serviceType;
    }

    public LocalDate getScheduledDate() {
        return scheduledDate;
    }

    public Long getGarageId() {
        return garageId;
    }

    public String getGarageName() {
        return garageName;
    }
}
