package com.dmart.productservice.product.controller;

import com.dmart.productservice.product.dto.request.CreateBrandRequest;
import com.dmart.productservice.product.dto.request.UpdateBrandRequest;
import com.dmart.productservice.product.dto.response.BrandResponse;
import com.dmart.productservice.product.entity.Brand;
import com.dmart.productservice.product.service.BrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/brands")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @PostMapping("/create")
    public ResponseEntity<BrandResponse> createBrand(
            @Valid @RequestBody CreateBrandRequest request) {
        // Implementation for creating a brand

        BrandResponse response = brandService.createBrand(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandResponse> getBrandById(@PathVariable Long id) {

        return ResponseEntity.ok(brandService.getBrandById(id));
    }

    @GetMapping("allBrands")
    public ResponseEntity<List<BrandResponse>> getAllBrands() {

        return ResponseEntity.ok(brandService.getAllBrands());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BrandResponse> updateBrand(
            @PathVariable Long id,
            @Valid @RequestBody UpdateBrandRequest request) {

        return ResponseEntity.ok(
                brandService.updateBrand(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable Long id) {

        brandService.deleteBrand(id);

        return ResponseEntity.noContent().build();
    }


}
