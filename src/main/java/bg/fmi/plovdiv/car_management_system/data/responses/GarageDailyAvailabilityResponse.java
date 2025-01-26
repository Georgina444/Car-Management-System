package bg.fmi.plovdiv.car_management_system.data.responses;

import java.time.LocalDate;

public class GarageDailyAvailabilityResponse {

    private LocalDate date;
    private Integer requests;
    private Integer availableCapacity;

    public GarageDailyAvailabilityResponse(LocalDate date, Integer requests, Integer availableCapacity) {
        this.date = date;
        this.requests = requests;
        this.availableCapacity = availableCapacity;
    }

    public LocalDate getDate() {
        return date;
    }

    public Integer getRequests() {
        return requests;
    }

    public Integer getAvailableCapacity() {
        return availableCapacity;
    }
}
