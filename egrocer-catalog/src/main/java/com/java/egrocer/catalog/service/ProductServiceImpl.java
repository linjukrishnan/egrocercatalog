package com.java.egrocer.catalog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.egrocer.catalog.model.Product;
import com.java.egrocer.catalog.model.ProductEntity;
import com.java.egrocer.catalog.repository.ProductDao;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductDao productDAO;
	
	@Override
	public List<Product> getAllProducts(){
		
		List<ProductEntity> entityList = productDAO.findAll();
		List<Product> productList= new ArrayList<>();
		entityList.forEach(entity->{
			Product product= new Product();
			BeanUtils.copyProperties(entity, product);
			productList.add(product);
		});
		return productList;
	}

	@Override
	public long addProduct(Product product) {
		
		ProductEntity entity = new ProductEntity();
		BeanUtils.copyProperties(product, entity);
		ProductEntity productEntity = productDAO.save(entity);
		return productEntity.getProductId();
	}

	@Override
	public Product updateProduct(Product product) {
		
		ProductEntity productEntity=productDAO.findById(product.getProductId()).get();
		Product updatedProduct = new Product();
		if(productEntity!=null) {
			productEntity.setProductName(product.getProductName());
			productEntity.setCategory(product.getCategory());
			productEntity.setProductPrice(product.getProductPrice());
			productEntity.setAvailable(product.getAvailable());
			ProductEntity entity=productDAO.save(productEntity);
			BeanUtils.copyProperties(entity, updatedProduct);
		}
		return updatedProduct;
	}

	@Override
	public String deleteProduct(long productId) {
		
		ProductEntity productEntity=productDAO.findById(productId).get();
		if(productEntity!=null) {
			productDAO.deleteById(productId);
		}
		return "Product deleted successfully";
	}

	@Override
	public List<String> getAllProductCategory() {
		
		List<String> categoryList = productDAO.getAllProductCategory();
		return categoryList;
	}
	
	@Override
	public List<Product> getProductsByCategory(String category) {
		List<ProductEntity> enList=productDAO.findAllByCategory(category);
		List<Product> productlist= new ArrayList<>();
		enList.forEach(entity->{
			Product product = new Product();
			BeanUtils.copyProperties(entity, product);
			productlist.add(product);
		});
		return productlist;
	}

}
