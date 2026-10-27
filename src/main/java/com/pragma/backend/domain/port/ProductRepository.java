package com.pragma.backend.domain.port;

import com.pragma.backend.domain.model.Product;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {
    Product save(Product product);
    
    Optional<Product> findById(UUID id);
    
    List<Product> findAll();
    
    List<Product> findByCategory(String category);
    
    List<Product> findByActive(boolean active);
    
    void deleteById(UUID id);
    
    boolean existsById(UUID id);
}