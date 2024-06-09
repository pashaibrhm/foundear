CREATE TABLE IF NOT EXISTS regional.mst_countries
(
  country_id       UUID               NOT NULL
    CONSTRAINT "PK_MstCountries"
      PRIMARY KEY,
  country_code     VARCHAR(10)        NOT NULL,
  country_name     VARCHAR(50)        NOT NULL,
  is_active        SMALLINT           NOT NULL,
  created_by       UUID               NOT NULL,
  created_at       TIMESTAMP          NOT NULL,
  last_updated_by  UUID               NOT NULL,
  last_updated_at  TIMESTAMP          NOT NULL,
  last_approved_by UUID,
  last_approved_at TIMESTAMP,
  last_version_at  TIMESTAMP          NOT NULL,
  is_deleted       SMALLINT DEFAULT 0 NOT NULL
);

ALTER TABLE regional.mst_countries
  OWNER TO postgres;

