package com.migros.couriertracking.utility.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.migros.couriertracking.service.CourierService;

public class ArithmeticUtil {
	private static final ArithmeticUtil instance = new ArithmeticUtil();
	
	Logger logger = LoggerFactory.getLogger(CourierService.class);
	
	private ArithmeticUtil() {}
	
	public static ArithmeticUtil getInstance() {
		return instance;
	}
	
	public double measureDistance(double firstLat, double firstLng, double secondLat, double secondLng) {
		logger.debug("Measure distance operation is started");
		double RadiusOfWorld = 6378.137;
		double distanceLat = (secondLat * Math.PI / 180) - (firstLat * Math.PI / 180);
		double distanceLng = (secondLng * Math.PI / 180) - (firstLng * Math.PI / 180);
		double a = (Math.sin(distanceLat/2) * Math.sin(distanceLat/2)) + (Math.cos(firstLat * Math.PI /180) * Math.cos(secondLat * Math.PI /180)
				* Math.sin(distanceLng/2) * Math.sin(distanceLng/2));
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		double distance = RadiusOfWorld * c;
		logger.debug("Measure distance operation is finished");
		return distance * 1000;
	}
}
