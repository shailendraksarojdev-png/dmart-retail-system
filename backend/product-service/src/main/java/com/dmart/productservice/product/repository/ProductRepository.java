package com.dmart.productservice.product.repository;

import com.dmart.productservice.product.entity.Brand;
import com.dmart.productservice.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long > {

    Optional<Product> findByBarcode(String barcode);

    Optional<Product> findBySku(String sku);

    boolean existsByBarcode(String barcode);

    boolean existsBySku(String sku);

    List<Product> findAllByActiveTrue();

    List<Product> findByBrandId(Long brandId);

    List<Product> findByCategoryId(Long categoryId);


}
