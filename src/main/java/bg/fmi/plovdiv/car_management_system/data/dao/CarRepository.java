package bg.fmi.plovdiv.car_management_system.data.dao;


import bg.fmi.plovdiv.car_management_system.data.model.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<CarEntity, Long> {

    Optional<CarEntity> findByIdAndIsActiveTrue(Long id);

    // if a parameter in NULL it is ignored by the filtering
    @Query("SELECT c FROM CarEntity c JOIN c.garages g WHERE "
            + "(:make IS NULL OR c.make LIKE CONCAT(:make, '%')) AND "
            + "(:garageId IS NULL OR g.id = :garageId) AND "
            + "(:fromYear IS NULL OR c.productionYear >= :fromYear) AND "
            + "(:toYear IS NULL OR c.productionYear <= :toYear) AND "
            + "c.isActive = true")

    // returns a list of CarEntity objects that match the filters
    List<CarEntity> findAllWithOptionalParameters(
            @Param("make") String make,
            @Param("garageId") Long garageId,
            @Param("fromYear") Integer fromYear,
            @Param("toYear") Integer toYear
    );
}
