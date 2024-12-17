package com.ilyamorozov.bootpark.controller;

import com.ilyamorozov.bootpark.dto.ParkingDto;
import com.ilyamorozov.bootpark.service.ParkingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")  // используется, чтобы клиент смог обратиться к серверу
@AllArgsConstructor
@RestController
@RequestMapping("/api/parkings")
public class ParkingController {

    private ParkingService parkingService;

    // Build Add Parking REST API
    @PostMapping("general")
    public ResponseEntity<ParkingDto> createPraking(@RequestBody ParkingDto parkingDto) {
        ParkingDto savedParking = parkingService.createParking(parkingDto);
        return new ResponseEntity<>(savedParking, HttpStatus.CREATED);
    }

    // Build Get Parking REST API
    @GetMapping("general/{id}")
    public ResponseEntity<ParkingDto> getParkingById(@PathVariable("id") Long id) {
        ParkingDto parkingDto = parkingService.getParkingById(id);
        return ResponseEntity.ok(parkingDto);
    }

    // Build Get All Parkings REST Api
    @GetMapping("general")
    public ResponseEntity<List<ParkingDto>> getAllParkings() {
        List<ParkingDto> parkingDtos = parkingService.getAllParkings();
        return ResponseEntity.ok(parkingDtos);
    }

    // Build Update Parking REST API
    @PutMapping("general/{id}")
    public ResponseEntity<ParkingDto> updateParking(@PathVariable("id") Long id,
                                                    @RequestBody ParkingDto updatedParkingDto) {
        ParkingDto parkingDto = parkingService.updateParking(id, updatedParkingDto);
        return ResponseEntity.ok(parkingDto);
    }

    // Обновление только количества оставшихся мест (разделение нужно, поскольку это действие разрешено не только для админа, но и для пользователя)
    @PutMapping("available_places_only/{id}")
    public ResponseEntity<ParkingDto> updateParkingAvailablePlacesOnly(@PathVariable("id") Long id,
                                                                       @RequestBody Integer availablePlaces) {
        ParkingDto parkingDto = parkingService.updateParkingAvailablePlacesOnly(id, availablePlaces);
        return ResponseEntity.ok(parkingDto);
    }

    // Build Delete Parking REST API
    @DeleteMapping("general/{id}")
    public ResponseEntity<String> deleteParking(@PathVariable("id") Long id) {
        parkingService.deleteParking(id);
        return ResponseEntity.ok("Parking deleted");
    }
}
