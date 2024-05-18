CREATE TABLE IF NOT EXISTS regional.mst_villages
(
  village_id       UUID               NOT NULL
    CONSTRAINT "PK_MstVillages"
      PRIMARY KEY,
  district_id      UUID
    CONSTRAINT "FK_MstVillages_DistrictId_MstDistricts_DistrictId"
      REFERENCES regional.mst_districts,
  village_code     VARCHAR(10)        NOT NULL,
  village_name     VARCHAR(50)        NOT NULL,
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

ALTER TABLE regional.mst_villages
  OWNER TO postgres;

