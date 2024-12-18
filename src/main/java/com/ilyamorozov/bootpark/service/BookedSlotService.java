package com.ilyamorozov.bootpark.service;

import com.ilyamorozov.bootpark.dto.BookedSlotDto;

import java.util.List;

public interface BookedSlotService {
    BookedSlotDto createBookedSlot(BookedSlotDto bookedSlotDto);
    BookedSlotDto getBookedSlotById(Long id);
    BookedSlotDto getBookedSlotByUserIdAndSlotId(Long userId, Long slotId);
    List<BookedSlotDto> getBookedSlotsByUserId(Long userId);
    List<BookedSlotDto> getAllBookedSlots();
    void deleteBookedSlot(Long id);
    void deleteBookedSlotByUserIdAndSlotId(Long userId, Long slotId);
}
