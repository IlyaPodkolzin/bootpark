package com.ilyamorozov.bootpark.mapper;

import com.ilyamorozov.bootpark.dto.BookedSlotDto;
import com.ilyamorozov.bootpark.entity.BookedSlot;
import com.ilyamorozov.bootpark.entity.Parking;
import com.ilyamorozov.bootpark.entity.UserEntity;
import com.ilyamorozov.bootpark.repository.BookedSlotRepository;
import com.ilyamorozov.bootpark.repository.ParkingRepository;
import com.ilyamorozov.bootpark.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@Data
public class BookedSlotMapper {

    private final UserRepository userRepository;
    private final ParkingRepository parkingRepository;

    // Конструктор для внедрения зависимостей
    @Autowired
    public BookedSlotMapper(UserRepository userRepository, ParkingRepository parkingRepository) {
        this.userRepository = userRepository;
        this.parkingRepository = parkingRepository;
    }

    public BookedSlotDto toBookedSlotDto(BookedSlot bookedSlot) {
        return new BookedSlotDto(
                bookedSlot.getId(),
                bookedSlot.getParking().getId(),
                bookedSlot.getUserEntity().getId(),
                bookedSlot.getDateOfEnd()
        );
    }

    public BookedSlot toBookedSlot(BookedSlotDto bookedSlotDto) {
        UserEntity user = userRepository.findById(bookedSlotDto.getUserEntityId())  // По айди ищем юзера и парковку
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + bookedSlotDto.getUserEntityId()));

        Parking parking = parkingRepository.findById(bookedSlotDto.getParkingId())
                .orElseThrow(() -> new RuntimeException("Parking not found with ID: " + bookedSlotDto.getParkingId()));

        return new BookedSlot(
                bookedSlotDto.getId(),
                parking,
                user,
                bookedSlotDto.getDateOfEnd()
        );
    }
}