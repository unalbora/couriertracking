package com.migros.couriertracking.utility.mapper;

import com.migros.couriertracking.model.Courier;
import com.migros.couriertracking.model.CourierEntrance;
import com.migros.couriertracking.model.Store;

public class CourierMapper {

	private static final CourierMapper instance = new CourierMapper();
	
	private CourierMapper() {}
	
	public static CourierMapper getInstance() {
		return instance;
	}
	
	public CourierEntrance mapCourierEntrance(Courier courier, Store store) {
		CourierEntrance courierEntrance = new CourierEntrance();
		courierEntrance.setCourier(courier.getCourier());
		courierEntrance.setLat(store.getLat());
		courierEntrance.setLng(store.getLng());
		courierEntrance.setTime(courier.getTime());
		courierEntrance.setStoreName(store.getName());
		return courierEntrance;
	}
}
