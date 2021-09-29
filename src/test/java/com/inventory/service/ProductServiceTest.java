package com.inventory.service;

import com.inventory.entity.Product;
import com.inventory.repository.ProductRepository;
import com.inventory.serviceImpl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class ProductServiceTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    public ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Product product1 = Product.builder().productId("P01")
                .productName("H123")
                .productCategory("Helicopter").productDescription("aerospace").units(5)
                .build();
        Product product2 = Product.builder().productId("P02")
                .productName("H124")
                .productCategory("Helicopter").productDescription("aerospace").units(5)
                .build();

        List<Product> listOfProducts = new ArrayList<>();
        listOfProducts.add(product1);
        listOfProducts.add(product2);

        Mockito.when(productRepository.findAll()).thenReturn(listOfProducts);
        Mockito.when(productRepository.getByProductCategory("Helicopter")).thenReturn(listOfProducts);

    }

    @Test
    @DisplayName("Get all the products")
    public void getAllProducts() {
     List<Product> productsList = productService.getAllProducts();
     assertNotNull(productsList);
    }

    @Test
    @DisplayName("Get all the products by category")
    void getProductsByCategory() {
        String category = "Helicopter";
        List<Product> productsList = productService.getProductsByCategory(category);
        for ( Product product: productsList) {
            assertEquals(category, product.getProductCategory());
        }
    }

    @Test
    @DisplayName("Add new Product")
    void addNewProduct() {
        Product product = Product.builder().productId("P03")
                .productName("Satellite").productCategory("Space").productDescription("space product")
                .units(10).build();
        Mockito.when(productRepository.save(product)).thenReturn(product);
        Product savedProduct = productService.addNewProduct(product);
        assertEquals(product.getProductId(),savedProduct.getProductId());
    }

    @Test
    @DisplayName("update the existing product")
    void updateProduct() {
        Product product = Product.builder().productId("P04")
                .productName("Satellite").productCategory("Space").productDescription("new space product")
                .units(15).build();
        Mockito.when(productRepository.findByProductId(product.getProductId())).thenReturn(java.util.Optional.of(product));
        Mockito.when(productRepository.save(any(Product.class))).thenReturn(product);
        Product updatedProduct = productService.updateProduct(product);
        assertEquals(product.getProductDescription(),updatedProduct.getProductDescription());
    }

    @Test
    @DisplayName("delete product")
    void deleteProduct() {
        String productId = "P03";
        Product product = Product.builder().productId("P03")
                .productName("Satellite").productCategory("Space").productDescription("space product")
                .units(10).build();
        Mockito.when(productRepository.findByProductId(productId)).thenReturn(java.util.Optional.ofNullable(product));
        productService.deleteProduct(product.getProductId());
        verify(productRepository, times(1)).delete(product);

    }
}