package bg.fmi.plovdiv.car_management_system.controllers;

import bg.fmi.plovdiv.car_management_system.data.requests.MaintenanceRequest;
import bg.fmi.plovdiv.car_management_system.data.responses.MaintenanceMonthlyRequestsResponse;
import bg.fmi.plovdiv.car_management_system.data.responses.MaintenanceResponse;
import bg.fmi.plovdiv.car_management_system.services.MaintenanceService;
import bg.fmi.plovdiv.car_management_system.services.ReportService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/maintenance")
@CrossOrigin(origins = "http://localhost:3000")
public class MaintenanceController {

    private final MaintenanceService maintenanceService;
    private final ReportService reportService;

    public MaintenanceController(MaintenanceService maintenanceService, ReportService reportService) {
        this.maintenanceService = maintenanceService;
        this.reportService = reportService;
    }

    @PostMapping
    public ResponseEntity<MaintenanceResponse> createMaintenance(@Valid @RequestBody MaintenanceRequest request) {
        try {
            MaintenanceResponse savedMaintenance = maintenanceService.createMaintenance(request);
            if (savedMaintenance == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(savedMaintenance, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaintenanceResponse> getMaintenance(@PathVariable Long id) {
        try {
            MaintenanceResponse maintenance = maintenanceService.getMaintenance(id);
            if (maintenance == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(maintenance, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaintenanceResponse> updateMaintenance(@PathVariable Long id, @Valid @RequestBody MaintenanceRequest request) {
        try {
            MaintenanceResponse updatedMaintenance = maintenanceService.updateMaintenance(id, request);
            if (updatedMaintenance == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(updatedMaintenance, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MaintenanceResponse> deleteMaintenance(@PathVariable Long id) {
        try {
            MaintenanceResponse deletedMaintenance = maintenanceService.deleteMaintenance(id);
            if (deletedMaintenance == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<MaintenanceResponse>> searchMaintenance(
            @RequestParam(required = false) Long carId,
            @RequestParam(required = false) Long garageId,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate) {
        try {
            List<MaintenanceResponse> maintenanceRecords = maintenanceService.searchMaintenance(carId, garageId, startDate, endDate);
            return new ResponseEntity<>(maintenanceRecords, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/monthlyRequestsReport")
    public ResponseEntity<List<MaintenanceMonthlyRequestsResponse>> getMonthlyRequestsReportsByGarage(
            @RequestParam(required = true) Long garageId,
            @RequestParam(required = true) String startMonth,
            @RequestParam(required = true) String endMonth) {
        try {
            List<MaintenanceMonthlyRequestsResponse> maintenanceMonthlyRequests = reportService.getMonthlyMaintenanceReport(garageId, startMonth, endMonth);
            return new ResponseEntity<>(maintenanceMonthlyRequests, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
