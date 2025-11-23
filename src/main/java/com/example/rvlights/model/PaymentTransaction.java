package com.example.rvlights.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment_transactions")
public class PaymentTransaction {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long paymentMethodId;
    private Long serviceId;
    private String serviceType; // "light" or "sound"
    private Integer quantity = 1;
    private BigDecimal hours;
    private BigDecimal amount;
    private String paymentType; // "card" or "bank_transfer"
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String paymentSlipUrl;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String bankDetails;
    private String status = "pending";
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate(){ this.createdAt = LocalDateTime.now(); }

    // getters / setters
    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}
    public Long getUserId(){return userId;}
    public void setUserId(Long userId){this.userId = userId;}
    public Long getPaymentMethodId(){return paymentMethodId;}
    public void setPaymentMethodId(Long paymentMethodId){this.paymentMethodId = paymentMethodId;}
    public Long getServiceId(){return serviceId;}
    public void setServiceId(Long serviceId){this.serviceId = serviceId;}
    public String getServiceType(){return serviceType;}
    public void setServiceType(String serviceType){this.serviceType = serviceType;}
    public Integer getQuantity(){return quantity;}
    public void setQuantity(Integer quantity){this.quantity = quantity;}
    public BigDecimal getHours(){return hours;}
    public void setHours(BigDecimal hours){this.hours = hours;}
    public java.math.BigDecimal getAmount(){return amount;}
    public void setAmount(java.math.BigDecimal amount){this.amount = amount;}
    public String getPaymentType(){return paymentType;}
    public void setPaymentType(String paymentType){this.paymentType = paymentType;}
    public String getPaymentSlipUrl(){return paymentSlipUrl;}
    public void setPaymentSlipUrl(String paymentSlipUrl){this.paymentSlipUrl = paymentSlipUrl;}
    public String getBankDetails(){return bankDetails;}
    public void setBankDetails(String bankDetails){this.bankDetails = bankDetails;}
    public String getStatus(){return status;}
    public void setStatus(String status){this.status = status;}
    public LocalDateTime getCreatedAt(){return createdAt;}
}
