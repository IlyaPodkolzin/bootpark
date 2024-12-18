package com.ilyamorozov.bootpark.service.impl;

import com.ilyamorozov.bootpark.dto.ParkingDto;
import com.ilyamorozov.bootpark.entity.Parking;
import com.ilyamorozov.bootpark.exception.ResourceNotFoundException;
import com.ilyamorozov.bootpark.mapper.ParkingMapper;
import com.ilyamorozov.bootpark.repository.BookedSlotRepository;
import com.ilyamorozov.bootpark.repository.ParkingRepository;
import com.ilyamorozov.bootpark.service.ParkingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ParkingServiceImpl implements ParkingService {

    private ParkingRepository parkingRepository;
    private BookedSlotRepository bookedSlotRepository;
    private ParkingMapper parkingMapper;

    @Override
    public ParkingDto createParking(ParkingDto parkingDto) {

        Parking parking = parkingMapper.toParking(parkingDto);
        Parking savedParking = parkingRepository.save(parking);
        return parkingMapper.toParkingDto(savedParking);
    }

    @Override
    public ParkingDto getParkingById(Long parkingId) {
        Parking parking = parkingRepository.findById(parkingId)
                .orElseThrow(() -> new ResourceNotFoundException("Parking with id " + parkingId + " not found"));
        return parkingMapper.toParkingDto(parking);
    }

    @Override
    public List<ParkingDto> getAllParkings() {
        List<Parking> parkings = parkingRepository.findAll();
        return parkings.stream().map(parkingMapper::toParkingDto)
                .collect(Collectors.toList());
    }

    @Override
    public ParkingDto updateParking(Long id, ParkingDto updatedParkingDto) {
        Parking parking = parkingRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Parking with id " + id + " not found")
        );

        parking.setName(updatedParkingDto.getName());
        parking.setAddress(updatedParkingDto.getAddress());
        parking.setAvailableSlotsAmount(  // число оставшихся мест меняется на столько же, на сколько поменялось общее число мест
                parking.getAvailableSlotsAmount() -
                        (parking.getParkingSlotsAmount() - updatedParkingDto.getParkingSlotsAmount()));
        parking.setParkingSlotsAmount(updatedParkingDto.getParkingSlotsAmount());

        Parking updatedParkingObj = parkingRepository.save(parking);

        return parkingMapper.toParkingDto(updatedParkingObj);
    }

    @Override
    public ParkingDto updateParkingAvailablePlacesOnly(Long id, Integer availablePlaces) {
        Parking parking = parkingRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Parking with id " + id + " not found")
        );

        parking.setAvailableSlotsAmount(availablePlaces);

        Parking updatedParkingObj = parkingRepository.save(parking);
        return parkingMapper.toParkingDto(updatedParkingObj);
    }

    @Override
    public void deleteParking(Long id) {
        Parking parking = parkingRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Parking with id " + id + " not found")
        );

        parkingRepository.deleteById(id);
    }
}
