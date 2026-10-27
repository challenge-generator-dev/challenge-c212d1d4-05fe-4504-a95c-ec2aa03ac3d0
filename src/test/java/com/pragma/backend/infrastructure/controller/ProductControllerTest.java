package com.pragma.backend.infrastructure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.backend.application.service.ProductService;
import com.pragma.backend.domain.model.Product;
import com.pragma.backend.infrastructure.exception.GlobalExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
@Import(GlobalExceptionHandler.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductService productService;

    private UUID productId;
    private Product product;
    private String productJson;

    @BeforeEach
    void setUp() {
        productId = UUID.randomUUID();
        product = new Product(
            productId,
            "Test Product",
            "Test Description",
            new BigDecimal("99.99"),
            "ELECTRONICS",
            100,
            true,
            LocalDate.now(),
            LocalDate.now()
        );
        productJson = """
            {
                "name": "Test Product",
                "description": "Test Description",
                "price": 99.99,
                "category": "ELECTRONICS",
                "stock": 100
            }
            """;
    }

    @Test
    @DisplayName("GET /api/products debe retornar todos los productos")
    void getAllProducts_ReturnsProductList() throws Exception {
        when(productService.getAllProducts()).thenReturn(List.of(product));

        mockMvc.perform(get("/api/products"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[0].id").value(productId.toString()))
            .andExpect(jsonPath("$[0].name").value("Test Product"))
            .andExpect(jsonPath("$[0].category").value("ELECTRONICS"));

        verify(productService).getAllProducts();
    }

    @Test
    @DisplayName("GET /api/products/{id} debe retornar un producto por su ID")
    void getProductById_ReturnsProduct() throws Exception {
        when(productService.getProductById(productId)).thenReturn(Optional.of(product));

        mockMvc.perform(get("/api/products/{id}", productId))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(productId.toString()))
            .andExpect(jsonPath("$.name").value("Test Product"))
            .andExpect(jsonPath("$.price").value(99.99));

        verify(productService).getProductById(productId);
    }

    @Test
    @DisplayName("GET /api/products/{id} debe retornar 404 cuando el producto no existe")
    void getProductById_NotFound() throws Exception {
        when(productService.getProductById(productId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/products/{id}", productId))
            .andExpect(status().isNotFound());

        verify(productService).getProductById(productId);
    }

    @Test
    @DisplayName("GET /api/products/category/{category} debe filtrar productos por categoría")
    void getProductsByCategory_ReturnsFilteredList() throws Exception {
        when(productService.getProductsByCategory("ELECTRONICS")).thenReturn(List.of(product));

        mockMvc.perform(get("/api/products/category/{category}", "ELECTRONICS"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[0].category").value("ELECTRONICS"))
            .andExpect(jsonPath("$[0].name").value("Test Product"));

        verify(productService).getProductsByCategory("ELECTRONICS");
    }

    @Test
    @DisplayName("GET /api/products/active debe retornar solo productos activos")
    void getActiveProducts_ReturnsOnlyActiveProducts() throws Exception {
        when(productService.getActiveProducts()).thenReturn(List.of(product));

        mockMvc.perform(get("/api/products/active"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[0].active").value(true));

        verify(productService).getActiveProducts();
    }

    @Test
    @DisplayName("POST /api/products debe crear un nuevo producto")
    void createProduct_ReturnsCreated() throws Exception {
        when(productService.createProduct(any(Product.class))).thenReturn(product);

        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productJson))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.name").value("Test Product"))
            .andExpect(header().string("Location", "/api/products/" + productId));

        verify(productService).createProduct(any(Product.class));
    }

    @Test
    @DisplayName("POST /api/products debe retornar 400 cuando el nombre está vacío")
    void createProduct_ValidationError_NameBlank() throws Exception {
        String invalidJson = """
            {
                "name": "",
                "description": "Test Description",
                "price": 99.99,
                "category": "ELECTRONICS",
                "stock": 100
            }
            """;

        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidJson))
            .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST /api/products debe retornar 400 cuando el precio es negativo")
    void createProduct_ValidationError_NegativePrice() throws Exception {
        String invalidJson = """
            {
                "name": "Test Product",
                "description": "Test Description",
                "price": -10.00,
                "category": "ELECTRONICS",
                "stock": 100
            }
            """;

        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidJson))
            .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("PUT /api/products/{id} debe actualizar un producto existente")
    void updateProduct_ReturnsOk() throws Exception {
        Product updatedProduct = new Product(
            productId,
            "Updated Product",
            "Updated Description",
            new BigDecimal("149.99"),
            "ELECTRONICS",
            200,
            true,
            LocalDate.now(),
            LocalDate.now()
        );

        when(productService.updateProduct(eq(productId), any(Product.class))).thenReturn(Optional.of(updatedProduct));

        String updateJson = """
            {
                "name": "Updated Product",
                "description": "Updated Description",
                "price": 149.99,
                "category": "ELECTRONICS",
                "stock": 200
            }
            """;

        mockMvc.perform(put("/api/products/{id}", productId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateJson))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.name").value("Updated Product"))
            .andExpect(jsonPath("$.price").value(149.99));

        verify(productService).updateProduct(eq(productId), any(Product.class));
    }

    @Test
    @DisplayName("PUT /api/products/{id} debe retornar 404 cuando el producto no existe")
    void updateProduct_NotFound() throws Exception {
        when(productService.updateProduct(eq(productId), any(Product.class))).thenReturn(Optional.empty());

        String updateJson = """
            {
                "name": "Updated Product",
                "description": "Updated Description",
                "price": 149.99,
                "category": "ELECTRONICS",
                "stock": 200
            }
            """;

        mockMvc.perform(put("/api/products/{id}", productId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateJson))
            .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("DELETE /api/products/{id} debe eliminar un producto")
    void deleteProduct_ReturnsNoContent() throws Exception {
        doNothing().when(productService).deleteProduct(productId);

        mockMvc.perform(delete("/api/products/{id}", productId))
            .andExpect(status().isNoContent());

        verify(productService).deleteProduct(productId);
    }

    @Test
    @DisplayName("DELETE /api/products/{id} debe retornar 404 cuando el producto no existe")
    void deleteProduct_NotFound() throws Exception {
        doNothing().when(productService).deleteProduct(productId);

        mockMvc.perform(delete("/api/products/{id}", productId))
            .andExpect(status().isNoContent());

        verify(productService).deleteProduct(productId);
    }

    @Test
    @DisplayName("PATCH /api/products/{id}/activate debe activar un producto")
    void activateProduct_ReturnsOk() throws Exception {
        Product activatedProduct = product.activate();
        when(productService.activateProduct(productId)).thenReturn(Optional.of(activatedProduct));

        mockMvc.perform(patch("/api/products/{id}/activate", productId))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.active").value(true));

        verify(productService).activateProduct(productId);
    }

    @Test
    @DisplayName("PATCH /api/products/{id}/deactivate debe desactivar un producto")
    void deactivateProduct_ReturnsOk() throws Exception {
        Product deactivatedProduct = product.deactivate();
        when(productService.deactivateProduct(productId)).thenReturn(Optional.of(deactivatedProduct));

        mockMvc.perform(patch("/api/products/{id}/deactivate", productId))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.active").value(false));

        verify(productService).deactivateProduct(productId);
    }
}