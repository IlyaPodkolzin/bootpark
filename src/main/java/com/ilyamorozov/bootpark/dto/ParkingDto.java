package com.ilyamorozov.bootpark.dto;

import com.ilyamorozov.bootpark.entity.BookedSlot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParkingDto {
    private Long id;
    private String name;
    private String address;
    private Integer availableSlotsAmount;
    private Integer parkingSlotsAmount;
    private List<BookedSlot> bookedSlots;
}
