-- BRAND
CREATE TABLE IF NOT EXISTS brand (
  id BIGSERIAL PRIMARY KEY,
  fipe_code VARCHAR(16) NOT NULL,
  name      VARCHAR(120) NOT NULL,
  type      VARCHAR(12)  NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT NOW(),
  updated_at TIMESTAMP NOT NULL DEFAULT NOW(),
  CONSTRAINT uk_brand_type_code UNIQUE (type, fipe_code)
);

CREATE INDEX IF NOT EXISTS idx_brand_type_code ON brand(type, fipe_code);
CREATE INDEX IF NOT EXISTS idx_brand_name       ON brand(name);

-- VEHICLE
CREATE TABLE IF NOT EXISTS vehicle (
  id BIGSERIAL PRIMARY KEY,
  brand_id BIGINT NOT NULL REFERENCES brand(id) ON DELETE CASCADE,
  fipe_model_code INTEGER NOT NULL,
  model_name      VARCHAR(180) NOT NULL,
  type            VARCHAR(12)  NOT NULL,
  observacoes     TEXT,
  created_at TIMESTAMP NOT NULL DEFAULT NOW(),
  updated_at TIMESTAMP NOT NULL DEFAULT NOW(),
  CONSTRAINT uk_vehicle_brand_modelcode UNIQUE (brand_id, fipe_model_code)
);

CREATE INDEX IF NOT EXISTS idx_vehicle_brand       ON vehicle(brand_id);
CREATE INDEX IF NOT EXISTS idx_vehicle_model_name  ON vehicle(model_name);
