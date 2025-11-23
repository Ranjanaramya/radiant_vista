package com.example.rvlights.controller;

import com.example.rvlights.model.LightService;
import com.example.rvlights.repo.LightServiceRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/customer/services")
public class CustomerServiceController {
    private final LightServiceRepository repo;
    public CustomerServiceController(LightServiceRepository repo){this.repo = repo;}

    @GetMapping
    public List<LightService> list(){ return repo.findAll(); }
    
    @GetMapping("/{id}")
    public LightService getById(@PathVariable Long id){
        return repo.findById(id).orElse(null);
    }
}


