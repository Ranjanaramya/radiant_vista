package com.example.rvlights.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment_methods")
public class PaymentMethod {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String provider;
    private String token;
    private String cardBrand;
    private String last4;
    private Integer expiryMonth;
    private Integer expiryYear;
    private Boolean isDefault = false;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate(){ this.createdAt = LocalDateTime.now(); }
    @PreUpdate
    protected void onUpdate(){ this.updatedAt = LocalDateTime.now(); }

    // getters and setters
    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}
    public Long getUserId(){return userId;}
    public void setUserId(Long userId){this.userId = userId;}
    public String getProvider(){return provider;}
    public void setProvider(String provider){this.provider = provider;}
    public String getToken(){return token;}
    public void setToken(String token){this.token = token;}
    public String getCardBrand(){return cardBrand;}
    public void setCardBrand(String cardBrand){this.cardBrand = cardBrand;}
    public String getLast4(){return last4;}
    public void setLast4(String last4){this.last4 = last4;}
    public Integer getExpiryMonth(){return expiryMonth;}
    public void setExpiryMonth(Integer expiryMonth){this.expiryMonth = expiryMonth;}
    public Integer getExpiryYear(){return expiryYear;}
    public void setExpiryYear(Integer expiryYear){this.expiryYear = expiryYear;}
    public Boolean getIsDefault(){return isDefault;}
    public void setIsDefault(Boolean isDefault){this.isDefault = isDefault;}
    public LocalDateTime getCreatedAt(){return createdAt;}
    public LocalDateTime getUpdatedAt(){return updatedAt;}
}
