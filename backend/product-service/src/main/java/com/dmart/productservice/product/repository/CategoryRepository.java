package com.dmart.productservice.product.repository;

import com.dmart.productservice.product.entity.Catagory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Catagory, Long> {
    Catagory findByName(String name);

}
