package com.dmart.productservice.product.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandResponse {

    private Long id;
    private String name;
    private String description;
    private boolean active;
    private String createdAt;
    private String updatedAt;


}
