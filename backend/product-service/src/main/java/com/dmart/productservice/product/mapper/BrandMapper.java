package com.dmart.productservice.product.mapper;

import com.dmart.productservice.product.dto.request.CreateBrandRequest;
import com.dmart.productservice.product.dto.request.UpdateBrandRequest;
import com.dmart.productservice.product.dto.response.BrandResponse;
import com.dmart.productservice.product.entity.Brand;
import org.springframework.stereotype.Component;

@Component
public class BrandMapper {

    public Brand toEntity(CreateBrandRequest request ){
        return Brand.builder()
                .name(request.getName())
                .description(request.getDescription())
                .active(true)
                .build();
    }

    public void updateEntity(UpdateBrandRequest request, Brand brand){
        brand.setName(request.getName());
        brand.setDescription(request.getDescription());
    }

    public BrandResponse toResponse(Brand brand){
        return BrandResponse.builder()
                .id(brand.getId())
                .name(brand.getName())
                .description(brand.getDescription())
                .active(brand.isActive())
                .createdAt(brand.getCreatedAt().toString())
                .updatedAt(brand.getUpdatedAt().toString())
                .build();
    }
}
