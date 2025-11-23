package com.example.rvlights.controller;

import com.example.rvlights.repo.PaymentTransactionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin/transactions")
public class AdminTransactionController {
    private final PaymentTransactionRepository repo;
    public AdminTransactionController(PaymentTransactionRepository repo){this.repo = repo;}

    @GetMapping
    public ResponseEntity<List<?>> getAll(){
        return ResponseEntity.ok(repo.findAll());
    }
}


