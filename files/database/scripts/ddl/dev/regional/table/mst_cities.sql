CREATE TABLE IF NOT EXISTS regional.mst_cities
(
  citiy_id         UUID               NOT NULL
    CONSTRAINT "PK_MstCities"
      PRIMARY KEY,
  province_id      UUID
    CONSTRAINT "FK_MstCities_ProvinceId_MstProvinces_ProvinceId"
      REFERENCES regional.mst_provinces,
  city_code        VARCHAR(10)        NOT NULL,
  city_name        VARCHAR(50)        NOT NULL,
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

ALTER TABLE regional.mst_cities
  OWNER TO postgres;

