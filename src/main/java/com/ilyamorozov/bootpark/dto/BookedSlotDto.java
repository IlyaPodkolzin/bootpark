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

    private Long id;   // мы получаем только айдишники парковки и юзера, а не их объекты. Далее в объект
    private Long parkingId;  // BookedSlot записываем объекты парковки и юзера, найденные по айдишникам
    private Long userEntityId;
    private LocalDateTime dateOfEnd;
}