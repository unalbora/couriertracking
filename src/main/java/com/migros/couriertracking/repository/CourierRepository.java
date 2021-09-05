package com.migros.couriertracking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.migros.couriertracking.model.Courier;

public interface CourierRepository extends JpaRepository<Courier, Long> {

	List<Courier> findByCourier(long courier);
	
}
