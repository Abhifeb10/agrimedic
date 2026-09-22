package com.agrimedic.agrimedic_backend.controller;

import com.agrimedic.agrimedic_backend.dto.*;
import com.agrimedic.agrimedic_backend.entity.*;
import com.agrimedic.agrimedic_backend.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	private final FertilizerOrderService orderService;
	private final UserService userService;
	private final CentralizedProductService fertilizerService;

	public OrderController(FertilizerOrderService orderService, UserService userService,
			CentralizedProductService fertilizerService) {
		this.orderService = orderService;
		this.userService = userService;
		this.fertilizerService = fertilizerService;
	}

	@PostMapping
	public ResponseEntity<?> place(@RequestBody OrderDTO dto) {
		FertilizerOrder o = new FertilizerOrder();
		User farmer = userService.findById(dto.getFarmerId()).orElseThrow();
		CentralizedProduct fert = fertilizerService.findById(dto.getFertilizerId()).orElseThrow();
		o.setFarmer(farmer);
		o.setFertilizer(fert);
		o.setQuantity(dto.getQuantity());
		FertilizerOrder saved = orderService.placeOrder(o);
		dto.setId(saved.getId());
		dto.setStatus(saved.getStatus());
		return ResponseEntity.ok(dto);
	}

	@GetMapping
	public List<OrderDTO> all() {
		return orderService.findAll().stream().map(this::toDto).collect(Collectors.toList());
	}

	@GetMapping("/farmer/{farmerId}")
	public List<OrderDTO> byFarmer(@PathVariable Long farmerId) {
		return orderService.findByFarmerId(farmerId).stream().map(this::toDto).collect(Collectors.toList());
	}

	@PutMapping("/{id}/status")
	public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestBody OrderDTO dto) {
		FertilizerOrder updated = orderService.updateStatus(id, dto.getStatus());
		return ResponseEntity.ok(toDto(updated));
	}

	private OrderDTO toDto(FertilizerOrder o) {
		OrderDTO d = new OrderDTO();
		d.setId(o.getId());
		d.setFarmerId(o.getFarmer() != null ? o.getFarmer().getId() : null);
		d.setFertilizerId(o.getFertilizer() != null ? o.getFertilizer().getId() : null);
		d.setQuantity(o.getQuantity());
		d.setStatus(o.getStatus());
		return d;
	}
}
