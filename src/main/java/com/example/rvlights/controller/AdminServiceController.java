package com.example.rvlights.controller;

import com.example.rvlights.model.LightService;
import com.example.rvlights.repo.LightServiceRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin/services")
public class AdminServiceController {
    private final LightServiceRepository repo;
    public AdminServiceController(LightServiceRepository repo){this.repo = repo;}

    @PostMapping
    public ResponseEntity<LightService> create(@RequestBody LightService s){
        // in prod, check admin role
        s.computeTotal();
        LightService saved = repo.save(s);
        return ResponseEntity.status(201).body(saved);
    }

    @GetMapping
    public List<LightService> list(){ return repo.findAll(); }
    
    @GetMapping("/{id}")
    public ResponseEntity<LightService> getById(@PathVariable Long id){
        return repo.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<LightService> update(@PathVariable Long id, @RequestBody LightService s){
        if(!repo.existsById(id)) return ResponseEntity.notFound().build();
        s.setId(id);
        s.computeTotal();
        LightService saved = repo.save(s);
        return ResponseEntity.ok(saved);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if(!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
