package com.onlineshop.storage.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.onlineshop.enteties.Product;
import com.onlineshop.enteties.impl.DefaultProduct;
import com.onlineshop.storage.ProductStoringService;

public class DefaultProductStoringService implements ProductStoringService {

	private final String ASSETS_FOLDER = "assets";
	private final String PRODUCTS_INFO_STORAGE = "products.csv";
	private final int PRODUCT_PRICE_INDEX = 3;
	private final int PRODUCT_CATEGORY_INDEX = 2;
	private final int PRODUCT_NAME_INDEX = 1;
	private final int PRODUCT_ID_INDEX = 0;

	@Override
	public List<Product> loadProducts() {
		try (var stream = Files.lines(Paths.get(ASSETS_FOLDER, PRODUCTS_INFO_STORAGE))) {
			return stream.filter(Objects::nonNull).filter((line) -> !line.isEmpty()).map((line) -> {
				String[] productElements = line.split(",");
				return new DefaultProduct(Integer.valueOf(productElements[PRODUCT_ID_INDEX]),
						productElements[PRODUCT_NAME_INDEX], productElements[PRODUCT_CATEGORY_INDEX],
						Double.valueOf(productElements[PRODUCT_PRICE_INDEX]));
			}).collect(Collectors.toList());
		}catch(IOException e){
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
}
