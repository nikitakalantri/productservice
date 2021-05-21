package com.productservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.productservice.enums.Operation;
import com.productservice.model.Product;

@Service
public interface ProductService {

	public void addProduct(Product product);

	public Product getProductDetails(String productId);

	public void updateProductQuantity(String productId, int quantity, Operation operation);

	public List<Product> getAllProducts();

}
