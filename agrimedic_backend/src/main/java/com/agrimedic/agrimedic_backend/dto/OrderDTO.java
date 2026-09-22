package com.agrimedic.agrimedic_backend.dto;


public class OrderDTO {
    private Long id;
    private Long farmerId;
    private Long fertilizerId;
    private Integer quantity;
    private String status;

    public OrderDTO() {}

    // getters/setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getFarmerId() { return farmerId; }
    public void setFarmerId(Long farmerId) { this.farmerId = farmerId; }
    public Long getFertilizerId() { return fertilizerId; }
    public void setFertilizerId(Long fertilizerId) { this.fertilizerId = fertilizerId; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
