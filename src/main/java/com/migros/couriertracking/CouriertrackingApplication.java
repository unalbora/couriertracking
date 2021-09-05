package com.migros.couriertracking;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.migros.couriertracking.utility.manager.FileManager;

@SpringBootApplication
public class CouriertrackingApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CouriertrackingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		FileManager.getInstance().loadStores();
	}

}
