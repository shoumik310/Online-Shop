package com.onlineshop.menu.impl;

import java.util.List;

import com.onlineshop.configs.ApplicationContext;
import com.onlineshop.enteties.User;
import com.onlineshop.menu.Menu;
import com.onlineshop.services.UserManagementService;
import com.onlineshop.services.impl.DefaultUserManagementService;

public class CustomerListMenu implements Menu {

	private ApplicationContext context;
	private UserManagementService userManagementService;

	{
		userManagementService = DefaultUserManagementService.getInstance();
		context = ApplicationContext.getInstance();
	}

	@Override
	public void start() {
		printMenuHeader();
		List<User> users = userManagementService.getUsers();
		if (users == null || users.isEmpty()) {
			System.out.println("Unfortunately, there are no customers.");
		} else {
			for (User user : users) {
				System.out.println(user);
			}
		}
		context.getMainMenu().start();
	}

	@Override
	public void printMenuHeader() {
		System.out.println("***** USERS *****");
	}

}