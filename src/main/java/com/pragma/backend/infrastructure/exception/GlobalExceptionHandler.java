package com.pragma.backend.infrastructure.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private static final String CORRELATION_ID_HEADER = "X-Correlation-ID";

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFound(
            ProductNotFoundException ex, WebRequest request) {
        String correlationId = getOrGenerateCorrelationId(request);
        MDC.put("correlationId", correlationId);
        MDC.put("exception", "ProductNotFoundException");

        log.warn("Producto no encontrado - CorrelationId: {}, ProductId: {}, Message: {}",
                correlationId, ex.getProductId(), ex.getMessage());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .error("PRODUCT_NOT_FOUND")
                .message(ex.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .timestamp(Instant.now().toString())
                .path(request.getDescription(false).replace("uri=", ""))
                .correlationId(correlationId)
                .build();

        MDC.clear();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationErrors(
            MethodArgumentNotValidException ex, WebRequest request) {
        String correlationId = getOrGenerateCorrelationId(request);
        MDC.put("correlationId", correlationId);
        MDC.put("exception", "ValidationException");

        log.warn("Error de validacion - CorrelationId: {}, Errors: {}",
                correlationId, ex.getBindingResult().getFieldErrors().size());

        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                fieldErrors.put(error.getField(), error.getDefaultMessage())
        );

        ValidationErrorResponse response = ValidationErrorResponse.builder()
                .error("VALIDATION_ERROR")
                .message("Error en los datos enviados")
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(Instant.now().toString())
                .path(request.getDescription(false).replace("uri=", ""))
                .correlationId(correlationId)
                .fieldErrors(fieldErrors)
                .build();

        MDC.clear();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(
            IllegalArgumentException ex, WebRequest request) {
        String correlationId = getOrGenerateCorrelationId(request);
        MDC.put("correlationId", correlationId);
        MDC.put("exception", "IllegalArgumentException");

        log.error("Argumento invalido - CorrelationId: {}, Message: {}",
                correlationId, ex.getMessage());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .error("BAD_REQUEST")
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(Instant.now().toString())
                .path(request.getDescription(false).replace("uri=", ""))
                .correlationId(correlationId)
                .build();

        MDC.clear();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(
            Exception ex, WebRequest request) {
        String correlationId = getOrGenerateCorrelationId(request);
        MDC.put("correlationId", correlationId);
        MDC.put("exception", ex.getClass().getSimpleName());

        log.error("Error interno del servidor - CorrelationId: {}, Type: {}, Message: {}",
                correlationId, ex.getClass().getSimpleName(), ex.getMessage(), ex);

        ErrorResponse errorResponse = ErrorResponse.builder()
                .error("INTERNAL_SERVER_ERROR")
                .message("Error interno del servidor. Por favor, contacte al administrador.")
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timestamp(Instant.now().toString())
                .path(request.getDescription(false).replace("uri=", ""))
                .correlationId(correlationId)
                .build();

        MDC.clear();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    private String getOrGenerateCorrelationId(WebRequest request) {
        String correlationId = request.getHeader(CORRELATION_ID_HEADER);
        return correlationId != null ? correlationId : UUID.randomUUID().toString();
    }

    public static class ErrorResponse {
        private String error;
        private String message;
        private int status;
        private String timestamp;
        private String path;
        private String correlationId;

        public static ErrorResponseBuilder builder() {
            return new ErrorResponseBuilder();
        }

        public String getError() { return error; }
        public void setError(String error) { this.error = error; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
        public int getStatus() { return status; }
        public void setStatus(int status) { this.status = status; }
        public String getTimestamp() { return timestamp; }
        public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
        public String getPath() { return path; }
        public void setPath(String path) { this.path = path; }
        public String getCorrelationId() { return correlationId; }
        public void setCorrelationId(String correlationId) { this.correlationId = correlationId; }

        public static class ErrorResponseBuilder {
            private String error;
            private String message;
            private int status;
            private String timestamp;
            private String path;
            private String correlationId;

            public ErrorResponseBuilder error(String error) { this.error = error; return this; }
            public ErrorResponseBuilder message(String message) { this.message = message; return this; }
            public ErrorResponseBuilder status(int status) { this.status = status; return this; }
            public ErrorResponseBuilder timestamp(String timestamp) { this.timestamp = timestamp; return this; }
            public ErrorResponseBuilder path(String path) { this.path = path; return this; }
            public ErrorResponseBuilder correlationId(String correlationId) { this.correlationId = correlationId; return this; }

            public ErrorResponse build() {
                ErrorResponse response = new ErrorResponse();
                response.error = this.error;
                response.message = this.message;
                response.status = this.status;
                response.timestamp = this.timestamp;
                response.path = this.path;
                response.correlationId = this.correlationId;
                return response;
            }
        }
    }

    public static class ValidationErrorResponse extends ErrorResponse {
        private Map<String, String> fieldErrors;

        public static ValidationErrorResponseBuilder builder() {
            return new ValidationErrorResponseBuilder();
        }

        public Map<String, String> getFieldErrors() { return fieldErrors; }
        public void setFieldErrors(Map<String, String> fieldErrors) { this.fieldErrors = fieldErrors; }

        public static class ValidationErrorResponseBuilder extends ErrorResponseBuilder {
            private Map<String, String> fieldErrors;

            public ValidationErrorResponseBuilder fieldErrors(Map<String, String> fieldErrors) {
                this.fieldErrors = fieldErrors;
                return this;
            }

            public ValidationErrorResponse build() {
                ValidationErrorResponse response = new ValidationErrorResponse();
                response.setError(error);
                response.setMessage(message);
                response.setStatus(status);
                response.setTimestamp(timestamp);
                response.setPath(path);
                response.setCorrelationId(correlationId);
                response.setFieldErrors(fieldErrors);
                return response;
            }
        }
    }
}