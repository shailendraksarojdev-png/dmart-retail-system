package com.dmart.productservice.product.entity;

import com.dmart.productservice.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "category")
public class Catagory extends BaseEntity {

    @NotNull(message = "Category name is required")
    @Size(max = 255, message = "Category name cannot exceed 255 characters")
    @Column(name = "name", nullable = false, unique = true)
    private String name;


    @NotNull(message = "Category description is required")
    @Size(max = 1000, message = "Category description cannot exceed 1000 characters")
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "active", nullable = false)
    private boolean active = true;

}
