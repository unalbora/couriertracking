package com.migros.couriertracking.iservice;

import com.migros.couriertracking.model.Courier;

public interface ICourierService {
	
	Courier enterCourier(Courier courier);
	Double measureCourierTravel(Long courierId);
}
