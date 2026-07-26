package com.dmart.productservice.product.service;

import com.dmart.productservice.product.dto.request.CreateCategoryRequest;
import com.dmart.productservice.product.dto.request.CreateProductRequest;
import com.dmart.productservice.product.dto.request.UpdateCatagoryRequest;
import com.dmart.productservice.product.dto.response.CategoryResponse;
import com.dmart.productservice.product.entity.Catagory;


import java.util.List;

public interface CategoryService {

     CategoryResponse createCategory(CreateCategoryRequest request);

     CategoryResponse getCategoryByName(String name);

     CategoryResponse updateCategory(Long id, UpdateCatagoryRequest request);

    CategoryResponse updateCategoryStatus(Long id, boolean active);

    CategoryResponse updateCategoryName(Long id, String name);

    List<CategoryResponse> saveMultipleCategories(List<CreateCategoryRequest> requests);

    List<CategoryResponse> getAllCategories();

   void deleteCategory(Long id);

    void deleteAllCategories();

    Catagory findCategoryByName(CreateProductRequest request);
}
