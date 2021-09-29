package com.inventory.serviceImpl;

import com.inventory.entity.Product;
import com.inventory.repository.ProductRepository;
import com.inventory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
            Optional<Product> existingProduct = productRepository.findByProductId(product.getProductId());
            if(existingProduct.get()!=null){
                existingProduct.get().setProductName(product.getProductName());
                existingProduct.get().setProductCategory(product.getProductCategory());
                existingProduct.get().setProductDescription(product.getProductDescription());
                existingProduct.get().setUnits(product.getUnits());
                return productRepository.save(existingProduct.get());
            }
            return null;
        }
        return null;
    }

    @Override
    public Product deleteProduct(String productId) {
         Optional<Product> existingProducts = productRepository.findByProductId(productId);
         if(existingProducts.get()!=null)
         {
             productRepository.delete(existingProducts.get());
             return existingProducts.get();
         }
         return null;
    }
}
