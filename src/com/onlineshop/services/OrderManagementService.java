package com.onlineshop.services;

import java.util.List;
import com.onlineshop.enteties.Order;

public interface OrderManagementService {

	void addOrder(Order order);

	List<Order> getOrdersByUserId(int userId);

	List<Order> getOrders();

}
