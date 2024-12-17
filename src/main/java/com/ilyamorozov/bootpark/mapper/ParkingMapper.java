package com.ilyamorozov.bootpark.mapper;

import com.ilyamorozov.bootpark.dto.ParkingDto;
import com.ilyamorozov.bootpark.entity.BookedSlot;
import com.ilyamorozov.bootpark.entity.Parking;
import com.ilyamorozov.bootpark.repository.BookedSlotRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Data
public class ParkingMapper {

    private final BookedSlotRepository bookedSlotRepository;

    @Autowired
    public ParkingMapper(BookedSlotRepository bookedSlotRepository) {
        this.bookedSlotRepository = bookedSlotRepository;
    }

    public ParkingDto toParkingDto(Parking parking) {
        return new ParkingDto(
                parking.getId(),
                parking.getName(),
                parking.getAddress(),
                parking.getAvailableSlotsAmount(),
                parking.getParkingSlotsAmount(),
                parking.getBookedSlots()  // передаем ТОЛЬКО АЙДИШНИКИ!!!! иначе будет рекурсия
                        .stream()
                        .map(BookedSlot::getId)
                        .collect(Collectors.toList())
        );
    }

    public Parking toParking(ParkingDto parkingDto) {
        return new Parking(
                parkingDto.getId(),
                parkingDto.getName(),
                parkingDto.getAddress(),
                parkingDto.getAvailableSlotsAmount(),
                parkingDto.getParkingSlotsAmount(),
                parkingDto.getBookedSlotsIds()  // по айдишникам получаем все bookedSlots
                        .stream()
                        .map(bookedSlotRepository::findById)
                        .toList()
                        .stream()
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .collect(Collectors.toList())
        );
    }
}
