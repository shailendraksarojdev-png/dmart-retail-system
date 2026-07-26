package com.dmart.productservice.product.mapper;

import com.dmart.productservice.product.dto.request.CreateCategoryRequest;
import com.dmart.productservice.product.dto.request.UpdateCatagoryRequest;
import com.dmart.productservice.product.dto.response.CategoryResponse;
import com.dmart.productservice.product.entity.Catagory;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Catagory toEntity(CreateCategoryRequest request) {

        return Catagory.builder()
                .name(request.getName())
                .description(request.getDescription())
                .active(true)
                .build();
    }
    public void updateEntity(UpdateCatagoryRequest request, Catagory category) {
        category.setName(request.getName());
        category.setDescription(request.getDescription());
    }


     public CategoryResponse toResponse(Catagory request){
        return CategoryResponse.builder()
                .name(request.getName())
                .description(request.getDescription())
                .active(true)
                .build();
     }

}
