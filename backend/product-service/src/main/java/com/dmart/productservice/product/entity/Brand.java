package com.dmart.productservice.product.entity;

import com.dmart.productservice.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "brand")
public class Brand  extends BaseEntity {

    @NotBlank(message = "Brand name must not be blank")
    @Size(max = 255, message = "Brand name must not exceed 255 characters")
    @Column(name = "name",nullable = false, unique = true)
    private String name;

    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    @Column(name = "description")
    private String description;

    @Column(name = "active")
    private boolean active = true;


}
