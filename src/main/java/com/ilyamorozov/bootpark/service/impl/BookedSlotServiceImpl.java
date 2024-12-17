package com.ilyamorozov.bootpark.service.impl;

import com.ilyamorozov.bootpark.dto.BookedSlotDto;
import com.ilyamorozov.bootpark.entity.BookedSlot;
import com.ilyamorozov.bootpark.entity.Parking;
import com.ilyamorozov.bootpark.entity.UserEntity;
import com.ilyamorozov.bootpark.exception.ResourceNotFoundException;
import com.ilyamorozov.bootpark.mapper.BookedSlotMapper;
import com.ilyamorozov.bootpark.repository.BookedSlotRepository;
import com.ilyamorozov.bootpark.repository.ParkingRepository;
import com.ilyamorozov.bootpark.repository.UserRepository;
import com.ilyamorozov.bootpark.service.BookedSlotService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookedSlotServiceImpl implements BookedSlotService {

    private BookedSlotRepository bookedSlotRepository;
    private UserRepository userRepository;
    private ParkingRepository parkingRepository;
    private BookedSlotMapper bookedSlotMapper;

    @Override
    public BookedSlotDto createBookedSlot(BookedSlotDto bookedSlotDto) {
        BookedSlot bookedSlot = bookedSlotMapper.toBookedSlot(bookedSlotDto);
        BookedSlot savedBookedSlot = bookedSlotRepository.save(bookedSlot);
        return bookedSlotMapper.toBookedSlotDto(savedBookedSlot);
    }

    @Override
    public BookedSlotDto getBookedSlotById(Long id) {
        BookedSlot bookedSlot = bookedSlotRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Booked slot with id " + id + " not found"));
        return bookedSlotMapper.toBookedSlotDto(bookedSlot);
    }

    @Override
    public List<BookedSlotDto> getBookedSlotsByUserId(Long userId) {
        List<BookedSlot> bookedSlots = bookedSlotRepository.findByUserEntity_Id(userId);
        return bookedSlots.stream().map(bookedSlotMapper::toBookedSlotDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookedSlotDto> getAllBookedSlots() {
        List<BookedSlot> bookedSlots = bookedSlotRepository.findAll();
        return bookedSlots.stream().map(bookedSlotMapper::toBookedSlotDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookedSlotDto updateBookedSlot(Long id, BookedSlotDto updatedBookedSlotDto) {
        BookedSlot bookedSlot = bookedSlotRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Booked slot with id " + id + " not found"));



        UserEntity user = new UserEntity();  // получаем юзера из его айдишника
        Optional<UserEntity> optionalUserEntity = userRepository.findById(updatedBookedSlotDto.getUserEntityId());
        if (optionalUserEntity.isPresent()) {
            user = optionalUserEntity.get();
        }

        Parking parking = new Parking();  // получаем парковку из ее айдишника
        Optional<Parking> optionalParking = parkingRepository.findById(updatedBookedSlotDto.getParkingId());
        if (optionalParking.isPresent()) {
            parking = optionalParking.get();
        }



        bookedSlot.setParking(parking);
        bookedSlot.setUserEntity(user);

        BookedSlot updatedBookedSlotObj = bookedSlotRepository.save(bookedSlot);

        return bookedSlotMapper.toBookedSlotDto(updatedBookedSlotObj);
    }

    @Override
    public void deleteBookedSlot(Long id) {
        BookedSlot bookedSlot = bookedSlotRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Booked slot with id " + id + " not found"));

        bookedSlotRepository.deleteById(id);
    }
}
