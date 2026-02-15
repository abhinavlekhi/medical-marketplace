-- Enable UUID generation
CREATE EXTENSION IF NOT EXISTS "pgcrypto";

-- =========================
-- USERS
-- =========================
CREATE TABLE users (
    id UUID PRIMARY KEY,
    user_name VARCHAR(255) NOT NULL UNIQUE,
    password_hash TEXT NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

-- =========================
-- ITEMS (Product-level info)
-- =========================
CREATE TABLE items (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    default_unit VARCHAR(50),
    created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

-- =========================
-- BATCHES (Inventory)
-- =========================
CREATE TABLE batches (
    id UUID PRIMARY KEY,
    item_id UUID NOT NULL REFERENCES items(id) ON DELETE CASCADE,
    batch_number VARCHAR(100) NOT NULL UNIQUE,
    unit_price BIGINT NOT NULL CHECK (unit_price >= 0),
    quantity INTEGER NOT NULL CHECK (quantity >= 0),
    reserved_quantity INTEGER NOT NULL DEFAULT 0 CHECK (reserved_quantity >= 0),
    expiry_date DATE,
    manufacturer VARCHAR(255),
    created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

-- =========================
-- ORDERS
-- =========================
CREATE TABLE orders (
    order_id UUID PRIMARY KEY,
    user_id UUID NOT NULL REFERENCES users(id),
    status VARCHAR(30) NOT NULL,
    order_amount BIGINT NOT NULL CHECK (order_amount >= 0),
    expires_at TIMESTAMPTZ NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

-- =========================
-- ORDER ITEMS
-- =========================
CREATE TABLE order_items (
    id UUID PRIMARY KEY,
    order_id UUID NOT NULL REFERENCES orders(order_id) ON DELETE CASCADE,
    batch_id UUID NOT NULL REFERENCES batches(id),
    quantity INTEGER NOT NULL CHECK (quantity > 0),
    unit_price BIGINT NOT NULL CHECK (unit_price >= 0)
);

-- =========================
-- INDEXES
-- =========================
CREATE INDEX idx_items_name ON items(name);
CREATE INDEX idx_batches_item_id ON batches(item_id);
CREATE INDEX idx_orders_status ON orders(status);
CREATE INDEX idx_orders_expires_at ON orders(expires_at);
