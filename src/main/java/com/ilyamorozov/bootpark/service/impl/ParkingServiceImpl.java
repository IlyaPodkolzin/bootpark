package com.ilyamorozov.bootpark.service.impl;

import com.ilyamorozov.bootpark.dto.ParkingDto;
import com.ilyamorozov.bootpark.entity.Parking;
import com.ilyamorozov.bootpark.exception.ResourceNotFoundException;
import com.ilyamorozov.bootpark.mapper.ParkingMapper;
import com.ilyamorozov.bootpark.repository.ParkingRepository;
import com.ilyamorozov.bootpark.service.ParkingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ParkingServiceImpl implements ParkingService {

    private ParkingRepository parkingRepository;

    @Override
    public ParkingDto createParking(ParkingDto parkingDto) {

        Parking parking = ParkingMapper.toParking(parkingDto);
        Parking savedParking = parkingRepository.save(parking);
        return ParkingMapper.toParkingDto(savedParking);
    }

    @Override
    public ParkingDto getParkingById(Long parkingId) {
        Parking parking = parkingRepository.findById(parkingId)
                .orElseThrow(() -> new ResourceNotFoundException("Parking with id " + parkingId + " not found"));
        return ParkingMapper.toParkingDto(parking);
    }

    @Override
    public List<ParkingDto> getAllParkings() {
        List<Parking> parkings = parkingRepository.findAll();
        return parkings.stream().map(ParkingMapper::toParkingDto)
                .collect(Collectors.toList());
    }

    @Override
    public ParkingDto updateParking(Long id, ParkingDto updatedParkingDto) {
        Parking parking = parkingRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Parking with id " + id + " not found")
        );

        parking.setName(updatedParkingDto.getName());
        parking.setAddress(updatedParkingDto.getAddress());
        parking.setAvailableSlotsAmount(updatedParkingDto.getAvailableSlotsAmount());
        parking.setParkingSlotsAmount(updatedParkingDto.getParkingSlotsAmount());
        parking.setBookedSlots(updatedParkingDto.getBookedSlots());

        Parking updatedParkingObj = parkingRepository.save(parking);

        return ParkingMapper.toParkingDto(updatedParkingObj);
    }

    @Override
    public void deleteParking(Long id) {
        Parking parking = parkingRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Parking with id " + id + " not found")
        );

        parkingRepository.deleteById(id);
    }
}
