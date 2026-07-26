package com.dmart.productservice.product.dto.response;


import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {

    private Long id;

    private String name;

    private String description;

    private String sku;

    private String barcode;

    private String brandName;

    private String categoryName;

    private Double mrp;

    private Double sellingPrice;

    private Boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
