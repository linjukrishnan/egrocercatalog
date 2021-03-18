package com.java.egrocer.catalog.service;

import java.util.List;
import com.java.egrocer.catalog.model.Product;

public interface ProductService {

	public List<Product> getAllProducts();
	public long addProduct(Product product);
	public Product updateProduct(Product  product);
	public String deleteProduct(long  productId);
	public List<String> getAllProductCategory();
	public List<Product> getProductsByCategory(String category);
}
