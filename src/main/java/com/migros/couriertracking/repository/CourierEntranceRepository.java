package com.migros.couriertracking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.migros.couriertracking.model.CourierEntrance;

public interface CourierEntranceRepository extends JpaRepository<CourierEntrance, Long> {

	List<CourierEntrance> findByCourier(long courier);
	
}
