package com.agrimedic.agrimedic_backend.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "CentralizedProduct")
public class CentralizedProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String brand;
    private Double price;
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "shop_owner_id")
    private User shopOwner;

    public CentralizedProduct() {}

    // getters/setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
    public User getShopOwner() { return shopOwner; }
    public void setShopOwner(User shopOwner) { this.shopOwner = shopOwner; }
}
