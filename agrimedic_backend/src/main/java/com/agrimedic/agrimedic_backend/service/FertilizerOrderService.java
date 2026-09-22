package com.agrimedic.agrimedic_backend.service;


import com.agrimedic.agrimedic_backend.entity.*;

import java.util.List;
import java.util.Optional;

public interface FertilizerOrderService {
    FertilizerOrder placeOrder(FertilizerOrder order);
    Optional<FertilizerOrder> findById(Long id);
    List<FertilizerOrder> findAll();
    List<FertilizerOrder> findByFarmerId(Long farmerId);
    FertilizerOrder updateStatus(Long id, String status);
}
