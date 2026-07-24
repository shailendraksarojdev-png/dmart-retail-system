package com.dmart.productservice.product.service.impl;

import com.dmart.productservice.product.dto.request.CreateCategoryRequest;
import com.dmart.productservice.product.dto.request.UpdateCatagoryRequest;
import com.dmart.productservice.product.dto.response.CategoryResponse;
import com.dmart.productservice.product.entity.Catagory;
import com.dmart.productservice.product.mapper.CategoryMapper;
import com.dmart.productservice.product.repository.CategoryRepository;
import com.dmart.productservice.product.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;
    @Override
    public CategoryResponse createCategory(CreateCategoryRequest request){
        Catagory category = categoryMapper.toEntity(request);
        category = categoryRepository.save(category);
        return  categoryMapper.toResponse(category);
    }

    @Override
    public CategoryResponse getCategoryByName(String name) {
        Catagory category = categoryRepository.findByName(name);
        if(category == null){
            throw new RuntimeException("Category not found");
        }
        return categoryMapper.toResponse(category);
    }

    @Override
    public CategoryResponse updateCategory(Long id, UpdateCatagoryRequest request) {

        Catagory category = categoryRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Category not found"));
        categoryMapper.updateEntity(request, category);
        categoryRepository.save(category);
        return categoryMapper.toResponse(category);
    }

    @Override
    public CategoryResponse updateCategoryStatus(Long id, boolean active) {
        Catagory category = categoryRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Category not found"));
        category.setActive(active);
        categoryRepository.save(category);
        return categoryMapper.toResponse(category);
    }

    @Override
    public CategoryResponse updateCategoryName(Long id, String name) {
        Catagory category = categoryRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Category not found"));
        category.setName(name);
        categoryRepository.save(category);
        return categoryMapper.toResponse(category);
    }

    @Override
    public List<CategoryResponse> saveMultipleCategories(List<CreateCategoryRequest> requests) {
        List<Catagory> categories = requests.stream()
                .map(categoryMapper::toEntity)
                .toList();
        List<Catagory> savedCategories = categoryRepository.saveAll(categories);
        return savedCategories.stream()
                .map(categoryMapper::toResponse)
                .toList();
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        List<CategoryResponse> categoryResponses = categoryRepository.findAll().stream()
                .map(categoryMapper::toResponse)
                .toList();
        return categoryResponses;
    }

    @Override
    public void deleteCategory(Long id) {

        categoryRepository.deleteById(id);


    }

    @Override
    public void deleteAllCategories() {
        categoryRepository.deleteAll();

    }
}
