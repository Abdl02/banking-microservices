-- customers table
CREATE TABLE IF NOT EXISTS customers (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    legal_id VARCHAR(15) NOT NULL,  -- Remove UNIQUE constraint if necessary
    type VARCHAR(15) CHECK (type IN ('RETAIL', 'CORPORATE', 'INVESTMENT')),
    address VARCHAR(255) NOT NULL
    );