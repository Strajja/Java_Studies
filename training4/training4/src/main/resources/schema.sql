DROP TABLE IF EXISTS insurance_products CASCADE;

CREATE TABLE insurance_products (
                                    id BIGINT PRIMARY KEY,
                                    name VARCHAR(255) NOT NULL,
                                    type VARCHAR(100) NOT NULL,
                                    description TEXT,
                                    CONSTRAINT uk_insurance_product_name UNIQUE (name)
);