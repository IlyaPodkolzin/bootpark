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
    @PostMapping
    public ResponseEntity<ParkingDto> createPraking(@RequestBody ParkingDto parkingDto) {
        ParkingDto savedParking = parkingService.createParking(parkingDto);
        return new ResponseEntity<>(savedParking, HttpStatus.CREATED);
    }

    // Build Get Parking REST API
    @GetMapping("{id}")
    public ResponseEntity<ParkingDto> getParkingById(@PathVariable("id") Long id) {
        ParkingDto parkingDto = parkingService.getParkingById(id);
        return ResponseEntity.ok(parkingDto);
    }

    // Build Get All Parkings REST Api
    @GetMapping
    public ResponseEntity<List<ParkingDto>> getAllParkings() {
        List<ParkingDto> parkingDtos = parkingService.getAllParkings();
        return ResponseEntity.ok(parkingDtos);
    }

    // Build Update Parking REST API
    @PutMapping("{id}")
    public ResponseEntity<ParkingDto> updateParking(@PathVariable("id") Long id,
                                                    @RequestBody ParkingDto updatedParkingDto) {
        ParkingDto parkingDto = parkingService.updateParking(id, updatedParkingDto);
        return ResponseEntity.ok(parkingDto);
    }

    // Build Delete Parking REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteParking(@PathVariable("id") Long id) {
        parkingService.deleteParking(id);
        return ResponseEntity.ok("Parking deleted");
    }
}
