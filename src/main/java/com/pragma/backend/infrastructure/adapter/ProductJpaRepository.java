package com.pragma.backend.infrastructure.adapter;

import com.pragma.backend.domain.model.Product;
import com.pragma.backend.domain.port.ProductRepository;
import com.pragma.backend.infrastructure.entity.ProductEntity;
import com.pragma.backend.infrastructure.mapper.ProductMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public interface ProductJpaRepository extends ProductRepository, JpaRepository<ProductEntity, UUID> {
    @Override
    default Product save(Product product) {
        ProductEntity entity = ProductMapper.INSTANCE.toEntity(product);
        ProductEntity savedEntity = save(entity);
        return ProductMapper.INSTANCE.toDomain(savedEntity);
    }
    
    @Override
    default Optional<Product> findById(UUID id) {
        return findById(id).map(ProductMapper.INSTANCE::toDomain);
    }
    
    @Override
    default List<Product> findAll() {
        return findAll().stream()
                .map(ProductMapper.INSTANCE::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    default List<Product> findByCategory(String category) {
        return findByCategory(category).stream()
                .map(ProductMapper.INSTANCE::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    default List<Product> findByActive(boolean active) {
        return findByActive(active).stream()
                .map(ProductMapper.INSTANCE::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    default void deleteById(UUID id) {
        deleteById(id);
    }
    
    @Override
    default boolean existsById(UUID id) {
        return existsById(id);
    }
    
    List<ProductEntity> findByCategory(String category);
    
    List<ProductEntity> findByActive(boolean active);
}