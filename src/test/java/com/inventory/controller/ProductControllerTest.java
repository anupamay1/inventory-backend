package com.inventory.controller;

import com.inventory.entity.Product;
import com.inventory.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductControllerTest {

    @InjectMocks
    ProductController productController;

    @Mock
    ProductService productService;

    @Test
    void getAllProductsEquals() {
        List<Product> product=new ArrayList<Product>();
        product.add(new Product(1,"P01","Commercial","A320","Passenger aircraft family", 4));
        product.add(new Product(2,"P02","Commercial","A322","Passenger aircraft family", 4));
        when(productService.getAllProducts()).thenReturn(product);
        List<Product> result = productController.getAllProducts();
        assertEquals(2, result.size());
    }
    @Test
    void getAllProductsNotEquals() {
        List<Product> product=new ArrayList<>();
        product.add(new Product(1,"P01","Commercial","A320","Passenger aircraft family", 4));
        product.add(new Product(2,"P02","Commercial","A322","Passenger aircraft family", 4));
        when(productService.getAllProducts()).thenReturn(product);
        List<Product> result = productController.getAllProducts();
        assertNotEquals(3, result.size());
    }


    @Test
    void getProductsByCategory() {
        List<Product> product=new ArrayList<>();
        product.add(new Product(1,"P01","Commercial","A320","Passenger aircraft family", 4));
        product.add(new Product(2,"P02","Commercial","A322","Passenger aircraft family", 4));
        when(productService.getProductsByCategory("Commercial")).thenReturn(product);
        List<Product> result = productController.getProductsByCategory("Commercial");
        result.forEach(product1 -> {
            assertTrue(product1.getProductCategory().equals("Commercial"));
        });
    }

    @Test
    void addNewProduct() {
        Product prod = new Product(1,"P10","Commercial","A320","Passenger aircraft family", 4);
        productController.addNewProduct(prod);
        verify(productService, times(1)).addNewProduct(prod);
    }

    @Test
    void updateProduct() {
        Product prod = new Product(1,"P10","Commercial","A320","Passenger aircraft family", 4);
        productController.updateProduct(prod);
        verify(productService, times(1)).updateProduct(prod);
    }

    @Test
    void deleteProduct() {
        Product prod = new Product(1,"P01","Commercial","A320","Passenger aircraft family", 4);
        productController.deleteProduct(prod.getProductId());
        verify(productService, times(1)).deleteProduct(prod.getProductId());
    }
}