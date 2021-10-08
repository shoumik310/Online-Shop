package com.onlineshop.enteties.impl;

import java.util.Arrays;

import com.onlineshop.enteties.Cart;
import com.onlineshop.enteties.Product;

public class DefaultCart implements Cart {

	private static final int DEFAULT_CART_CAPACITY = 10;
	private Product[] products;
	private int productIndex = 0;

	{
		products = new Product[DEFAULT_CART_CAPACITY];
	}

	@Override
	public boolean isEmpty() {
		if (products == null || productIndex == 0) {
			return true;
		}

		for (Product product : products) {
			if (product != null) {
				return false;
			}
		}

		return true;
	}

	@Override
	public void addProduct(Product product) {
		if (product == null) {
			return;
		}
		//TODO: <=
		if (productIndex == products.length) { 
			products = Arrays.copyOf(products, products.length << 1);
		}

		products[productIndex++] = product;
	}

	@Override
	public Product[] getProducts() {

		int nonNullProductsAmount = 0;
		for (Product product : products) {
			if (product != null) {
				nonNullProductsAmount++;
			}
		}

		Product[] nonNullProducts = new Product[nonNullProductsAmount];
		int index = 0;
		for (Product product : products) {
			if (product != null) {
				nonNullProducts[index++] = product;
			}
		}
		return nonNullProducts;
	}

	@Override
	public void clear() {
		products = new Product[DEFAULT_CART_CAPACITY];
		productIndex = 0;
	}

}