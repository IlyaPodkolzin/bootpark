package com.ilyamorozov.bootpark.controller;

import com.ilyamorozov.bootpark.dto.BookedSlotDto;
import com.ilyamorozov.bootpark.service.BookedSlotService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/api/booked")
public class BookedSlotController {

    private BookedSlotService bookedSlotService;

    // Build Add BookedSlot REST API
    @PostMapping
    public ResponseEntity<BookedSlotDto> createBookedSlot(@RequestBody BookedSlotDto bookedSlotDTO) {
        BookedSlotDto savedBookedSlot = bookedSlotService.createBookedSlot(bookedSlotDTO);
        return new ResponseEntity<>(savedBookedSlot, HttpStatus.CREATED);
    }

    // Build Get BookedSlot REST API
    @GetMapping("{id}")
    public ResponseEntity<BookedSlotDto> getBookedSlot(@PathVariable("id") Long id) {
        BookedSlotDto bookedSlotDto = bookedSlotService.getBookedSlotById(id);
        return ResponseEntity.ok(bookedSlotDto);
    }

    // Build Get All BookedSlots REST API
    @GetMapping
    public ResponseEntity<List<BookedSlotDto>> getAllBookedSlots() {
        List<BookedSlotDto> bookedSlotsDtos = bookedSlotService.getAllBookedSlots();
        return ResponseEntity.ok(bookedSlotsDtos);
    }

    // Build Update BookedSlot REST API
    @PutMapping("{id}")
    public ResponseEntity<BookedSlotDto> updateBookedSlot(@PathVariable("id") Long id, @RequestBody BookedSlotDto bookedSlotDTO) {
        BookedSlotDto bookedSlotDto = bookedSlotService.updateBookedSlot(id, bookedSlotDTO);
        return ResponseEntity.ok(bookedSlotDTO);
    }

    // Build Delete BookedSlot REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteBookedSlot(@PathVariable("id") Long id) {
        bookedSlotService.deleteBookedSlot(id);
        return ResponseEntity.ok("BookedSlot deleted");
    }
}