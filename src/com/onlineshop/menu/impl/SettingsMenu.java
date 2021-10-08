package com.onlineshop.menu.impl;

import java.util.Scanner;


import com.onlineshop.configs.ApplicationContext;
import com.onlineshop.menu.Menu;

public class SettingsMenu implements Menu {

	private static final String SETTINGS = "1. Change Password" + System.lineSeparator() + "2. Change Email";
	private ApplicationContext context;

	{
		context = ApplicationContext.getInstance();
	}

	@Override
	public void start() {

		Menu menuToNavigate;

		mainloop: while (true) {
			printMenuHeader();

			if (context.getLoggedInUser() == null) {
				System.out.println("Please, log in or create new account to change your account settings");
				menuToNavigate = new MainMenu();
				break mainloop;
			} else {

				System.out.println(SETTINGS);

				System.out.print("Please, enter option or type 'menu' to navigate back to the main menu: ");
				@SuppressWarnings("resource")
				Scanner sc = new Scanner(System.in);
				String userInput = sc.next();

				if (userInput.equalsIgnoreCase(MainMenu.MENU_COMMAND)) {
					menuToNavigate = new MainMenu();
					break mainloop;
				}

				int chosenOption = Integer.parseInt(userInput);
				switch (chosenOption) {
				case 1:
					menuToNavigate = new ChangePasswordMenu();
					break mainloop;
				case 2:
					menuToNavigate = new ChangeEmailMenu();
					break mainloop;
				default:
					System.out.println("Only 1, 2 is allowed. Try one more time");
					continue;
				}
			}
		}

		menuToNavigate.start();

	}

	@Override
	public void printMenuHeader() {
		System.out.println("***** SETTINGS *****");
	}

}