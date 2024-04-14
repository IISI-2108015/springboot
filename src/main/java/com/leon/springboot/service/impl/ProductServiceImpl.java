package com.leon.springboot.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leon.springboot.db1.model.User;
import com.leon.springboot.db2.dao.ProductRepository;
import com.leon.springboot.db2.model.Product;
import com.leon.springboot.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public void create(Product product) {
		productRepository.save(product);
	}
	
	@Override
	public 	void update(Integer id, Product product) {
		Product oriProduct = productRepository.findById(id).get();
		if(oriProduct != null) {
			oriProduct.setProductName(product.getProductName());
			oriProduct.setProductPrice(product.getProductPrice());
			productRepository.save(oriProduct);
		}
	}
	
	@Override
	public Product findById(Integer id) {
		return productRepository.findById(id).get();
	}
	
	@Override
	public List<Product> findAll(){
		List<Product> list = new ArrayList<Product>();
		Iterable<Product> users = productRepository.findAll();
		users.forEach(list::add);
		return list;
	}
	
	@Override
	public void delete(Integer id) {
		productRepository.deleteById(id);
	}

}
