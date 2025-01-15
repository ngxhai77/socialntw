package com.example.socialntw.controller;

import com.example.socialntw.dto.ModeratorDto;
import com.example.socialntw.entity.Moderator;
import com.example.socialntw.service.ModeratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/moderators")
public class ModeratorController {

    @Autowired
    private ModeratorService moderatorService;

    @PostMapping("/add")
    public ResponseEntity<ModeratorDto> addModerator(@RequestBody ModeratorDto moderatorDto) {
        ModeratorDto savedModerator = moderatorService.addModerator(moderatorDto);
        return ResponseEntity.ok(savedModerator);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeModerator(@PathVariable Integer id) {
        moderatorService.removeModerator(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<Moderator>> getAllModerators() {
        List<Moderator> moderators = moderatorService.getAllModerators();
        return ResponseEntity.ok(moderators);
    }

    @GetMapping("/area/{areaId}")
    public ResponseEntity<List<Moderator>> getAllModeratorByAreaId(@PathVariable Integer areaId) {
        List<Moderator> moderators = moderatorService.getAllModeratorByAreaId(areaId);
        return ResponseEntity.ok(moderators);
    }


    @PutMapping("/ban/user/{id}")
    public ResponseEntity<Void> banUser(@PathVariable Integer id) {
        moderatorService.banUser(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Integer id) {
        moderatorService.deletePost(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/ban/area/{id}")
    public ResponseEntity<Void> banArea(@PathVariable Integer id) {
        moderatorService.banArea(id);
        return ResponseEntity.ok().build();
    }
}