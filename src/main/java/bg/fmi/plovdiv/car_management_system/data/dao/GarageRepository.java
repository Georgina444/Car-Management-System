package bg.fmi.plovdiv.car_management_system.data.dao;

import bg.fmi.plovdiv.car_management_system.data.model.GarageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GarageRepository extends JpaRepository<GarageEntity, Long> {
    //GarageEntity - the entity this repository manages
    //Long - the type of the primary key

    // Spring Data JPA automatically generates this query based on the method name
    List<GarageEntity> findByIsActiveTrue();

    // Custom Query - uses JPQL(Java Persistence Query Language)
    @Query("SELECT g FROM GarageEntity g WHERE "
            + "(:city IS NULL OR g.city LIKE CONCAT(:city, '%')) AND "  // finds cities that start with given input
            + "g.isActive = true")
    List<GarageEntity> findByCityAndIsActiveTrue(@Param("city")String city);
    Optional<GarageEntity> findByIdAndIsActiveTrue(Long id);

}
