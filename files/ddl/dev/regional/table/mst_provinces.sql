CREATE TABLE IF NOT EXISTS regional.mst_provinces
(
  province_id      UUID               NOT NULL
    CONSTRAINT "PK_MstProvinces"
      PRIMARY KEY,
  country_id       UUID
    CONSTRAINT "FK_MstProvinces_CountryId_MstCountries_CountryId"
      REFERENCES regional.mst_countries,
  province_code    VARCHAR(10)        NOT NULL,
  province_name    VARCHAR(50)        NOT NULL,
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

ALTER TABLE regional.mst_provinces
  OWNER TO postgres;

