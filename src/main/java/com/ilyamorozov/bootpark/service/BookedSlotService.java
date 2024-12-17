package com.ilyamorozov.bootpark.service;

import com.ilyamorozov.bootpark.dto.BookedSlotDto;

import java.util.List;

public interface BookedSlotService {
    BookedSlotDto createBookedSlot(BookedSlotDto bookedSlotDto);
    BookedSlotDto getBookedSlotById(Long id);
    List<BookedSlotDto> getBookedSlotsByUserId(Long userId);
    List<BookedSlotDto> getAllBookedSlots();
    BookedSlotDto updateBookedSlot(Long id, BookedSlotDto updatedBookedSlotDto);
    void deleteBookedSlot(Long id);
}
