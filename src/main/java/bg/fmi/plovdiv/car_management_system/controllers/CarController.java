package bg.fmi.plovdiv.car_management_system.controllers;


import bg.fmi.plovdiv.car_management_system.data.requests.CarRequest;
import bg.fmi.plovdiv.car_management_system.data.responses.CarResponse;
import bg.fmi.plovdiv.car_management_system.services.CarService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
// allows requests from specific origins(domains)
@CrossOrigin(origins = "http://localhost:3000")  // default port for frontend applications
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public ResponseEntity<List<CarResponse>> getAllCarsAtGarageMadeFromYearToYear(
            @RequestParam(required = false) String carMake,
            @RequestParam(required = false) Long garageId,
            @RequestParam(required = false) Integer fromYear,
            @RequestParam(required = false) Integer toYear) {
        try {
            List<CarResponse> cars = carService.getAllCarsAtGarageMadeFromYearToYear(carMake, garageId, fromYear, toYear);
            return new ResponseEntity<>(cars, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<CarResponse> createCar(@Valid @RequestBody CarRequest request) {
        try {
            CarResponse savedItem = carService.createCar(request);
            return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarResponse> getCarById(@PathVariable Long id) {
        try {
            CarResponse car = carService.getCarById(id);
            if (car == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(car, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarResponse> updateCar(@PathVariable Long id, @Valid @RequestBody CarRequest request) {
        try {
            CarResponse updatedItem = carService.updateCar(id, request);
            if (updatedItem == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(updatedItem, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CarResponse> deleteCar(@PathVariable Long id) {
        try {
            CarResponse deletedItem = carService.deleteCar(id);
            if (deletedItem == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
