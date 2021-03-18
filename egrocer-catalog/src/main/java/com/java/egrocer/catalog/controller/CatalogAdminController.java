package com.java.egrocer.catalog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.java.egrocer.catalog.model.GrocerProducts;
import com.java.egrocer.catalog.model.Product;
import com.java.egrocer.catalog.service.ProductService;

@RestController
public class CatalogAdminController {
		
	@Autowired
	private ProductService productService;

	@RequestMapping(method = RequestMethod.GET, value = "/getAllProducts")
	public GrocerProducts getAllProducts()
	{
		GrocerProducts gproducts = new GrocerProducts();
		List<Product> productList = productService.getAllProducts();
		gproducts.getGrocerProductMap().put("productList", productList);
		return  gproducts;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/addProduct")
	public String addProduct(@RequestBody Product productRequest)
	{
		long productId = 0;
		Product product = (Product)productRequest;
		productId = productService.addProduct(product);
		if(productId != 0) {
			return "Product added successfully";
		}
		return "Product add failed";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/updateProduct")
	public String updateProduct(@RequestBody Product request)
	{
		Product product = (Product)request;
		Product updateProduct = productService.updateProduct(product);
		if(updateProduct.getProductId() != 0) {
			return "Product updated successfully";
		}
		return "Product update failed";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/deleteProduct")
	public String deleteProducts(@RequestBody Product request)
	{
		Product product = (Product)request;
		String message = productService.deleteProduct(product.getProductId());
		return message;
	}
	
}
