CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE IF NOT EXISTS reagent (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    formula VARCHAR(255),
    cas_number VARCHAR(50),
    description TEXT,
    created_at DATE,
    updated_at DATE
    );

CREATE TABLE IF NOT EXISTS reagent_stock (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    reagent_id UUID NOT NULL,
    quantity DOUBLE PRECISION NOT NULL,
    unit VARCHAR(50),
    received_at DATE,
    active BOOLEAN DEFAULT TRUE,
    CONSTRAINT fk_reagent FOREIGN KEY (reagent_id) REFERENCES reagent(id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS reagent_consumption (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    reagent_id UUID NOT NULL,
    amount DOUBLE PRECISION NOT NULL,
    unit VARCHAR(50),
    experiment_id UUID,
    consumed_at TIMESTAMP,
    CONSTRAINT fk_reagent_consumption FOREIGN KEY (reagent_id) REFERENCES reagent(id) ON DELETE CASCADE
    );