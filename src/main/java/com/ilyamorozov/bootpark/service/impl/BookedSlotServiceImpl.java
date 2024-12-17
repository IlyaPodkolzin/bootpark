package com.ilyamorozov.bootpark.service.impl;

import com.ilyamorozov.bootpark.dto.BookedSlotDto;
import com.ilyamorozov.bootpark.entity.BookedSlot;
import com.ilyamorozov.bootpark.exception.ResourceNotFoundException;
import com.ilyamorozov.bootpark.mapper.BookedSlotMapper;
import com.ilyamorozov.bootpark.repository.BookedSlotRepository;
import com.ilyamorozov.bootpark.service.BookedSlotService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookedSlotServiceImpl implements BookedSlotService {

    private BookedSlotRepository bookedSlotRepository;

    @Override
    public BookedSlotDto createBookedSlot(BookedSlotDto bookedSlotDto) {
        BookedSlot bookedSlot = BookedSlotMapper.toBookedSlot(bookedSlotDto);
        BookedSlot savedBookedSlot = bookedSlotRepository.save(bookedSlot);
        return BookedSlotMapper.toBookedSlotDto(savedBookedSlot);
    }

    @Override
    public BookedSlotDto getBookedSlotById(Long id) {
        BookedSlot bookedSlot = bookedSlotRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Booked slot with id " + id + " not found"));
        return  BookedSlotMapper.toBookedSlotDto(bookedSlot);
    }

    @Override
    public List<BookedSlotDto> getAllBookedSlots() {
        List<BookedSlot> bookedSlots = bookedSlotRepository.findAll();
        return bookedSlots.stream().map(BookedSlotMapper::toBookedSlotDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookedSlotDto updateBookedSlot(Long id, BookedSlotDto updatedBookedSlotDto) {
        BookedSlot bookedSlot = bookedSlotRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Booked slot with id " + id + " not found"));

        bookedSlot.setParking(updatedBookedSlotDto.getParking());
        bookedSlot.setUserEntity(updatedBookedSlotDto.getUserEntity());
        bookedSlot.setDateOfEnd(updatedBookedSlotDto.getDateOfEnd());

        BookedSlot updatedBookedSlotObj = bookedSlotRepository.save(bookedSlot);

        return BookedSlotMapper.toBookedSlotDto(updatedBookedSlotObj);
    }

    @Override
    public void deleteBookedSlot(Long id) {
        BookedSlot bookedSlot = bookedSlotRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Booked slot with id " + id + " not found"));

        bookedSlotRepository.deleteById(id);
    }
}
