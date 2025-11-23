package com.example.rvlights.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "sound_services")
public class SoundService {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String imageUrl;
    @Column(columnDefinition = "TEXT")
    private String description;
    private BigDecimal pricePerItem;
    private BigDecimal defaultHours;
    private BigDecimal totalPrice;
    @Column(columnDefinition = "TEXT")
    private String additionalServices;
    private Long createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void preCreate(){
        this.createdAt = LocalDateTime.now();
        computeTotal();
    }
    @PreUpdate
    protected void preUpdate(){
        this.updatedAt = LocalDateTime.now();
        computeTotal();
    }
    public void computeTotal(){
        if(pricePerItem == null) pricePerItem = BigDecimal.ZERO;
        if(defaultHours == null) defaultHours = BigDecimal.ONE;
        this.totalPrice = pricePerItem.multiply(defaultHours);
    }

    // getters and setters
    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}
    public String getTitle(){return title;}
    public void setTitle(String title){this.title = title;}
    public String getDescription(){return description;}
    public void setDescription(String description){this.description = description;}
    public String getImageUrl(){return imageUrl;}
    public void setImageUrl(String imageUrl){this.imageUrl = imageUrl;}
    public BigDecimal getPricePerItem(){return pricePerItem;}
    public void setPricePerItem(BigDecimal pricePerItem){this.pricePerItem = pricePerItem;}
    public BigDecimal getDefaultHours(){return defaultHours;}
    public void setDefaultHours(BigDecimal defaultHours){this.defaultHours = defaultHours;}
    public BigDecimal getTotalPrice(){return totalPrice;}
    public void setTotalPrice(BigDecimal totalPrice){this.totalPrice = totalPrice;}
    public String getAdditionalServices(){return additionalServices;}
    public void setAdditionalServices(String additionalServices){this.additionalServices = additionalServices;}
    public Long getCreatedBy(){return createdBy;}
    public void setCreatedBy(Long createdBy){this.createdBy = createdBy;}
    public LocalDateTime getCreatedAt(){return createdAt;}
    public LocalDateTime getUpdatedAt(){return updatedAt;}
}



