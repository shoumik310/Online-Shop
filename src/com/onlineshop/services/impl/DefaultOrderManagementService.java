package com.onlineshop.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.onlineshop.enteties.Order;
import com.onlineshop.services.OrderManagementService;

public class DefaultOrderManagementService implements OrderManagementService {

	private static DefaultOrderManagementService instance;
	private List<Order> orders;

	{
		orders = new ArrayList<>();
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
		return this.orders;
	}

	void clearServiceState() {
		orders.clear();
	}

}