package com.onlineshop.menu.impl;

import java.util.List;

import com.onlineshop.configs.ApplicationContext;
import com.onlineshop.enteties.Order;
import com.onlineshop.menu.Menu;
import com.onlineshop.services.OrderManagementService;
import com.onlineshop.services.impl.DefaultOrderManagementService;

public class MyOrdersMenu implements Menu {

	private ApplicationContext context;
	private OrderManagementService orderManagementService;

	{
		context = ApplicationContext.getInstance();
		orderManagementService = DefaultOrderManagementService.getInstance();
	}

	@Override
	public void start() {
		printMenuHeader();
		if (context.getLoggedInUser() == null) {
			System.out.println("Please, log in or create new account to see list of your orders");
		} else {
			printAllUserOrders();
		}
		new MainMenu().start();
	}

	private void printAllUserOrders() {
		List<Order> loggedInUserOrders  = orderManagementService.getOrdersByUserId(context.getLoggedInUser().getId());
		if (loggedInUserOrders  == null || loggedInUserOrders.isEmpty()) {
			System.out.println("Unfortunately, you don’t have any orders yet. "
					+ "Navigate back to main menu to place a new order");
		} else {
			for (Order order : loggedInUserOrders ) {
				System.out.println(order);
			}
		}
	}

	@Override
	public void printMenuHeader() {
		System.out.println("***** MY ORDERS *****");
	}

}
