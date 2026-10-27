-- Script de migracion Flyway para crear la tabla de productos
-- Version: V1
-- Descripcion: Crea la tabla products con todas las columnas necesarias para el dominio

CREATE TABLE products (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    category VARCHAR(100) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Indice para busqueda por categoria
CREATE INDEX idx_products_category ON products(category);

-- Indice para busqueda por estado activo
CREATE INDEX idx_products_active ON products(active);

-- Indice compuesto para consultas frecuentes: categoria + estado
CREATE INDEX idx_products_category_active ON products(category, active);

-- Comentario para documentacion
COMMENT ON TABLE products IS 'Tabla principal para gestion de productos del sistema';
COMMENT ON COLUMN products.id IS 'Identificador unico del producto (UUID)';
COMMENT ON COLUMN products.name IS 'Nombre del producto';
COMMENT ON COLUMN products.description IS 'Descripcion detallada del producto';
COMMENT ON COLUMN products.price IS 'Precio del producto con dos decimales de precision';
COMMENT ON COLUMN products.category IS 'Categoria a la que pertenece el producto';
COMMENT ON COLUMN products.active IS 'Indica si el producto esta activo o inactivo para su uso';
COMMENT ON COLUMN products.created_at IS 'Fecha y hora de creacion del registro';
COMMENT ON COLUMN products.updated_at IS 'Fecha y hora de la ultima modificacion del registro';

--插入一些 datos iniciales para pruebas
INSERT INTO products (id, name, description, price, category, active, created_at, updated_at) VALUES
    ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Laptop Pro 15', 'Laptop de alta gama con procesador Intel i7', 1299.99, 'Electronics', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('b1ffcd99-9d1c-5ef9-cb7c-7cc0ce491b22', 'Wireless Mouse', 'Mouse inalambrico ergonomico', 49.99, 'Electronics', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('c2ggde99-9e2d-6fg0-dc8d-8dd1df502c33', 'Organic Coffee', 'Cafe organico premium de Colombia', 24.99, 'Food', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);