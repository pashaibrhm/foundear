CREATE TABLE IF NOT EXISTS foundear.mst_branches
(
  branch_id       UUID               NOT NULL
    CONSTRAINT "PK_MstBrances"
      PRIMARY KEY,
  branch_type     UUID               NOT NULL
    CONSTRAINT "FK_MstBranches_BranchType_MstLookupDetails_DetailId"
      REFERENCES foundear.mst_lookup_details,
  entity_id       UUID               NOT NULL,
  entity_type     UUID               NOT NULL
    CONSTRAINT "FK_MstBranches_EntityType_MstLookupDetails_DetailId"
      REFERENCES foundear.mst_lookup_details,
  branch_code     VARCHAR(20)        NOT NULL,
  branch_name     VARCHAR(30)        NOT NULL,
  branch_email    VARCHAR(50),
  branch_phone_no VARCHAR(20),
  address_detail  VARCHAR(255),
  village_id      UUID
    CONSTRAINT "FK_MstBranches_VillageId_MstVillages_VillageId"
      REFERENCES regional.mst_villages,
  district_id     UUID
    CONSTRAINT "FK_MstBranches_DistrictId_MstDistricts_DistrictId"
      REFERENCES regional.mst_districts,
  city_id         UUID
    CONSTRAINT "FK_MstBranches_CityId_MstCities_CityId"
      REFERENCES regional.mst_cities,
  province_id     UUID
    CONSTRAINT "FK_MstBranches_ProvinceId_MstProvinces_ProvinceId"
      REFERENCES regional.mst_provinces,
  country_id      UUID
    CONSTRAINT "FK_MstBranches_CountryId_MstCountries_CountryId"
      REFERENCES regional.mst_countries,
  created_by      UUID               NOT NULL,
  created_at      TIMESTAMP          NOT NULL,
  last_updated_by UUID               NOT NULL,
  last_updated_at TIMESTAMP          NOT NULL,
  approved_by     UUID,
  approved_at     TIMESTAMP,
  last_version_at TIMESTAMP          NOT NULL,
  is_main_branch  SMALLINT DEFAULT 0 NOT NULL,
  is_deleted      SMALLINT           NOT NULL
);

ALTER TABLE foundear.mst_branches
  OWNER TO postgres;

