package com.dmart.productservice.product.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateProductRequest {

    @NotNull(message = "Product name is required")
    @Size(max = 255, message = "Product name cannot exceed 255 characters")
    private String name;

    @NotNull(message = "Product price is required")
    @Size(max = 1000, message = "Product description cannot exceed 1000 characters")
    private String description;

}
