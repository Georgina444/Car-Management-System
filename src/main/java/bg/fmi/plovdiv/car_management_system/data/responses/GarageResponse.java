package bg.fmi.plovdiv.car_management_system.data.responses;

public class GarageResponse {

    private Long id;
    private String name;
    private String location;
    private String city;
    private Integer capacity;

    public GarageResponse(Long id, String name, String location, String city, Integer capacity) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.city = city;
        this.capacity = capacity;
    }

    public Long getId() {
        return id;
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
