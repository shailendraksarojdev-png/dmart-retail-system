package com.dmart.productservice.product.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCategoryRequest {

    @NotNull(message = "Category name is required")
    @Size(max = 255, message = "Category name cannot exceed 255 characters")
    private String name;

    @NotNull(message = "Category description is required")
    @Size(max = 1000, message = "Category description cannot exceed 1000 characters")
    private String description;

}
