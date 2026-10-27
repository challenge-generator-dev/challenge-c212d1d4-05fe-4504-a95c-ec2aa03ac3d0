package com.pragma.backend.infrastructure.exception;

import java.util.UUID;

public class ProductNotFoundException extends RuntimeException {

    private final UUID productId;
    private final String productName;
    private final long timestamp;

    public ProductNotFoundException(UUID productId) {
        super(buildMessage(null, productId, null));
        this.productId = productId;
        this.productName = null;
        this.timestamp = System.currentTimeMillis();
        validateInvariant();
    }

    public ProductNotFoundException(String productName) {
        super(buildMessage(productName, null, null));
        this.productId = null;
        this.productName = productName;
        this.timestamp = System.currentTimeMillis();
        validateInvariant();
    }

    public ProductNotFoundException(UUID productId, String additionalContext) {
        super(buildMessage(null, productId, additionalContext));
        this.productId = productId;
        this.productName = null;
        this.timestamp = System.currentTimeMillis();
        validateInvariant();
    }

    private static String buildMessage(String productName, UUID productId, String context) {
        StringBuilder sb = new StringBuilder("Producto no encontrado");
        if (productId != null) {
            sb.append(": ID=").append(productId);
        } else if (productName != null && !productName.isBlank()) {
            sb.append(": nombre='").append(productName).append("'");
        }
        if (context != null && !context.isBlank()) {
            sb.append(" (").append(context).append(")");
        }
        return sb.toString();
    }

    private void validateInvariant() {
        if (productId == null && (productName == null || productName.isBlank())) {
            throw new IllegalStateException("ProductNotFoundException debe tener al menos productId o productName");
        }
    }

    public UUID getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getMessageId() {
        if (productId != null) {
            return "PROD_NOT_FOUND_" + productId.toString().substring(0, 8).toUpperCase();
        }
        return "PROD_NOT_FOUND_" + (productName != null ? productName.toUpperCase().replace(" ", "_") : "UNKNOWN");
    }
}