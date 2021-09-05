package com.migros.couriertracking.utility.manager;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.migros.couriertracking.model.Store;
import com.migros.couriertracking.service.CourierService;

public class FileManager {
	private static final FileManager instance = new FileManager();
	private List<Store> storeList = null;
	
	Logger logger = LoggerFactory.getLogger(CourierService.class);
	
	private FileManager() {}
	
	public static FileManager getInstance() {
		return instance;
	}
	
	public void loadStores() {
		logger.debug("Store file loading operation is started");
		InputStream jsonFileStream = getClass().getResourceAsStream("/stores.json");
		ObjectMapper objectMapper = new ObjectMapper();
		TypeReference<List<Store>> typeReference = new TypeReference<List<Store>>() {};
		try {
			storeList = objectMapper.readValue(jsonFileStream, typeReference);
		} catch (JsonParseException e) {
			logger.error(e.getMessage());
		} catch (JsonMappingException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		logger.debug("Store file loading operation is finished");
	}

	public List<Store> getStoreList() {
		return storeList;
	}
	
}
