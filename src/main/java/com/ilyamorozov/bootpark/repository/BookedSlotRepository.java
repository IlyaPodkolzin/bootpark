package com.ilyamorozov.bootpark.repository;

import com.ilyamorozov.bootpark.entity.BookedSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public interface BookedSlotRepository extends JpaRepository<BookedSlot, Long> {
    List<BookedSlot> findByParking_Id(Long parkingId);
    List<BookedSlot> findByUserEntity_Id(Long id);
    BookedSlot findByUserEntity_IdAndId(Long userEntityId, Long bookedSlotId);
    Optional<BookedSlot> findByUserEntity_IdAndParking_Id(Long userEntityId, Long parkingId);  // нужен для поиска бронирований пользователя определенной парковки
    // (у пользователя не может быть больше одной активной брони определенной парковки)

}