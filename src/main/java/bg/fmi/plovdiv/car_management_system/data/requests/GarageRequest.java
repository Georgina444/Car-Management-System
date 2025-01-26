package bg.fmi.plovdiv.car_management_system.data.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class GarageRequest {

    @NotBlank(message = "Name is mandatory")
    @Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters")
    private String name;
    @NotBlank(message = "Location is mandatory")
    @Size(min = 1, max = 255, message = "Location must be between 1 and 255 characters")
    private String location;
    @NotBlank(message = "City is mandatory")
    @Size(min = 1, max = 100, message = "City must be between 1 and 100 characters")
    private String city;
    @Min(value = 1, message = "Capacity must be greater than 0")
    private Integer capacity;

    public GarageRequest() {
    }

    public GarageRequest(String name, String location, String city, Integer capacity) {
        this.name = name;
        this.location = location;
        this.city = city;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getCity() {
        return city;
    }

    public Integer getCapacity() {
        return capacity;
    }
}
