package com.ilyamorozov.bootpark.repository;

import com.ilyamorozov.bootpark.entity.BookedSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public interface BookedSlotRepository extends JpaRepository<BookedSlot, Long> {
    List<BookedSlot> findByUserEntity_Id(Long id);
}