package com.agrimedic.agrimedic_backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.agrimedic.agrimedic_backend.entity.FertilizerOrder;

import java.util.List;

public interface FertilizerOrderRepository extends JpaRepository<FertilizerOrder, Long> {
    List<FertilizerOrder> findByFarmerId(Long farmerId);
}
