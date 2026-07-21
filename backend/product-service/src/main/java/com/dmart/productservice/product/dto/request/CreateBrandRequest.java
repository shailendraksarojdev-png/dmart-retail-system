package com.dmart.productservice.product.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateBrandRequest {

    @NotBlank(message = "Brand name is required")
    @Size(max = 255, message = "Brand name cannot exceed 255 characters")
    private String name;

    @Size(max = 255, message = "Brand description cannot exceed 255 characters")
    private String description;


}
