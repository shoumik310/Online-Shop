package com.onlineshop.menu.impl;

import java.util.Scanner;

import com.onlineshop.configs.ApplicationContext;
import com.onlineshop.enteties.Cart;
import com.onlineshop.enteties.Product;
import com.onlineshop.menu.Menu;
import com.onlineshop.services.ProductManagementService;
import com.onlineshop.services.impl.DefaultProductManagementService;

public class ProductCatalogMenu implements Menu {

	private static final String CHECKOUT_COMMAND = "checkout";
	private ApplicationContext context;
	private ProductManagementService productManagementService;

	{
		context = ApplicationContext.getInstance();
		productManagementService = DefaultProductManagementService.getInstance();
	}

	@Override
	public void start() {
		Menu menuToNavigate = null;
		while (true) {
			printMenuHeader();
			printAllProducts();
			String userInput = getUserInput();

			if (context.getLoggedInUser() == null) {
				System.out.println("You are not logged in. Please, sign in or create new account");
				menuToNavigate = new MainMenu();
				break;
			}

			if (userInput.equalsIgnoreCase(MainMenu.MENU_COMMAND)) {
				menuToNavigate = new MainMenu();
				break;
			}

			if (userInput.equalsIgnoreCase(CHECKOUT_COMMAND)) {
				Cart sessionCart = context.getSessionCart();

				if (sessionCart == null || sessionCart.isEmpty()) {
					System.out.println(
							"Your cart is empty. Please, add product to cart first and then proceed with checkout");
					continue;
				} else {

					menuToNavigate = new CheckoutMenu();
					break;
				}

			} else {
				Product productToAddToCart = getProduct(userInput);

				if (productToAddToCart == null) {
					System.out.println(
							"Please, enter product ID if you want to add product to cart. Or enter ‘checkout’ if you want to proceed with checkout. Or enter ‘menu’ if you want to navigate back to the main menu.");
					continue;
				}

				addProductToCart(productToAddToCart);
			}
		}
		menuToNavigate.start();
	}

	private String getUserInput() {

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.print("Product ID to add to cart or enter 'checkout' to proceed with checkout: ");
		String userInput = sc.next();
		return userInput;
	}

	private Product getProduct(String userInput) {
		int productToAddToCartId = Integer.parseInt(userInput);
		Product productToAddToCart = productManagementService.getProductById(productToAddToCartId);
		return productToAddToCart;
	}

	private void addProductToCart(Product productToAddToCart) {
		context.getSessionCart().addProduct(productToAddToCart);
		System.out.printf(
				"Product %s has been added to your cart. " + "If you want to add a new product - enter the product id. "
						+ "If you want to proceed with checkout - enter word " + "‘checkout’ to console %n",
				productToAddToCart.getProductName());
	}

	private void printAllProducts() {
		Product[] products = productManagementService.getProducts();
		for (Product product : products) {
			System.out.println(product);
		}
	}

	@Override
	public void printMenuHeader() {
		System.out.println("***** PRODUCT CATALOG *****");
		System.out.println(
				"Enter product id to add it to the cart or ‘menu’ if you want to navigate back to the main menu");
	}

}