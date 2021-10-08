package com.onlineshop.menu.impl;

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
		User[] users = userManagementService.getUsers();
		if(users==null || users.length==0) {
			System.out.println("Unfortunately, there are no customers.");
		}else {
			for(User user:users) {
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