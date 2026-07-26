package com.dmart.productservice.product.service;

import com.dmart.productservice.product.dto.request.CreateProductRequest;
import com.dmart.productservice.product.dto.request.UpdateProductRequest;
import com.dmart.productservice.product.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse createProduct(CreateProductRequest request);

    ProductResponse updateProduct(Long id, UpdateProductRequest request);

    ProductResponse getProductById(Long id);

    List<ProductResponse> getAllProducts();

    void deleteProduct(Long id);
}
