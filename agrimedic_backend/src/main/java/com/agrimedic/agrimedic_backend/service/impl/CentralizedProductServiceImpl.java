package com.agrimedic.agrimedic_backend.service.impl;

import org.springframework.stereotype.Service;

import com.agrimedic.agrimedic_backend.entity.CentralizedProduct;
import com.agrimedic.agrimedic_backend.repository.CentralizedProductRepository;
import com.agrimedic.agrimedic_backend.service.CentralizedProductService;

import java.util.List;
import java.util.Optional;

@Service
public class CentralizedProductServiceImpl implements CentralizedProductService {

	private final CentralizedProductRepository repo;

	public CentralizedProductServiceImpl(CentralizedProductRepository repo) {
		this.repo = repo;
	}

	@Override
	public CentralizedProduct create(CentralizedProduct f) {
		return repo.save(f);
	}

	@Override
	public List<CentralizedProduct> findAll() {
		return repo.findAll();
	}

	@Override
	public Optional<CentralizedProduct> findById(Long id) {
		return repo.findById(id);
	}

	@Override
	public CentralizedProduct update(Long id, CentralizedProduct f) {
		CentralizedProduct existing = repo.findById(id).orElseThrow();
		existing.setName(f.getName());
		existing.setBrand(f.getBrand());
		existing.setPrice(f.getPrice());
		existing.setStock(f.getStock());
		return repo.save(existing);
	}

	@Override
	public void delete(Long id) {
		repo.deleteById(id);
	}

	@Override
	public List<CentralizedProduct> findByShopOwnerId(Long shopOwnerId) {
		return repo.findByShopOwnerId(shopOwnerId);
	}
}
