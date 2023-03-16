package ua.com.alevel.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.alevel.entity.Profile;
import ua.com.alevel.service.ProfileService;

import java.util.Collection;

@RestController
@RequestMapping("profiles")
@AllArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @PutMapping("/{id}")
    private ResponseEntity<Boolean> update(@PathVariable Long id, @RequestBody Profile profile) {
        profileService.update(id, profile);
        return ResponseEntity.ok(true);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Profile> findById(@PathVariable Long id) {
        return ResponseEntity.ok(profileService.findById(id));
    }

    @GetMapping
    private ResponseEntity<Collection<Profile>> findAll() {
        return ResponseEntity.ok(profileService.findAll());
    }
}
