package com.migros.couriertracking.service;

import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.migros.couriertracking.iservice.ICourierService;
import com.migros.couriertracking.model.Courier;
import com.migros.couriertracking.model.CourierEntrance;
import com.migros.couriertracking.model.Store;
import com.migros.couriertracking.repository.CourierEntranceRepository;
import com.migros.couriertracking.repository.CourierRepository;
import com.migros.couriertracking.utility.manager.FileManager;
import com.migros.couriertracking.utility.mapper.CourierMapper;
import com.migros.couriertracking.utility.util.ArithmeticUtil;

/**
 * Kurye işlemleri için gerçekleştirilen kayıt altına alma ve hesaplama servislerini içerir.
 * 
 * @author BORA
 *
 */
@Service
public class CourierService implements ICourierService {
	
	private static final int DISTANCE = 100;
	private static final int MINUTE = 60;

	private final CourierRepository courierRepository;
	private final CourierEntranceRepository courierEntranceRepository;
	
	Logger logger = LoggerFactory.getLogger(CourierService.class);

	public CourierService(CourierRepository courierRepository, CourierEntranceRepository courierEntranceRepository) {
		this.courierRepository = courierRepository;
		this.courierEntranceRepository = courierEntranceRepository;
	}

	@Override
	public Courier enterCourier(Courier courier) {
		logger.debug("Enter courier operation is started.");
		List<Store> stores = FileManager.getInstance().getStoreList();
		saveCourierEntrance(courier, stores);
		logger.debug("Enter courier operation is finished.");
		return courierRepository.save(courier);
	}
	
	private void saveCourierEntrance(Courier courier, List<Store> stores) {
		for (Store store : stores) {
			if (getEnteredStore(courier, store)) {
				logger.debug("Save courier entrance operation is started.");
				courierEntranceRepository.save(CourierMapper.getInstance().mapCourierEntrance(courier, store));
				logger.info("Courier Id: " + courier.getCourier() + " Store Name: " + store.getName());
				logger.debug("Save courier entrance operation is finished.");
			}
		}
	}
	
	private boolean getEnteredStore(Courier courier, Store store) {
		if (ArithmeticUtil.getInstance().measureDistance(courier.getLat(), courier.getLng(), store.getLat(), store.getLng()) <= DISTANCE) {
			if (isEntrance(courier, store))
				return true;
		}
		return false;
	}
	
	private boolean isEntrance(Courier newCourier, Store store) {
		List<CourierEntrance> courierEntrances = courierEntranceRepository.findByCourier(newCourier.getCourier());
		for (CourierEntrance courierEntrance : courierEntrances) {
			if (courierEntrance.getStoreName() != null && courierEntrance.getStoreName().equals(store.getName())) {
				long seconds = ChronoUnit.SECONDS.between(newCourier.getTime(), courierEntrance.getTime());
				if (Math.abs(seconds) < MINUTE)
					return false;
			}
		}
		return true;
	}

	@Override
	public Double measureCourierTravel(Long courierId) {
		logger.debug("Measure courier travel operation is started.");
		List<Courier> couriers = courierRepository.findByCourier(courierId);
		List<Courier> sortedCouriers = couriers.stream()
												.sorted(Comparator.comparing(Courier::getTime))
												.collect(Collectors.toList());
		logger.debug("Measure courier travel operation is finished.");
		return measureCourierTotalTravelDistance(sortedCouriers);
	}
	
	private double measureCourierTotalTravelDistance(List<Courier> courier) {
		double totalDistance = 0.0;
		for (int i = 0; i < courier.size()-1; i++) {
			totalDistance += ArithmeticUtil.getInstance().measureDistance(courier.get(i).getLat(), courier.get(i).getLng(),
					courier.get(i+1).getLat(), courier.get(i+1).getLng());
		}
		return totalDistance;
	}
}
