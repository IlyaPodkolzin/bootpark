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

    // Add BookedSlot REST API
    @PostMapping
    public ResponseEntity<BookedSlotDto> createBookedSlot(@RequestBody BookedSlotDto bookedSlotDTO) {
        BookedSlotDto savedBookedSlot = bookedSlotService.createBookedSlot(bookedSlotDTO);
        return new ResponseEntity<>(savedBookedSlot, HttpStatus.CREATED);
    }

    // Get All BookedSlots REST API (specific user)
    @GetMapping("user/{user_id}")
    public ResponseEntity<List<BookedSlotDto>> getAllBookedSlotsByUserId(@PathVariable Long user_id) {
        List<BookedSlotDto> bookedSlotsDtos = bookedSlotService.getBookedSlotsByUserId(user_id);
        return ResponseEntity.ok(bookedSlotsDtos);
    }

    // Get BookedSlot REST API (admin)
    @GetMapping("admin/{slot_id}")
    public ResponseEntity<BookedSlotDto> getBookedSlotBySlotId(@PathVariable Long slot_id) {
        BookedSlotDto bookedSlotDto = bookedSlotService.getBookedSlotById(slot_id);
        return ResponseEntity.ok(bookedSlotDto);
    }

    // Get BookedSlot REST API (specific user)
    @GetMapping("user/{user_id}/{slot_id}")
    public ResponseEntity<BookedSlotDto> getBookedSlotByUserIdAndSlotId(@PathVariable Long user_id,
                                                                        @PathVariable Long slot_id) {
        BookedSlotDto bookedSlotDto = bookedSlotService.getBookedSlotByUserIdAndSlotId(user_id, slot_id);
        return ResponseEntity.ok(bookedSlotDto);
    }

    // Get All BookedSlots REST API (admin only)
    @GetMapping("admin")
    public ResponseEntity<List<BookedSlotDto>> getAllBookedSlots() {
        List<BookedSlotDto> bookedSlotsDtos = bookedSlotService.getAllBookedSlots();
        return ResponseEntity.ok(bookedSlotsDtos);
    }

    // Delete BookedSlot REST API (admin)
    @DeleteMapping("admin/{slot_id}")
    public ResponseEntity<String> deleteBookedSlot(@PathVariable Long slot_id) {
        bookedSlotService.deleteBookedSlot(slot_id);
        return ResponseEntity.ok("BookedSlot deleted");
    }

    // Delete BookedSlot REST API (specific user)
    @DeleteMapping("user/{user_id}/{slot_id}")
    public ResponseEntity<String> deleteBookedSlotByUserIdAndSlotId(@PathVariable Long user_id,
                                                                    @PathVariable Long slot_id) {
        bookedSlotService.deleteBookedSlotByUserIdAndSlotId(user_id, slot_id);
        return ResponseEntity.ok("BookedSlot deleted");
    }
}