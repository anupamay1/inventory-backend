package com.inventory.serviceImpl;

import com.inventory.entity.Product;
import com.inventory.repository.ProductRepository;
import com.inventory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    public ProductRepository productRepository;
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.getByProductCategory(category);
    }

    @Override
    public Product addNewProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        if(product!=null){
            Product existingProduct = (Product) productRepository.findByProductId(product.getProductId()).orElse(null);
            existingProduct.setProductName(product.getProductName());
            existingProduct.setProductCategory(product.getProductCategory());
            existingProduct.setProductDescription(product.getProductDescription());
            existingProduct.setUnits(product.getUnits());
            return productRepository.save(existingProduct);
        }
        return null;
    }

    @Override
    public List<Product> deleteProduct(String productId) {
         List<Product> existingProducts = productRepository.getByProductId(productId);
         productRepository.deleteAll(existingProducts);
         return existingProducts;
    }
}
