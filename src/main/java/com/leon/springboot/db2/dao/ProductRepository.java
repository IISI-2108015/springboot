package com.leon.springboot.db2.dao;

import org.springframework.data.repository.CrudRepository;

import com.leon.springboot.db2.model.Product;

public interface ProductRepository  extends CrudRepository<Product, Integer> {

}
