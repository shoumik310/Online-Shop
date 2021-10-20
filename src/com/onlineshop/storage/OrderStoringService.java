package com.onlineshop.storage;

import java.util.List;

import com.onlineshop.enteties.Order;

public interface OrderStoringService {

	void saveOrders(List<Order> orders);
	
	List<Order> loadOrders();
}
