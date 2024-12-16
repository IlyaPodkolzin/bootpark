package com.ilyamorozov.bootpark.mapper;

import com.ilyamorozov.bootpark.dto.ParkingDto;
import com.ilyamorozov.bootpark.entity.Parking;

public class ParkingMapper {

    public static ParkingDto toParkingDto(Parking parking) {
        return new ParkingDto(
                parking.getId(),
                parking.getName(),
                parking.getAddress(),
                parking.getAvailableSlotsAmount(),
                parking.getParkingSlotsAmount(),
                parking.getBookedSlots()
        );
    }

    public static Parking toParking(ParkingDto parkingDto) {
        return new Parking(
                parkingDto.getId(),
                parkingDto.getName(),
                parkingDto.getAddress(),
                parkingDto.getAvailableSlotsAmount(),
                parkingDto.getParkingSlotsAmount(),
                parkingDto.getBookedSlots()
        );
    }
}
