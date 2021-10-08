package com.onlineshop.menu.impl;

import java.util.Scanner;

import com.onlineshop.Main;
import com.onlineshop.configs.ApplicationContext;
import com.onlineshop.menu.Menu;

public class MainMenu implements Menu {

	public static final String MENU_COMMAND = "menu";

	private static final String MAIN_MENU_TEXT_FOR_LOGGED_OUT_USER = "Please, enter number in console to proceed."
			+ System.lineSeparator() + "1. Sign Up" + System.lineSeparator() + "2. Sign In" + System.lineSeparator()
			+ "3. Product Catalog" + System.lineSeparator() + "4. My Orders" + System.lineSeparator() + "5. Settings"
			+ System.lineSeparator() + "6. Customer List";

	private static final String MAIN_MENU_TEXT_FOR_LOGGED_IN_USER = "Please, enter number in console to proceed."
			+ System.lineSeparator() + "1. Sign Up" + System.lineSeparator() + "2. Sign Out" + System.lineSeparator()
			+ "3. Product Catalog" + System.lineSeparator() + "4. My Orders" + System.lineSeparator() + "5. Settings"
			+ System.lineSeparator() + "6. Customer List";;

	private ApplicationContext context;

	{
		context = ApplicationContext.getInstance();
	}

	@Override
	public void start() {
		if (context.getMainMenu() == null) {
			context.setMainMenu(this);
		}
		
		Menu menuToNavigate = null;
		mainloop: while (true) {
			printMenuHeader();

			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);

			System.out.print("User Input: ");
			String userInput = sc.next();
			if (userInput.equalsIgnoreCase(Main.EXIT_COMMAND)) {
				System.exit(0);
				sc.close();
			} else {
				int commandNumber = Integer.parseInt(userInput);
				switch (commandNumber) {
				case 1: // SignOUt
					menuToNavigate = new SignUpMenu();
					break mainloop;
				case 2: // Sign In/Sign Out
					if (context.getLoggedInUser() == null) {
						menuToNavigate = new SignInMenu();
					} else {
						menuToNavigate = new SignOutMenu();
					}
					break mainloop;
				case 3: // Product Catalog
					menuToNavigate = new ProductCatalogMenu();
					break mainloop;
				case 4: // My Order
					menuToNavigate = new MyOrdersMenu();
					break mainloop;
				case 5: // Settings
					menuToNavigate = new SettingsMenu();
					break mainloop;
				case 6: // Customer List
					menuToNavigate = new CustomerListMenu();
					break mainloop;
				default:
					System.out.println("Only 1, 2, 3, 4, 5 is allowed. Try one more time");
					continue;
				}
			}
		}
		menuToNavigate.start();
	}

	@Override
	public void printMenuHeader() {
		System.out.println("***** MAIN MENU *****");
		if (context.getLoggedInUser() == null) {
			System.out.println(MAIN_MENU_TEXT_FOR_LOGGED_OUT_USER);
		} else {
			System.out.println(MAIN_MENU_TEXT_FOR_LOGGED_IN_USER);
		}
	}

}
