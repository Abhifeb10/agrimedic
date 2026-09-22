package com.agrimedic.agrimedic_backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.agrimedic.agrimedic_backend.entity.CentralizedProduct;

import java.util.List;

public interface CentralizedProductRepository extends JpaRepository<CentralizedProduct, Long> {
    List<CentralizedProduct> findByShopOwnerId(Long shopOwnerId);
}
