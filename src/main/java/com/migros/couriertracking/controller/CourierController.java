package com.migros.couriertracking.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.migros.couriertracking.model.Courier;
import com.migros.couriertracking.service.CourierService;
/**
 * Courier Tracking uygulamasında sunulan servislerin erişimi için hazırlanmış olan get ve post tiplerinde
 * olmak üzere rest servislerini içerir.
 * 
 * @author BORA ÜNAL
 *
 */
@RestController
@RequestMapping("/courier")
public class CourierController {

	private final CourierService courierService;

	public CourierController(CourierService courierService) {
		super();
		this.courierService = courierService;
	}
	
	/**
	 * Kuryeye ait bilgileri alıp mağaza bilgileri ile karşılaştırır ve şartları sağladığı durumda
	 * kayıt altına alır.
	 * 
	 * @param courier Kuryenin id, lokasyon ve zaman bilgilerini içerir.
	 */
	@PostMapping("/enter")
	public ResponseEntity<Courier> enterCourier(@RequestBody Courier courier) {
		return new ResponseEntity<Courier>(courierService.enterCourier(courier), HttpStatus.OK);
	}
	
	/**
	 * Id si verilen kuryeye ait toplam gezi mesafesini hesaplar.
	 * @param courierId Kurye id
	 * @return
	 */
	@GetMapping("/distance/{courierId}")
	public ResponseEntity<Double> measureCourierTravel(@PathVariable Long courierId) {
		return new ResponseEntity<Double>(courierService.measureCourierTravel(courierId), HttpStatus.OK);
	}
}
