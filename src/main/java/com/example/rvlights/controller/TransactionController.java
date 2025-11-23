package com.example.rvlights.controller;

import com.example.rvlights.model.LightService;
import com.example.rvlights.model.SoundService;
import com.example.rvlights.model.PaymentMethod;
import com.example.rvlights.model.PaymentTransaction;
import com.example.rvlights.repo.LightServiceRepository;
import com.example.rvlights.repo.SoundServiceRepository;
import com.example.rvlights.repo.PaymentMethodRepository;
import com.example.rvlights.repo.PaymentTransactionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer/payments")
public class TransactionController {
    private final PaymentMethodRepository pmRepo;
    private final LightServiceRepository lsRepo;
    private final SoundServiceRepository ssRepo;
    private final PaymentTransactionRepository txRepo;

    public TransactionController(PaymentMethodRepository pmRepo, LightServiceRepository lsRepo, SoundServiceRepository ssRepo, PaymentTransactionRepository txRepo){
        this.pmRepo = pmRepo; this.lsRepo = lsRepo; this.ssRepo = ssRepo; this.txRepo = txRepo;
    }

    private Long getUserId(HttpServletRequest req){
        String h = req.getHeader("X-User-Id");
        if(h == null) return 1L;
        try { return Long.parseLong(h); } catch(Exception e){ return 1L; }
    }

    public static class ChargeRequest {
        public Long paymentMethodId;
        public Long serviceId;
        public String serviceType; // "light" or "sound"
        public Integer quantity = 1;
        public BigDecimal hours;
        public String paymentType; // "card" or "bank_transfer"
        public String paymentSlipUrl;
        public String bankDetails;
        public Boolean saveCard = false;
    }

    @PostMapping("/charge")
    public ResponseEntity<?> charge(@RequestBody ChargeRequest reqBody, HttpServletRequest req){
        Long userId = getUserId(req);
        if(reqBody.serviceId == null) return ResponseEntity.badRequest().body("serviceId required");
        if(reqBody.serviceType == null) return ResponseEntity.badRequest().body("serviceType required (light or sound)");

        BigDecimal pricePerItem;
        BigDecimal defaultHours;
        
        if("light".equals(reqBody.serviceType)) {
            Optional<LightService> svcOpt = lsRepo.findById(reqBody.serviceId);
            if(svcOpt.isEmpty()) return ResponseEntity.badRequest().body("Light service not found");
            LightService svc = svcOpt.get();
            pricePerItem = svc.getPricePerItem();
            defaultHours = svc.getDefaultHours();
        } else if("sound".equals(reqBody.serviceType)) {
            Optional<SoundService> svcOpt = ssRepo.findById(reqBody.serviceId);
            if(svcOpt.isEmpty()) return ResponseEntity.badRequest().body("Sound service not found");
            SoundService svc = svcOpt.get();
            pricePerItem = svc.getPricePerItem();
            defaultHours = svc.getDefaultHours();
        } else {
            return ResponseEntity.badRequest().body("Invalid serviceType. Must be 'light' or 'sound'");
        }

        int qty = reqBody.quantity == null ? 1 : reqBody.quantity;
        BigDecimal hours = reqBody.hours != null ? reqBody.hours : defaultHours;
        BigDecimal amount = pricePerItem.multiply(new BigDecimal(qty)).multiply(hours);

        PaymentTransaction tx = new PaymentTransaction();
        tx.setUserId(userId);
        tx.setServiceId(reqBody.serviceId);
        tx.setServiceType(reqBody.serviceType);
        tx.setQuantity(qty);
        tx.setHours(hours);
        tx.setAmount(amount);
        tx.setPaymentType(reqBody.paymentType != null ? reqBody.paymentType : "card");
        tx.setPaymentSlipUrl(reqBody.paymentSlipUrl);
        tx.setBankDetails(reqBody.bankDetails);
        
        if("card".equals(reqBody.paymentType) && reqBody.paymentMethodId != null) {
            Optional<PaymentMethod> pmOpt = pmRepo.findById(reqBody.paymentMethodId);
            if(pmOpt.isPresent() && userId.equals(pmOpt.get().getUserId())) {
                tx.setPaymentMethodId(reqBody.paymentMethodId);
            }
        }
        
        tx.setStatus("completed");
        PaymentTransaction saved = txRepo.save(tx);

        return ResponseEntity.ok(saved);
    }

    @GetMapping("/transactions")
    public ResponseEntity<?> getTransactions(HttpServletRequest req){
        Long userId = getUserId(req);
        List<PaymentTransaction> transactions = txRepo.findByUserId(userId);
        return ResponseEntity.ok(transactions);
    }
}
