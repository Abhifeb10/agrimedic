package com.agrimedic.agrimedic_backend.dto;

public class CentralizedProductDTO {
	private Long id;
	private String name;
	private String brand;
	private Double price;
	private Integer stock;
	private Long shopOwnerId;

	public CentralizedProductDTO() {
	}

	// getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Long getShopOwnerId() {
		return shopOwnerId;
	}

	public void setShopOwnerId(Long shopOwnerId) {
		this.shopOwnerId = shopOwnerId;
	}
}
