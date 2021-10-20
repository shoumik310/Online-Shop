package com.onlineshop.storage.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import com.onlineshop.enteties.Order;
import com.onlineshop.storage.OrderStoringService;

public class DefaultOrderStoringService implements OrderStoringService {

	private final String ASSETS_FOLDER = "assets";
	private final String ORDERS_DATA_FILE_NAME = "orders.data";
	
	private static DefaultOrderStoringService instance;

	private DefaultOrderStoringService() {
	}
	
	@Override
	public void saveOrders(List<Order> orders) {
		try(var oos = new ObjectOutputStream(new FileOutputStream(ASSETS_FOLDER+File.separator+ORDERS_DATA_FILE_NAME))){
			oos.writeObject(orders);
		}catch(IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> loadOrders() {
		try(var ois = new ObjectInputStream(new FileInputStream(ASSETS_FOLDER+File.separator+ORDERS_DATA_FILE_NAME))){
			return (List<Order>) ois.readObject();
		}catch(IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static DefaultOrderStoringService getInstance() {
		if (instance == null) {
			instance = new DefaultOrderStoringService();
		}
		return instance;
	}

}
