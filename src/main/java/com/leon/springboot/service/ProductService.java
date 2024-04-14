package com.leon.springboot.service;

import java.util.List;

import com.leon.springboot.db2.model.Product;

public interface ProductService {
	
	void create(Product product);
	
	void update(Integer id, Product product);
	
	Product findById(Integer id);
	
	List<Product> findAll();
	
	void delete(Integer id);

}
