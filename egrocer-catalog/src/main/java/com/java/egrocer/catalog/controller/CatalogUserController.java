package com.java.egrocer.catalog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.java.egrocer.catalog.model.GrocerProducts;
import com.java.egrocer.catalog.model.Product;
import com.java.egrocer.catalog.service.ProductService;

@RestController
public class CatalogUserController {
		
	@Autowired
	private ProductService productService;

	@RequestMapping(method = RequestMethod.GET, value = "/getAllDetails")
	public GrocerProducts getAllDetails()
	{
		GrocerProducts gproducts = new GrocerProducts();
		
		List<Product> productList = productService.getAllProducts();
		List<String> productCategoryList = productService.getAllProductCategory();
		gproducts.getGrocerProductMap().put("productCategoryList", productCategoryList);
		gproducts.getGrocerProductMap().put("productList", productList);
		return  gproducts;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getProductByCategory/{category}")
	public GrocerProducts getProductByCategory(@PathVariable(value="category") String category)
	{
		GrocerProducts gproducts = new GrocerProducts();
		List<Product> productList = productService.getProductsByCategory(category);
		List<String> productCategoryList = productService.getAllProductCategory();
		gproducts.getGrocerProductMap().put("productCategoryList", productCategoryList);
		gproducts.getGrocerProductMap().put("productList", productList);
		return  gproducts;
	}
	
}
