package com.agrimedic.agrimedic_backend.service.impl;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agrimedic.agrimedic_backend.entity.CentralizedProduct;
import com.agrimedic.agrimedic_backend.entity.FertilizerOrder;
import com.agrimedic.agrimedic_backend.repository.*;
import com.agrimedic.agrimedic_backend.service.*;

import java.util.List;
import java.util.Optional;

@Service
public class FertilizerOrderServiceImpl implements FertilizerOrderService {

    private final FertilizerOrderRepository orderRepo;
    private final CentralizedProductRepository fertRepo;

    public FertilizerOrderServiceImpl(FertilizerOrderRepository orderRepo, CentralizedProductRepository fertRepo) {
        this.orderRepo = orderRepo;
        this.fertRepo = fertRepo;
    }

    @Override
    @Transactional
    public FertilizerOrder placeOrder(FertilizerOrder order) {
    	CentralizedProduct f = fertRepo.findById(order.getFertilizer().getId()).orElseThrow(() -> new RuntimeException("Fertilizer not found"));
        if (f.getStock() < order.getQuantity()) {
            throw new RuntimeException("Not enough stock");
        }
        f.setStock(f.getStock() - order.getQuantity());
        fertRepo.save(f);
        order.setStatus("PLACED");
        return orderRepo.save(order);
    }

    @Override
    public Optional<FertilizerOrder> findById(Long id) { return orderRepo.findById(id); }

    @Override
    public List<FertilizerOrder> findAll() { return orderRepo.findAll(); }

    @Override
    public List<FertilizerOrder> findByFarmerId(Long farmerId) { return orderRepo.findByFarmerId(farmerId); }

    @Override
    public FertilizerOrder updateStatus(Long id, String status) {
        FertilizerOrder o = orderRepo.findById(id).orElseThrow();
        o.setStatus(status);
        return orderRepo.save(o);
    }
}
