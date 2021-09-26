package com.migros.couriertracking.utility.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Kuryenin toplam gezinti mesafesini hesaplamak için kullanılan util sınıfıdır.
 * 
 * @author BORA
 *
 */
public class ArithmeticUtil {
	private static final ArithmeticUtil instance = new ArithmeticUtil();
	
	Logger logger = LoggerFactory.getLogger(ArithmeticUtil.class);
	
	private static final double RADIUS_OF_WORLD = 6378.137;
	private static final int DEGREE = 180;
	private static final int TO_METER = 1000;
	private static final int HALF = 2;
	
	private ArithmeticUtil() {}
	
	public static ArithmeticUtil getInstance() {
		return instance;
	}
	
	public double measureDistance(double firstLat, double firstLng, double secondLat, double secondLng) {
		logger.debug("Measure distance operation is started");
		double distanceLat = measureDifference(secondLat, firstLat);
		double distanceLng = measureDifference(secondLng, firstLng);
		double angularDistance = getAngularDistance(distanceLat, distanceLng, firstLat, secondLat);
		double distance = RADIUS_OF_WORLD * angularDistance;
		logger.debug("Measure distance operation is finished");
		return distance * TO_METER;
	}
	
	private double measureDifference(double second, double first) {
		return (second * Math.PI / DEGREE) - (first * Math.PI / DEGREE);
	}
	
	private double getAngularDistance(double distanceLat, double distanceLng, double firstLat, double secondLat) {
		double a = (Math.sin(distanceLat/HALF) * Math.sin(distanceLat/HALF)) + (Math.cos(firstLat * Math.PI /DEGREE) * Math.cos(secondLat * Math.PI /DEGREE)
				* Math.sin(distanceLng/HALF) * Math.sin(distanceLng/HALF));
		return HALF * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
	}
}
