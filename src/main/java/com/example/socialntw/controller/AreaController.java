package com.example.socialntw.controller;

import com.example.socialntw.dto.AreaDto;
import com.example.socialntw.entity.User;
import com.example.socialntw.parent.ApiResponse;
import com.example.socialntw.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/areas")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addArea(@AuthenticationPrincipal UserDetails userDetails, @RequestBody AreaDto areaDto) {
        User user = (User) userDetails;
        AreaDto createdArea = areaService.addArea(user, areaDto);
        ApiResponse apiResponse = new ApiResponse(HttpStatus.CREATED.value(), createdArea);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AreaDto> updateArea(@PathVariable Integer id, @RequestBody AreaDto areaDto) {
        AreaDto updatedArea = areaService.updateArea(id, areaDto);
        return ResponseEntity.ok(updatedArea);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteArea(@PathVariable Integer id) {
        areaService.deleteArea(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AreaDto> getArea(@PathVariable Integer id) {
        AreaDto area = areaService.getArea(id);
        return ResponseEntity.ok(area);
    }

    @GetMapping
    public ResponseEntity<List<AreaDto>> getAreas() {
        List<AreaDto> areas = areaService.getAreas();
        return ResponseEntity.ok(areas);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AreaDto>> getAreasByUser(@PathVariable Integer userId) {
        List<AreaDto> areas = areaService.getAreasByUser(userId);
        return ResponseEntity.ok(areas);
    }
}