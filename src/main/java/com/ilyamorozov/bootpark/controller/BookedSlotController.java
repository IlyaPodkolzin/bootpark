package com.ilyamorozov.bootpark.controller;

import com.ilyamorozov.bootpark.dto.BookedSlotDto;
import com.ilyamorozov.bootpark.service.BookedSlotService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
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

    // Build Get All BookedSlots REST API (specific user)
    @GetMapping("user{user_id}")
    public ResponseEntity<List<BookedSlotDto>> getAllBookedSlotsByUserId(@PathVariable Long user_id) {
        List<BookedSlotDto> bookedSlotsDtos = bookedSlotService.getBookedSlotsByUserId(user_id);
        return ResponseEntity.ok(bookedSlotsDtos);
    }

    // Build Get BookedSlot REST API (specific user or admin)
    @GetMapping({"*/{slot_id}", "{slot_id}"})
    public ResponseEntity<BookedSlotDto> getBookedSlotByUserIdAndSlotId(@PathVariable Long slot_id) {
        BookedSlotDto bookedSlotDto = bookedSlotService.getBookedSlotById(slot_id);
        return ResponseEntity.ok(bookedSlotDto);
    }

    // Build Get All BookedSlots REST API (admin only)
    @GetMapping
    public ResponseEntity<List<BookedSlotDto>> getAllBookedSlots() {
        List<BookedSlotDto> bookedSlotsDtos = bookedSlotService.getAllBookedSlots();
        return ResponseEntity.ok(bookedSlotsDtos);
    }

    // Build Update BookedSlot REST API (for specific user or admin)
    @PutMapping({"*/{slot_id}", "{slot_id}"})
    public ResponseEntity<BookedSlotDto> updateBookedSlot(@PathVariable Long slot_id, @RequestBody BookedSlotDto bookedSlotDTO) {
        BookedSlotDto bookedSlotDto = bookedSlotService.updateBookedSlot(slot_id, bookedSlotDTO);
        return ResponseEntity.ok(bookedSlotDto);
    }

    // Build Delete BookedSlot REST API (for specific user or admin)
    @DeleteMapping({"*/{slot_id}", "{slot_id}"})
    public ResponseEntity<String> deleteBookedSlot(@PathVariable Long slot_id) {
        bookedSlotService.deleteBookedSlot(slot_id);
        return ResponseEntity.ok("BookedSlot deleted");
    }
}