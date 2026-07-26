package com.dmart.productservice.product.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "product")
public class Product {
//    id BIGSERIAL primary key,
//    name varchar(255) not null unique,
//    description text null,
//    mrp NUMERIC(10,2) not null,
//    selling_price NUMERIC(10,2) not null,
//    stock int not null,
//    status varchar(20) not null,
//    category_id BIGINT not null references catagory(id) on delete cascade,
//    brand_id BIGINT not null references brand(id) on delete cascade,
//    active boolean default true not null,
//    created_at timestamp default current_timestamp not null,
//    updated_at timestamp default current_timestamp not null,
//    sku varchar(30) not null unique,
//    barcode varchar(30) not null unique


    @Id
    @UuidGenerator
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "mrp", precision = 10, scale = 2, nullable = false)
    private Double mrp;

    @Column(name = "selling_price", precision = 10, scale = 2, nullable = false)
    private Double sellingPrice;

    @Size(min = 0, message = "Stock must be a non-negative integer")
    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "status")
    private String status;

    @Column(name = "active")
    private Boolean active;

    @Size(max = 16, message = "SKU must not exceed 16 characters")
    @Column(name = "sku", nullable = false, unique = true)
    private String sku;


    @Size(max = 16, message = "Barcode must not exceed 16 characters")
    @Column(name = "barcode", nullable = false, unique = true)
    private String barcode;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Catagory category;

}


