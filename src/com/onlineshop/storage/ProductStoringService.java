package com.onlineshop.storage;

import java.util.List;
import com.onlineshop.enteties.Product;

public interface ProductStoringService {

	List<Product> loadProducts();

}
