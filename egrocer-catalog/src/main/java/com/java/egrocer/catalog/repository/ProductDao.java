package com.java.egrocer.catalog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.java.egrocer.catalog.model.ProductEntity;

@Repository
public interface ProductDao extends JpaRepository<ProductEntity, Long> {
	
	@Query(name="ProductDao.getAllProductCategory")
	public List<String> getAllProductCategory();
	public List<ProductEntity> findAllByCategory(String category);

}