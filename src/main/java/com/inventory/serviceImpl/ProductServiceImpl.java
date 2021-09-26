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
        if(product!=null && product.getProductId()!=null){
            Product existingProduct = (Product) productRepository.findByProductId(product.getProductId()).orElse(null);
            product.setProductId(existingProduct.getProductId());
            product.setProductName(existingProduct.getProductName());
            product.setProductCategory(existingProduct.getProductCategory());
            product.setProductDescription(existingProduct.getProductDescription());
            product.setUnits(existingProduct.getUnits());
            return productRepository.save(product);
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
