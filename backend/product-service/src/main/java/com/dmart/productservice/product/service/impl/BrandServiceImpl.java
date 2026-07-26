package com.dmart.productservice.product.service.impl;

import com.dmart.productservice.product.dto.request.CreateBrandRequest;
import com.dmart.productservice.product.dto.request.CreateProductRequest;
import com.dmart.productservice.product.dto.request.UpdateBrandRequest;
import com.dmart.productservice.product.dto.response.BrandResponse;
import com.dmart.productservice.product.entity.Brand;
import com.dmart.productservice.product.mapper.BrandMapper;
import com.dmart.productservice.product.repository.BrandRepository;
import com.dmart.productservice.product.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    @Override
    public BrandResponse createBrand(CreateBrandRequest request) {

        if(brandRepository.existsByName(request.getName())){
            throw new IllegalArgumentException("Brand with name " + request.getName() + " already exists");
        }


        Brand brand = brandMapper.toEntity(request);
        brand = brandRepository.save(brand);
        return brandMapper.toResponse(brand);

    }

    @Override
    public BrandResponse getBrandById(Long id) {
        if(id == null || id <=0){
            throw new IllegalArgumentException("Invalid brand ID");
        }
         BrandResponse brandResponse = brandRepository.findById(id)
                .map(brandMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Brand not found"));
       return brandResponse;
    }

    @Override
    public BrandResponse updateBrand(Long id, UpdateBrandRequest request) {

        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand not found"));

         brandMapper.updateEntity(request,brand);

         Brand updatedBrand = brandRepository.save(brand);

        return brandMapper.toResponse(updatedBrand);
    }

    @Override
    public BrandResponse findBrandById(Long id) {
        BrandResponse response = brandRepository.findById(id)
                .map(brandMapper::toResponse)
                .orElse(null);
        return response;
    }

    @Override
    public List<BrandResponse> getAllBrands() {
        List<BrandResponse> brandResponses = brandRepository.findAll()
                .stream()
                .map(brandMapper::toResponse)
                .collect(Collectors.toList());
        return brandResponses;
    }

    @Override
    public void deleteBrand(Long id) {
        if(id == null || id <=0){
            throw new IllegalArgumentException("Invalid brand ID");
        }
        Optional<Brand> brandOptional = brandRepository.findById(id);
        if (brandOptional.isPresent()) {
            Brand brand = brandOptional.get();
            brand.setActive(false); // Mark the brand as inactive
            brandRepository.save(brand);
        } else {
            throw new RuntimeException("Brand not found");
        }
    }

    @Override
    public Brand findBrandByName(CreateProductRequest request) {
        return null;
    }
    // Implement the methods defined in the BrandService interface
}
