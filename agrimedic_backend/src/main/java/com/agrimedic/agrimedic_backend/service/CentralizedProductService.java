package com.agrimedic.agrimedic_backend.service;



import com.agrimedic.agrimedic_backend.entity.*;

import java.util.List;
import java.util.Optional;

public interface CentralizedProductService {
	CentralizedProduct create(CentralizedProduct f);
    List<CentralizedProduct> findAll();
    Optional<CentralizedProduct> findById(Long id);
    CentralizedProduct update(Long id, CentralizedProduct f);
    void delete(Long id);
    List<CentralizedProduct> findByShopOwnerId(Long shopOwnerId);
}
