package bg.fmi.plovdiv.car_management_system.controllers;


import bg.fmi.plovdiv.car_management_system.data.requests.GarageRequest;
import bg.fmi.plovdiv.car_management_system.data.responses.GarageDailyAvailabilityResponse;
import bg.fmi.plovdiv.car_management_system.data.responses.GarageResponse;
import bg.fmi.plovdiv.car_management_system.services.GarageService;
import bg.fmi.plovdiv.car_management_system.services.ReportService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/garages")
@CrossOrigin(origins = "http://localhost:3000")
public class GarageController {

    private final GarageService garageService;
    private final ReportService reportService;

    public GarageController(GarageService garageService, ReportService reportService) {
        this.garageService = garageService;
        this.reportService = reportService;
    }

    @PostMapping
    public ResponseEntity<GarageResponse> createGarage(@Valid @RequestBody GarageRequest request) {
        try {
            GarageResponse savedItem = garageService.createGarage(request);
            return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<GarageResponse>> getGarages(@RequestParam(required = false) String city) {
        try {
            List<GarageResponse> garages;
            if (city != null) {
                garages = garageService.getGaragesByCity(city);
            } else {
                garages = garageService.getAllGarages();
            }
            return new ResponseEntity<>(garages, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GarageResponse> getGarageById(@PathVariable Long id) {
        try {
            GarageResponse item = garageService.getGarageById(id);
            if (item == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(item, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<GarageResponse> updateGarage(@PathVariable Long id, @Valid @RequestBody GarageRequest request) {
        try {
            GarageResponse updatedItem = garageService.updateGarage(id, request);
            if (updatedItem == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(updatedItem, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteGarage(@PathVariable Long id) {
        try {
            GarageResponse deletedItem = garageService.deleteGarage(id);
            if (deletedItem == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/dailyAvailabilityReport")
    public ResponseEntity<List<GarageDailyAvailabilityResponse>> getDailyAvailabilityReport(
            @RequestParam(required = true) Long garageId,
            @RequestParam(required = true) LocalDate startDate,
            @RequestParam(required = true) LocalDate endDate) {
        try {
            List<GarageDailyAvailabilityResponse> report = reportService.getDailyAvailabilityReport(garageId, startDate, endDate);
            return new ResponseEntity<>(report, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
