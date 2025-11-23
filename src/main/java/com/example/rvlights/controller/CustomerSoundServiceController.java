package com.example.rvlights.controller;

import com.example.rvlights.model.SoundService;
import com.example.rvlights.repo.SoundServiceRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/customer/sound-services")
public class CustomerSoundServiceController {
    private final SoundServiceRepository repo;
    public CustomerSoundServiceController(SoundServiceRepository repo){this.repo = repo;}

    @GetMapping
    public List<SoundService> list(){ return repo.findAll(); }
    
    @GetMapping("/{id}")
    public SoundService getById(@PathVariable Long id){
        return repo.findById(id).orElse(null);
    }
}



