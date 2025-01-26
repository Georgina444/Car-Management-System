package bg.fmi.plovdiv.car_management_system.data.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "car")
public class CarEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "car_entity_sequence")
    @SequenceGenerator(name = "car_entity_sequence", sequenceName = "car_entity_sequence", allocationSize = 1)
    private Long id;

    @CreationTimestamp
    private LocalDateTime created_at;

    @UpdateTimestamp
    private LocalDateTime updated_at;

    @Column(name = "is_active", nullable = false, length = 100)
    private Boolean isActive;

    @Column(name = "make", nullable = false, length = 100)
    private String make;

    @Column(name = "model", nullable = false, length = 100)
    private String model;

    @Column(name = "production_year", nullable = false)
    private Integer productionYear;

    @Column(name = "license_plate", nullable = false, length = 10)
    private String licensePlate;


    @ManyToMany // a car can be registered at many garages and a garage can have many cars
    private List<GarageEntity> garages = new ArrayList<>();

    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}

    public LocalDateTime getCreatedAt(){return created_at;}
    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdatedAt(){return updated_at;}
    public void setUpdatedAt(LocalDateTime updated_at){
        this.updated_at = updated_at;
    }

    public Boolean getIsActive(){return isActive;}
    public void setIsActive(Boolean isActive){
        this.isActive = isActive;
    }

    public String getMake(){return make;}
    public void setMake(String make){
        this.make = make;
    }

    public String getModel(){return model;}
    public void setModel(String model){
        this.model = model;
    }

    public Integer getProductionYear(){return productionYear;}
    public void setProductionYear(Integer productionYear){
        this.productionYear = productionYear;
    }

    public String getLicensePlate(){return licensePlate;}
    public void setLicensePlate(String licensePlate){
        this.licensePlate = licensePlate;
    }

    public List<GarageEntity> getGarages(){return  garages;}
    public void setGarages(List<GarageEntity> garages){
        this.garages = garages;
    }

}
