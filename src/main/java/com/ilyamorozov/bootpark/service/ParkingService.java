package com.ilyamorozov.bootpark.service;

import com.ilyamorozov.bootpark.dto.ParkingDto;

import java.util.List;

public interface ParkingService {
    ParkingDto createParking(ParkingDto parkingDto);
    ParkingDto getParkingById(Long parkingId);
    List<ParkingDto> getAllParkings();
    ParkingDto updateParking(Long id, ParkingDto updatedParkingDto);
    void deleteParking(Long id);
}
