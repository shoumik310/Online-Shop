package com.onlineshop.services.impl;

import java.util.Arrays;

import com.onlineshop.enteties.Order;
import com.onlineshop.services.OrderManagementService;

public class DefaultOrderManagementService implements OrderManagementService {

	private static final int DEFAULT_ORDER_CAPACITY = 10;

	private static DefaultOrderManagementService instance;
	private Order[] orders;
	private int orderIndex = 0;

	{
		orders = new Order[DEFAULT_ORDER_CAPACITY];
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

		// TODO: <=
		if (orderIndex == orders.length) {
			orders = Arrays.copyOf(orders, orders.length << 1);
		}

		orders[orderIndex++] = order;
	}

	@Override
	public Order[] getOrdersByUserId(int userId) {
		int numberOfOrders = 0;

		for (Order order : orders) {
			if (order != null && order.getCustomerId() == userId) {
				numberOfOrders++;
			}
		}

		Order[] ordersByUserId = new Order[numberOfOrders];
		int index = 0;

		for (Order order : orders) {
			if (order != null && order.getCustomerId() == userId) {
				ordersByUserId[index++] = order;
			}
		}

		return ordersByUserId;
	}

	@Override
	public Order[] getOrders() {
		int nonNullOrdersAmount = 0;

		for (Order order : orders) {
			if (order != null) {
				nonNullOrdersAmount++;
			}
		}

		Order[] nonNullOrders = new Order[nonNullOrdersAmount];
		int index = 0;

		for (Order order : orders) {
			if (order != null) {
				nonNullOrders[index++] = order;
			}
		}

		return nonNullOrders;
	}

	void clearServiceState() {
		orders = new Order[DEFAULT_ORDER_CAPACITY];
		orderIndex = 0;
	}

}