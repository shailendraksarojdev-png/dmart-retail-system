package com.dmart.productservice.product.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateProductRequest {

    @NotBlank(message = "Product name is required")
    @Size(max = 255, message = "Product name cannot exceed 255 characters")
    private String name;

    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;

    @NotNull(message = "MRP is required")
    @DecimalMin(value = "0.01", message = "MRP must be greater than zero")
    private Double mrp;

    @NotNull(message = "Selling Price is required")
    @DecimalMin(value = "0.01", message = "Selling Price must be greater than zero")
    private Double sellingPrice;

    @NotBlank(message = "Brand name is required")
    private String brandName;

    @NotBlank(message = "Category name is required")
    private String categoryName;
}
