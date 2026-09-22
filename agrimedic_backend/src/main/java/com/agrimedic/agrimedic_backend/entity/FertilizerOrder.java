package com.agrimedic.agrimedic_backend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Fertilizer_order")
public class FertilizerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "farmer_id")
    private User farmer;

    @ManyToOne	
    @JoinColumn(name = "CentralizedProduct_id")
    private CentralizedProduct CentralizedProduct;

    private Integer quantity;

    private String status; // PLACED, PROCESSING, COMPLETED, CANCELLED

    private LocalDateTime createdAt;

    public FertilizerOrder() {}

    @PrePersist
    public void prePersist() {
        if (createdAt == null) createdAt = LocalDateTime.now();
        if (status == null) status = "PLACED";
    }

    // getters/setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getFarmer() { return farmer; }
    public void setFarmer(User farmer) { this.farmer = farmer; }
    public CentralizedProduct getFertilizer() { return CentralizedProduct; }
    public void setFertilizer(CentralizedProduct CentralizedProduct) { this.CentralizedProduct = CentralizedProduct; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
