package com.dmart.productservice.product.mapper;

import com.dmart.productservice.product.dto.request.CreateProductRequest;
import com.dmart.productservice.product.dto.request.UpdateProductRequest;
import com.dmart.productservice.product.dto.response.ProductResponse;
import com.dmart.productservice.product.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    Product toEntity(CreateProductRequest request){
     return Product.builder()
             .name(request.getName())
             .description(request.getDescription())
             .mrp(request.getMrp())
             .sellingPrice(request.getSellingPrice())
             .active(true)
             .build();
    }

    Product updateProduct(UpdateProductRequest request){
        return Product.builder()
                .mrp(request.getMrp())
                .sellingPrice(request.getSellingPrice())
                .build();
    }

    ProductResponse toResponse(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .sku(product.getSku())
                .barcode(product.getBarcode())
                .brandName(product.getBrand().getName())
                .categoryName(product.getCategory().getName())
                .mrp(product.getMrp())
                .sellingPrice(product.getSellingPrice())
                .active(product.getActive())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }

}
