package com.ilyamorozov.bootpark.dto;

import com.ilyamorozov.bootpark.entity.Parking;
import com.ilyamorozov.bootpark.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookedSlotDto {

    private Long id;
    private Parking parking;
    private UserEntity userEntity;
    private LocalDateTime dateOfEnd;
}