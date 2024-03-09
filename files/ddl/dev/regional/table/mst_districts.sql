CREATE TABLE IF NOT EXISTS regional.mst_districts
(
  district_id      UUID               NOT NULL
    CONSTRAINT "PK_MstDistricts"
      PRIMARY KEY,
  citiy_id         UUID
    CONSTRAINT "FK_MstDistricts_CityId_MstCities_CityId"
      REFERENCES regional.mst_cities,
  district_code    VARCHAR(10)        NOT NULL,
  district_name    VARCHAR(50)        NOT NULL,
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

ALTER TABLE regional.mst_districts
  OWNER TO postgres;

