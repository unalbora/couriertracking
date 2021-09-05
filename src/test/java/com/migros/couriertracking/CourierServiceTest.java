package com.migros.couriertracking;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.mockito.Mockito;

import com.migros.couriertracking.model.Courier;
import com.migros.couriertracking.repository.CourierEntranceRepository;
import com.migros.couriertracking.repository.CourierRepository;
import com.migros.couriertracking.service.CourierService;
import com.migros.couriertracking.utility.manager.FileManager;

public class CourierServiceTest extends CouriertrackingApplicationTests {

	private CourierService courierService;
	private CourierRepository courierRepository;
	private CourierEntranceRepository courierEntranceRepository;
	
	@Before
	public void setUp() throws Exception {
		courierRepository = Mockito.mock(CourierRepository.class);
		courierEntranceRepository = Mockito.mock(CourierEntranceRepository.class);
		courierService = new CourierService(courierRepository, courierEntranceRepository);
		FileManager.getInstance().loadStores();
	}
	
	@Test
	public void whenEnterCourierCalled_itShouldReturnCourier() {
		Courier courier = new Courier();
		courier.setCourier(3);
		courier.setLat(41.055783);
		courier.setLng(29.0210292);
		courier.setTime(LocalDateTime.now());

		Mockito.when(courierRepository.save(courier)).thenReturn(courier);
		
		Courier result = courierService.enterCourier(courier);
		Assert.assertEquals(result, courier);
		Mockito.verify(courierRepository).save(courier);
	}
	
	@Test
	public void whenMeasureCourierTravelCalled_itShouldReturnDouble() {
		long courierId = 3;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		Courier courier = new Courier();
		courier.setCourier(3);
		courier.setLat(41.0066851);
		courier.setLng(28.6552262);
		courier.setTime(LocalDateTime.parse("2021-09-04 15:57:32", formatter));
		Courier courier2 = new Courier();
		courier2.setCourier(3);
		courier2.setLat(41.0076851);
		courier2.setLng(28.6562262);
		courier2.setTime(LocalDateTime.parse("2021-09-04 15:57:40", formatter));
		Courier courier3 = new Courier();
		courier3.setCourier(3);
		courier3.setLat(40.986106);
		courier3.setLng(29.1161293);
		courier3.setTime(LocalDateTime.parse("2021-09-04 15:57:40", formatter));
		Courier courier4 = new Courier();
		courier4.setCourier(3);
		courier4.setLat(40.986206);
		courier4.setLng(29.1163293);
		courier4.setTime(LocalDateTime.parse("2021-09-04 15:59:40", formatter));
		Courier courier5 = new Courier();
		courier5.setCourier(3);
		courier5.setLat(40.9923307);
		courier5.setLng(29.1244229);
		courier5.setTime(LocalDateTime.parse("2021-09-04 17:59:40", formatter));
		Courier courier6 = new Courier();
		courier6.setCourier(3);
		courier6.setLat(41.055783);
		courier6.setLng(29.0210292);
		courier6.setTime(LocalDateTime.parse("2021-09-04 18:59:40", formatter));
		List<Courier> couriers = new ArrayList<Courier>();
		couriers.add(courier);
		couriers.add(courier2);
		couriers.add(courier3);
		couriers.add(courier4);
		couriers.add(courier5);
		couriers.add(courier6);
		
		double totalDistance = 51030.66960184035;
		double delta = 0.001;
		
		Mockito.when(courierRepository.findByCourier(courierId)).thenReturn(couriers);
		
		double result = courierService.measureCourierTravel(courierId);
		Assert.assertEquals(result, totalDistance, delta);
		Mockito.verify(courierRepository).findByCourier(courierId);
	}
}