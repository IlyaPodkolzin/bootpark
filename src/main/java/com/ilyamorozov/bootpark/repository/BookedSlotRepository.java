package com.ilyamorozov.bootpark.repository;

import com.ilyamorozov.bootpark.entity.BookedSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookedSlotRepository extends JpaRepository<BookedSlot, Long> {
}