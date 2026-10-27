package com.pragma.backend.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record Product(
    UUID id,
    String name,
    String description,
    BigDecimal price,
    Integer stock,
    String category,
    LocalDate createdAt,
    LocalDate updatedAt,
    boolean active
) {
    public Product {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Product price must be positive");
        }
        if (stock == null || stock < 0) {
            throw new IllegalArgumentException("Product stock cannot be negative");
        }
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("Product category cannot be null or empty");
        }
    }

    public Product withId(UUID id) {
        return new Product(id, this.name, this.description, this.price, this.stock, this.category, this.createdAt, this.updatedAt, this.active);
    }

    public Product withUpdatedAt(LocalDate updatedAt) {
        return new Product(this.id, this.name, this.description, this.price, this.stock, this.category, this.createdAt, updatedAt, this.active);
    }

    public Product activate() {
        return new Product(this.id, this.name, this.description, this.price, this.stock, this.category, this.createdAt, this.updatedAt, true);
    }

    public Product deactivate() {
        return new Product(this.id, this.name, this.description, this.price, this.stock, this.category, this.createdAt, this.updatedAt, false);
    }
}