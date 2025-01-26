package bg.fmi.plovdiv.car_management_system.data.dao;

import bg.fmi.plovdiv.car_management_system.data.model.MaintenanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MaintenanceRepository extends JpaRepository<MaintenanceEntity, Long> {

    Optional<MaintenanceEntity> findByIdAndIsActiveTrue(Long id);
    @Query("SELECT m FROM MaintenanceEntity m WHERE "
            + "m.isActive = true AND "
            + "(:carId IS NULL OR m.car.id = :carId) AND "
            + "(:garageId IS NULL OR m.garage.id = :garageId) AND "
            + "(:startDate IS NULL OR m.scheduledDate >= :startDate) AND "
            + "(:endDate IS NULL OR m.scheduledDate <= :endDate)")
    List<MaintenanceEntity> findByCarIdAndGarageIdAndScheduledDateBetween(
            @Param("carId") Long carId,
            @Param("garageId") Long garageId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);
    @Query("SELECT m FROM MaintenanceEntity m WHERE "
            + "m.isActive = true AND "
            + "m.garage.id = :garageId AND "
            + "m.scheduledDate BETWEEN :startDate AND :endDate")
    List<MaintenanceEntity> findAllByGarageIdAndScheduledDateBetween(
            @Param("garageId") Long garageId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);
    @Query("SELECT m FROM MaintenanceEntity m WHERE "
            + "m.isActive = true AND "
            + "m.garage.id = :garageId AND "
            + "m.scheduledDate = :date")
    List<MaintenanceEntity> findAllByGarageIdAndScheduledDate(Long garageId, LocalDate date);
    @Query("SELECT m FROM MaintenanceEntity m WHERE "
            + "m.isActive = true AND "
            + "m.car.id = :carId AND "
            + "m.scheduledDate = :date")
    List<MaintenanceEntity> findAllByCarIdAndScheduledDate(Long carId, LocalDate date);
}