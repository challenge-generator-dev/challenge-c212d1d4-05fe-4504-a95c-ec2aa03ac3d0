package com.pragma.backend.infrastructure.controller;

import com.pragma.backend.application.service.ProductService;
import com.pragma.backend.domain.model.Product;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    public record CreateProductRequest(
        @NotBlank(message = "El nombre es obligatorio") String name,
        @NotBlank(message = "La descripcion es obligatoria") String description,
        @NotNull(message = "El precio es obligatorio") @Positive(message = "El precio debe ser positivo") BigDecimal price,
        @NotBlank(message = "La categoria es obligatoria") String category,
        @NotNull(message = "El stock es obligatorio") @Positive(message = "El stock debe ser positivo") Integer stock
    ) {}

    public record UpdateProductRequest(
        String name,
        String description,
        BigDecimal price,
        String category,
        Integer stock
    ) {}

    public record ProductResponse(
        UUID id,
        String name,
        String description,
        BigDecimal price,
        String category,
        int stock,
        boolean active,
        String createdAt,
        String updatedAt
    ) {
        public static ProductResponse fromDomain(Product product) {
            return new ProductResponse(
                product.id(),
                product.name(),
                product.description(),
                product.price(),
                product.category(),
                product.stock(),
                product.active(),
                product.createdAt().toString(),
                product.updatedAt().toString()
            );
        }
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody CreateProductRequest request) {
        String correlationId = UUID.randomUUID().toString();
        MDC.put("correlationId", correlationId);
        logger.info("Peticion REST - POST /api/v1/products - Creando producto: {}", request.name());

        Product product = productService.createProduct(
            request.name(),
            request.description(),
            request.price(),
            request.category(),
            request.stock()
        );

        logger.info("Producto creado exitosamente - id: {}", product.id());
        MDC.clear();
        return ResponseEntity.status(HttpStatus.CREATED).body(ProductResponse.fromDomain(product));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable UUID id) {
        String correlationId = UUID.randomUUID().toString();
        MDC.put("correlationId", correlationId);
        logger.info("Peticion REST - GET /api/v1/products/{}", id);

        Product product = productService.getProductById(id);
        logger.info("Producto obtenido - id: {}", product.id());
        MDC.clear();
        return ResponseEntity.ok(ProductResponse.fromDomain(product));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Boolean active) {
        String correlationId = UUID.randomUUID().toString();
        MDC.put("correlationId", correlationId);
        logger.info("Peticion REST - GET /api/v1/products - category: {}, active: {}", category, active);

        List<Product> products;
        if (category != null && !category.isBlank()) {
            products = productService.getProductsByCategory(category);
        } else if (active != null) {
            products = productService.getActiveProducts(active);
        } else {
            products = productService.getAllProducts();
        }

        List<ProductResponse> responses = products.stream()
            .map(ProductResponse::fromDomain)
            .toList();

        logger.info("Productos obtenidos - cantidad: {}", responses.size());
        MDC.clear();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateProductRequest request) {
        String correlationId = UUID.randomUUID().toString();
        MDC.put("correlationId", correlationId);
        logger.info("Peticion REST - PUT /api/v1/products/{} - Actualizando producto", id);

        Product product = productService.updateProduct(
            id,
            request.name(),
            request.description(),
            request.price(),
            request.category(),
            request.stock()
        );

        logger.info("Producto actualizado - id: {}", product.id());
        MDC.clear();
        return ResponseEntity.ok(ProductResponse.fromDomain(product));
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<ProductResponse> activateProduct(@PathVariable UUID id) {
        String correlationId = UUID.randomUUID().toString();
        MDC.put("correlationId", correlationId);
        logger.info("Peticion REST - PATCH /api/v1/products/{}/activate", id);

        Product product = productService.activateProduct(id);
        logger.info("Producto activado - id: {}", product.id());
        MDC.clear();
        return ResponseEntity.ok(ProductResponse.fromDomain(product));
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<ProductResponse> deactivateProduct(@PathVariable UUID id) {
        String correlationId = UUID.randomUUID().toString();
        MDC.put("correlationId", correlationId);
        logger.info("Peticion REST - PATCH /api/v1/products/{}/deactivate", id);

        Product product = productService.deactivateProduct(id);
        logger.info("Producto desactivado - id: {}", product.id());
        MDC.clear();
        return ResponseEntity.ok(ProductResponse.fromDomain(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        String correlationId = UUID.randomUUID().toString();
        MDC.put("correlationId", correlationId);
        logger.info("Peticion REST - DELETE /api/v1/products/{}", id);

        productService.deleteProduct(id);
        logger.info("Producto eliminado - id: {}", id);
        MDC.clear();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> existsProduct(@PathVariable UUID id) {
        String correlationId = UUID.randomUUID().toString();
        MDC.put("correlationId", correlationId);
        logger.info("Peticion REST - GET /api/v1/products/{}/exists", id);

        boolean exists = productService.existsProduct(id);
        logger.info("Verificacion de existencia - id: {}, resultado: {}", id, exists);
        MDC.clear();
        return ResponseEntity.ok(exists);
    }
}