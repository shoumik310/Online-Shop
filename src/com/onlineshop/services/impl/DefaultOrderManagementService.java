package com.onlineshop.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.onlineshop.enteties.Order;
import com.onlineshop.services.OrderManagementService;
import com.onlineshop.storage.impl.DefaultOrderStoringService;

public class DefaultOrderManagementService implements OrderManagementService {

	private static DefaultOrderManagementService instance;
	private static DefaultOrderStoringService orderStoringService;
	private List<Order> orders;
	
	{
		orderStoringService = DefaultOrderStoringService.getInstance();
		orders = orderStoringService.loadOrders();
		if(orders==null) {
			orders = new ArrayList<>();
		}
	}

	public static OrderManagementService getInstance() {
		if (instance == null) {
			instance = new DefaultOrderManagementService();
		}
		return instance;
	}

	@Override
	public void addOrder(Order order) {
		if (order == null) {
			return;
		}

		orders.add(order);
		orderStoringService.saveOrders(orders);
	}

	@Override
	public List<Order> getOrdersByUserId(int userId) {
		List<Order> ordersByUserId = new ArrayList<>();

		for (Order order : orders) {
			if (order != null && order.getCustomerId() == userId) {
				ordersByUserId.add(order);
			}
		}
		return ordersByUserId;
	}

	@Override
	public List<Order> getOrders() {
		if(orders==null || orders.size()==0) {
			orders = orderStoringService.loadOrders();
		}
		return this.orders;
	}

	void clearServiceState() {
		orders.clear();
	}

}