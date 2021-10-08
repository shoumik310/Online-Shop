package com.onlineshop.services;

import java.util.List;
import com.onlineshop.enteties.Product;

public interface ProductManagementService {

	List<Product> getProducts();

	Product getProductById(int productIdToAddToCart);

}