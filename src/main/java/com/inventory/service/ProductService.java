package com.inventory.service;

import com.inventory.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    List<Product> getProductsByCategory(String category);

    Product addNewProduct(Product product);

    Product updateProduct(Product product);

    List<Product> deleteProduct(String productId);
}
