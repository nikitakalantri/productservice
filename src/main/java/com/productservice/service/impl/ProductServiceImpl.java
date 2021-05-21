package com.productservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productservice.enums.Operation;
import com.productservice.model.Product;
import com.productservice.repo.ProductRepository;
import com.productservice.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepo;
	
	@Override
	public void addProduct(Product product) {
		String productId = product.getProductId();
		Product existingProduct = productRepo.findById(productId).get();
		if(null == existingProduct) {
			productRepo.save(product);
		}else {
			System.out.println("Product Already Exists.");
		}
	}

	@Override
	public List<Product> getAllProducts() {
		List<Product> products = new ArrayList<>();
		productRepo.findAll().forEach(products::add);
		return products;
	}

	@Override
	public Product getProductDetails(String productId) {
		return productRepo.findById(productId).get();
	}

	@Override
	public void updateProductQuantity(String productId, int quantity, Operation operation) {
		Product existingProduct = productRepo.findById(productId).get();
		int qty = 0;
		if(null != existingProduct) {
			int existingQuantity = existingProduct.getAvailableQuantity();
			qty = Operation.ADD.equals(operation) ? (existingQuantity + quantity) : (existingQuantity - quantity);
		}
		existingProduct.setAvailableQuantity(qty);
		productRepo.save(existingProduct);
	}

}
