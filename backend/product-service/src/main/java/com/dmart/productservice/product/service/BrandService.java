package com.dmart.productservice.product.service;

import com.dmart.productservice.product.dto.request.CreateBrandRequest;
import com.dmart.productservice.product.dto.request.CreateProductRequest;
import com.dmart.productservice.product.dto.request.UpdateBrandRequest;
import com.dmart.productservice.product.dto.response.BrandResponse;
import com.dmart.productservice.product.entity.Brand;
import com.dmart.productservice.product.entity.Catagory;

import java.util.List;


public interface BrandService {
    BrandResponse createBrand(CreateBrandRequest request);

    BrandResponse getBrandById(Long id);

    BrandResponse updateBrand(Long id, UpdateBrandRequest request);

    BrandResponse findBrandById(Long id);

    List<BrandResponse> getAllBrands();

    void deleteBrand(Long id);

    Brand findBrandByName(CreateProductRequest request);

}
