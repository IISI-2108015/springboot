package com.leon.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leon.springboot.db2.model.Product;
import com.leon.springboot.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/{id}")
	public ResponseEntity<Product> getUser(@PathVariable Integer id) {
		Product product = productService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(product);
	}

	@GetMapping
	public ResponseEntity<List<Product>> getUser() {
		List<Product> list = productService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}

	@PostMapping
	public ResponseEntity<Product> createUser(@RequestBody Product product) {
		productService.create(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(product);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Product> updateUser(@PathVariable Integer id, @RequestBody Product product) {
		productService.update(id, product);
		product.setProductId(id);
		return ResponseEntity.status(HttpStatus.OK).body(product);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
		try {
			productService.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body("Delete Product");
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Delete Failed");
		}
	}
	
}
