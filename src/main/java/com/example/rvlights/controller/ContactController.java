package com.example.rvlights.controller;

import com.example.rvlights.model.ContactMessage;
import com.example.rvlights.repo.ContactMessageRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/contact")
public class ContactController {
    private final ContactMessageRepository repo;
    public ContactController(ContactMessageRepository repo){ this.repo = repo; }

    private Long getUserId(HttpServletRequest req){
        String h = req.getHeader("X-User-Id");
        if(h == null) return null;
        try { return Long.parseLong(h); } catch(Exception e){ return null; }
    }

    @PostMapping
    public ResponseEntity<ContactMessage> send(@RequestBody ContactMessage msg, HttpServletRequest req){
        msg.setUserId(getUserId(req));
        ContactMessage saved = repo.save(msg);
        return ResponseEntity.status(201).body(saved);
    }
    
    @GetMapping
    public ResponseEntity<?> getAll(HttpServletRequest req){
        // In production, check if user is admin
        return ResponseEntity.ok(repo.findAll());
    }
}
