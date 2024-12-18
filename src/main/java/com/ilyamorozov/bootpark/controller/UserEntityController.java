package com.ilyamorozov.bootpark.controller;

import com.ilyamorozov.bootpark.dto.UserEntityDto;
import com.ilyamorozov.bootpark.entity.UserEntity;
import com.ilyamorozov.bootpark.service.UserEntityService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserEntityController {
    private UserEntityService userEntityService;

    @GetMapping("{id}")
    public ResponseEntity<UserEntityDto> findById(@PathVariable Long id) {
        UserEntityDto userEntityDto = userEntityService.getUserById(id);
        return ResponseEntity.ok(userEntityDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        userEntityService.deleteUserById(id);
        return ResponseEntity.ok("Deleted user with id " + id);
    }
}
