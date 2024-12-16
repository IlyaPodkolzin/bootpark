package com.ilyamorozov.bootpark.repository;

import com.ilyamorozov.bootpark.entity.Parking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingRepository extends JpaRepository<Parking, Long> {
}
