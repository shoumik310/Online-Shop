package com.onlineshop.enteties.impl;

import java.util.ArrayList;
import java.util.List;

import com.onlineshop.enteties.Cart;
import com.onlineshop.enteties.Product;

public class DefaultCart implements Cart {

	private List<Product> products;

	{
		products = new ArrayList<>();
	}

	@Override
	public boolean isEmpty() {
		return products.isEmpty();
	}

	@Override
	public void addProduct(Product product) {
		if (product == null) {
			return;
		}
		products.add(product);
	}

	@Override
	public List<Product> getProducts() {
		return products;
	}

	@Override
	public void clear() {
		products.clear();

	}
}