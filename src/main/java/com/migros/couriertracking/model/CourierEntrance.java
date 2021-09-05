package com.migros.couriertracking.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="courierentrances")
public class CourierEntrance {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private long courier;
	private double lat;
	private double lng;
	private LocalDateTime time;
	private String storeName;
	
	public long getCourier() {
		return courier;
	}
	public void setCourier(long courier) {
		this.courier = courier;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
}
