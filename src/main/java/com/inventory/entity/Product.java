package com.inventory.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(name="product_id")
    String productId;
    @Column(name = "product_category")
    String productCategory;
    @Column(name = "product_name")
    String productName;
    @Column(name = "product_description")
    String productDescription;
    @Column(name = "units")
    Integer units;
}
