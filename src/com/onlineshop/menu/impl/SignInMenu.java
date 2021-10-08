package com.onlineshop.menu.impl;

import java.util.Scanner;

import com.onlineshop.configs.ApplicationContext;
import com.onlineshop.enteties.User;
import com.onlineshop.menu.Menu;
import com.onlineshop.services.UserManagementService;
import com.onlineshop.services.impl.DefaultUserManagementService;

public class SignInMenu implements Menu {

	private ApplicationContext context;
	private UserManagementService userManagementService;

	{
		context = ApplicationContext.getInstance();
		userManagementService = DefaultUserManagementService.getInstance();
	}

	@Override
	public void start() {
		printMenuHeader();

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		System.out.print("Please enter your email: ");
		String email = sc.next();

		System.out.print("Please enter your password: ");
		String password = sc.next();

		User user = userManagementService.getUserByEmail(email);
		if (user != null && user.getPassword().equals(password)) {
			System.out.printf("Glad to see you back %s %s", user.getFirstName(),
					user.getLastName() + System.lineSeparator());
			context.setLoggedInUser(user);
		} else {
			System.out.println("Unfortunately, such login and password doesn’t exist");
		}
		context.getMainMenu().start();
	}

	@Override
	public void printMenuHeader() {
		System.out.println("***** Sign In *****");
	}

}