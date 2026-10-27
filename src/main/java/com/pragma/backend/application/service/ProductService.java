package com.pragma.backend.application.service;

import com.pragma.backend.domain.model.Product;
import com.pragma.backend.domain.port.ProductRepository;
import com.pragma.backend.infrastructure.exception.ProductNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public Product createProduct(String name, String description, BigDecimal price, 
                                  String category, int stock) {
        String correlationId = UUID.randomUUID().toString();
        MDC.put("correlationId", correlationId);
        logger.info("Iniciando creacion de producto - nombre: {}, categoria: {}", name, category);

        Product product = new Product(
            UUID.randomUUID(),
            name,
            description,
            price,
            category,
            stock,
            true,
            LocalDate.now(),
            LocalDate.now()
        );

        Product savedProduct = productRepository.save(product);
        logger.info("Producto creado exitosamente - id: {}, nombre: {}", savedProduct.id(), savedProduct.name());
        MDC.clear();
        return savedProduct;
    }

    @Transactional(readOnly = true)
    public Product getProductById(UUID id) {
        String correlationId = UUID.randomUUID().toString();
        MDC.put("correlationId", correlationId);
        logger.info("Buscando producto por id: {}", id);

        Product product = productRepository.findById(id)
            .orElseThrow(() -> {
                logger.warn("Producto no encontrado - id: {}", id);
                return new ProductNotFoundException("Producto no encontrado con id: " + id);
            });

        logger.info("Producto encontrado - id: {}, nombre: {}", product.id(), product.name());
        MDC.clear();
        return product;
    }

    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        String correlationId = UUID.randomUUID().toString();
        MDC.put("correlationId", correlationId);
        logger.info("Obteniendo todos los productos");

        List<Product> products = productRepository.findAll();
        logger.info("Total de productos encontrados: {}", products.size());
        MDC.clear();
        return products;
    }

    @Transactional(readOnly = true)
    public List<Product> getProductsByCategory(String category) {
        String correlationId = UUID.randomUUID().toString();
        MDC.put("correlationId", correlationId);
        logger.info("Buscando productos por categoria: {}", category);

        List<Product> products = productRepository.findByCategory(category);
        logger.info("Productos encontrados en categoria {}: {}", category, products.size());
        MDC.clear();
        return products;
    }

    @Transactional(readOnly = true)
    public List<Product> getActiveProducts(boolean active) {
        String correlationId = UUID.randomUUID().toString();
        MDC.put("correlationId", correlationId);
        logger.info("Buscando productos activos: {}", active);

        List<Product> products = productRepository.findByActive(active);
        logger.info("Productos encontrados con estado {}: {}", active, products.size());
        MDC.clear();
        return products;
    }

    @Transactional
    public Product updateProduct(UUID id, String name, String description, 
                                  BigDecimal price, String category, int stock) {
        String correlationId = UUID.randomUUID().toString();
        MDC.put("correlationId", correlationId);
        logger.info("Actualizando producto - id: {}, nombre: {}", id, name);

        Product existingProduct = productRepository.findById(id)
            .orElseThrow(() -> {
                logger.warn("Producto no encontrado para actualizacion - id: {}", id);
                return new ProductNotFoundException("Producto no encontrado con id: " + id);
            });

        Product updatedProduct = new Product(
            existingProduct.id(),
            name != null ? name : existingProduct.name(),
            description != null ? description : existingProduct.description(),
            price != null ? price : existingProduct.price(),
            category != null ? category : existingProduct.category(),
            stock >= 0 ? stock : existingProduct.stock(),
            existingProduct.active(),
            existingProduct.createdAt(),
            LocalDate.now()
        );

        Product savedProduct = productRepository.save(updatedProduct);
        logger.info("Producto actualizado exitosamente - id: {}", savedProduct.id());
        MDC.clear();
        return savedProduct;
    }

    @Transactional
    public Product activateProduct(UUID id) {
        String correlationId = UUID.randomUUID().toString();
        MDC.put("correlationId", correlationId);
        logger.info("Activando producto - id: {}", id);

        Product product = productRepository.findById(id)
            .orElseThrow(() -> {
                logger.warn("Producto no encontrado para activacion - id: {}", id);
                return new ProductNotFoundException("Producto no encontrado con id: " + id);
            });

        Product activatedProduct = product.activate();
        Product savedProduct = productRepository.save(activatedProduct);
        logger.info("Producto activado - id: {}", savedProduct.id());
        MDC.clear();
        return savedProduct;
    }

    @Transactional
    public Product deactivateProduct(UUID id) {
        String correlationId = UUID.randomUUID().toString();
        MDC.put("correlationId", correlationId);
        logger.info("Desactivando producto - id: {}", id);

        Product product = productRepository.findById(id)
            .orElseThrow(() -> {
                logger.warn("Producto no encontrado para desactivacion - id: {}", id);
                return new ProductNotFoundException("Producto no encontrado con id: " + id);
            });

        Product deactivatedProduct = product.deactivate();
        Product savedProduct = productRepository.save(deactivatedProduct);
        logger.info("Producto desactivado - id: {}", savedProduct.id());
        MDC.clear();
        return savedProduct;
    }

    @Transactional
    public void deleteProduct(UUID id) {
        String correlationId = UUID.randomUUID().toString();
        MDC.put("correlationId", correlationId);
        logger.info("Eliminando producto - id: {}", id);

        if (!productRepository.existsById(id)) {
            logger.warn("Producto no encontrado para eliminacion - id: {}", id);
            throw new ProductNotFoundException("Producto no encontrado con id: " + id);
        }

        productRepository.deleteById(id);
        logger.info("Producto eliminado - id: {}", id);
        MDC.clear();
    }

    @Transactional(readOnly = true)
    public boolean existsProduct(UUID id) {
        String correlationId = UUID.randomUUID().toString();
        MDC.put("correlationId", correlationId);
        logger.debug("Verificando existencia de producto - id: {}", id);

        boolean exists = productRepository.existsById(id);
        logger.debug("Producto existe - id: {}, resultado: {}", id, exists);
        MDC.clear();
        return exists;
    }
}