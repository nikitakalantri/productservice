package com.productservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.productservice.enums.Operation;
import com.productservice.model.Product;
import com.productservice.service.ProductService;

@RestController
@RequestMapping("/api/productservice")
public class ProductServiceController {
	
	@Autowired
	ProductService productService;
	
	@PostMapping(value = "/products")
	public ResponseEntity<Void> addProduct(@Valid @RequestBody Product product){
		System.out.println("Request accepted for adding product - "+ product);
		productService.addProduct(product);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/products")
	public ResponseEntity<List<Product>> getAllProducts(String filter){
		List<Product> products = productService.getAllProducts();
		if (products.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	@GetMapping(value = "/products/{productId}")
	public ResponseEntity<Product> getProduct(@Valid @PathVariable("productId")
								final String productId){
		return new ResponseEntity<Product>(productService.getProductDetails(productId), HttpStatus.OK);
	}

	@PatchMapping(value = "/products/{productId}")
	public ResponseEntity<Void> updateProductQuantity(@Valid @PathVariable("productId") final String productId,
			 @RequestParam(value = "quantity", required = true) final int quantity,
			 @RequestParam(value = "operation", required = true) final Operation operation) {
		productService.updateProductQuantity(productId, quantity, operation);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
