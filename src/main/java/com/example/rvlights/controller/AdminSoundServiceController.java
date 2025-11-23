package com.example.rvlights.controller;

import com.example.rvlights.model.SoundService;
import com.example.rvlights.repo.SoundServiceRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin/sound-services")
public class AdminSoundServiceController {
    private final SoundServiceRepository repo;
    public AdminSoundServiceController(SoundServiceRepository repo){this.repo = repo;}

    @PostMapping
    public ResponseEntity<SoundService> create(@RequestBody SoundService s){
        s.computeTotal();
        SoundService saved = repo.save(s);
        return ResponseEntity.status(201).body(saved);
    }

    @GetMapping
    public List<SoundService> list(){ return repo.findAll(); }
    
    @GetMapping("/{id}")
    public ResponseEntity<SoundService> getById(@PathVariable Long id){
        return repo.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<SoundService> update(@PathVariable Long id, @RequestBody SoundService s){
        if(!repo.existsById(id)) return ResponseEntity.notFound().build();
        s.setId(id);
        s.computeTotal();
        SoundService saved = repo.save(s);
        return ResponseEntity.ok(saved);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if(!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}



