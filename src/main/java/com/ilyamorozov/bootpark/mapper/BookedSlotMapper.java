package com.ilyamorozov.bootpark.mapper;

import com.ilyamorozov.bootpark.dto.BookedSlotDto;
import com.ilyamorozov.bootpark.entity.BookedSlot;

public class BookedSlotMapper {

    public static BookedSlotDto toBookedSlotDto(BookedSlot bookedSlot) {
        return new BookedSlotDto(
                bookedSlot.getId(),
                bookedSlot.getParking(),
                bookedSlot.getUserEntity(),
                bookedSlot.getDateOfEnd()
        );
    }

    public static BookedSlot toBookedSlot(BookedSlotDto bookedSlotDto) {
        return new BookedSlot(
                bookedSlotDto.getId(),
                bookedSlotDto.getParking(),
                bookedSlotDto.getUserEntity(),
                bookedSlotDto.getDateOfEnd()
        );
    }
}
