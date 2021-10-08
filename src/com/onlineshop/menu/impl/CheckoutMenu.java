package com.onlineshop.menu.impl;

import java.util.Scanner;


import com.onlineshop.configs.ApplicationContext;
import com.onlineshop.enteties.Order;
import com.onlineshop.enteties.impl.DefaultOrder;
import com.onlineshop.menu.Menu;
import com.onlineshop.services.OrderManagementService;
import com.onlineshop.services.impl.DefaultOrderManagementService;

public class CheckoutMenu implements Menu {

	private ApplicationContext context;
	private OrderManagementService orderManagementService;

	{
		context = ApplicationContext.getInstance();
		orderManagementService = DefaultOrderManagementService.getInstance();
	}

	@Override
	public void start() {

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			printMenuHeader();
			String userInput = sc.next();
			if(!createOrder(userInput)) {
				continue;
			}
			context.getSessionCart().clear();
			break;
		}
		System.out.println("Thanks a lot for your purchase. Details about order delivery are sent to your email.");
		new MainMenu().start();
	}

	private boolean createOrder(String creditCardNumber) {
		Order order = new DefaultOrder();
		if(!order.isCreditCardNumberValid(creditCardNumber)) {
			System.out.println("You entered invalid credit card number. Valid credit card should contain 16 digits. Please, try one more time.");
			return false;
		}
		order.setCreditCardNumber(creditCardNumber);
		order.setCustomerId(context.getLoggedInUser().getId());
		order.setProducts(context.getSessionCart().getProducts());
		orderManagementService.addOrder(order);
		return true;
	}

	@Override
	public void printMenuHeader() {
		System.out.println("***** CHECKOUT *****");
		System.out.println("Enter your credit card number without spaces and press enter if you confirm purchase: ");
	}

}