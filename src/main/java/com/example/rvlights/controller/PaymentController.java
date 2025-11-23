package com.example.rvlights.controller;

import com.example.rvlights.model.PaymentMethod;
import com.example.rvlights.repo.PaymentMethodRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer/payments")
public class PaymentController {
    private final PaymentMethodRepository repo;
    public PaymentController(PaymentMethodRepository repo){ this.repo = repo; }

    // For demo: extract user id from X-User-Id header. In real app, use SecurityContext/JWT.
    private Long getUserId(HttpServletRequest req){
        String h = req.getHeader("X-User-Id");
        if(h == null) return 1L; // default demo user
        try { return Long.parseLong(h); } catch(Exception e){ return 1L; }
    }

    @GetMapping
    public List<PaymentMethod> list(HttpServletRequest req){
        return repo.findByUserId(getUserId(req));
    }

    @PostMapping
    public ResponseEntity<PaymentMethod> create(@RequestBody PaymentMethod pm, HttpServletRequest req){
        pm.setUserId(getUserId(req));
        // in prod do tokenization with payment gateway
        PaymentMethod saved = repo.save(pm);
        return ResponseEntity.status(201).body(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, HttpServletRequest req){
        Long uid = getUserId(req);
        Optional<PaymentMethod> opt = repo.findById(id);
        if(opt.isEmpty() || !uid.equals(opt.get().getUserId())) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
