package com.ilyamorozov.bootpark.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "parkings")
public class Parking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(name = "available_slots_amount", nullable = false)
    private Integer availableSlotsAmount;

    @Column(name = "parking_slots_amount", nullable = false)
    private Integer parkingSlotsAmount;

    @OneToMany(mappedBy = "parking", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BookedSlot> bookedSlots;
}
