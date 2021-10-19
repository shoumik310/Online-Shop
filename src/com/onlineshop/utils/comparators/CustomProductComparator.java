package com.onlineshop.utils.comparators;

import java.util.Comparator;

import com.onlineshop.enteties.Product;

public class CustomProductComparator implements Comparator<Product> {
	@Override
	public int compare(Product product1, Product product2) {
		int retVal = product1.getCategoryName().compareTo(product2.getCategoryName());
		if (retVal == 0) {
			Double priceDelta = product1.getPrice() - product2.getPrice();
			retVal = priceDelta < 0 ? -1 : (priceDelta == 0) ? 0 : 1;
		}
		if (retVal == 0) {
			retVal = product1.getProductName().compareTo(product2.getProductName());
		}
		return retVal;

	}

}