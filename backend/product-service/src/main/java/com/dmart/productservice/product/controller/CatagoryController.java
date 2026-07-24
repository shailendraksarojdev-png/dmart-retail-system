package com.dmart.productservice.product.controller;

import com.dmart.productservice.product.dto.request.CreateCategoryRequest;
import com.dmart.productservice.product.dto.request.UpdateCatagoryRequest;
import com.dmart.productservice.product.dto.response.CategoryResponse;
import com.dmart.productservice.product.service.impl.CategoryServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CatagoryController {
    private final CategoryServiceImpl categoryService;

    @PostMapping("/create")
    public ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CreateCategoryRequest request) {
        // Implementation for creating a category
        CategoryResponse response = categoryService.createCategory(request);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/getByName")
    public ResponseEntity<CategoryResponse> getCategoryByName(String name) {
        CategoryResponse response = categoryService.getCategoryByName(name);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable Long id, @Valid @RequestBody UpdateCatagoryRequest request) {
        CategoryResponse response = categoryService.updateCategory(id, request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/updateStatus/{id}")
    public ResponseEntity<CategoryResponse> updateCategoryStatus(@PathVariable Long id, @RequestParam boolean active) {
        CategoryResponse response = categoryService.updateCategoryStatus(id, active);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/updateName/{id}")
    public ResponseEntity<CategoryResponse> updateCategoryName(@PathVariable Long id, @RequestParam String name) {
        CategoryResponse response = categoryService.updateCategoryName(id, name);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getAllCategories")
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        List<CategoryResponse> response = categoryService.getAllCategories();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/saveMultipleCategories")
    public ResponseEntity<List<CategoryResponse>> saveMultipleCategories(@Valid @RequestBody List<CreateCategoryRequest> requests) {
        List<CategoryResponse> result = categoryService.saveMultipleCategories(requests);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAllCategories() {
        categoryService.deleteAllCategories();
        return ResponseEntity.noContent().build();
    }


}
