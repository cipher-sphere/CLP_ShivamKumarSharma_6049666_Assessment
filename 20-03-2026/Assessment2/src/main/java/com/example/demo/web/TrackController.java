package com.example.demo.web;

import com.example.demo.entity.Track;
import com.example.demo.repo.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tracks")
public class TrackController {

    @Autowired
    private TrackRepository trackRepository;

    @PostMapping
    public ResponseEntity<String> addTrack(@RequestBody Track track) {
        trackRepository.save(track);
        return new ResponseEntity<>("Track added successfully", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Track>> getTracks() {
        List<Track> tracks = trackRepository.findAll();
        return new ResponseEntity<>(tracks, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Track>> getTracksByTitle(@RequestParam String title) {
        List<Track> tracks = trackRepository.findByTitleContainingIgnoreCase(title);
        return new ResponseEntity<>(tracks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTrack(@PathVariable Integer id) {
        Optional<Track> track = trackRepository.findById(Long.valueOf(id));
        if (track.isPresent()) {
            return new ResponseEntity<>(track.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Track not found with id: " + id, HttpStatus.NOT_FOUND);
        }
    }
}
