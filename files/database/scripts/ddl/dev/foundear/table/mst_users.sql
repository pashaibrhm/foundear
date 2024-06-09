CREATE TABLE IF NOT EXISTS foundear.mst_users
(
  user_id         UUID                 NOT NULL
    CONSTRAINT "PK_MstUsers"
      PRIMARY KEY,
  group_id        UUID
    CONSTRAINT "FK_MstUsers_GroupId_MstGroups_GroupId"
      REFERENCES foundear.mst_groups,
  first_name      VARCHAR(20)          NOT NULL,
  middle_name     VARCHAR(20),
  last_name       VARCHAR(20),
  username        VARCHAR(30)          NOT NULL,
  email           VARCHAR(50)          NOT NULL,
  password        VARCHAR(255)         NOT NULL,
  address_detail  VARCHAR(255),
  village_id      UUID
    CONSTRAINT "FK_MstUsers_VillageId_MstVillages_VillageId"
      REFERENCES regional.mst_villages,
  district_id     UUID
    CONSTRAINT "FK_MstUsers_DistrictId_MstDistricts_DistrictId"
      REFERENCES regional.mst_districts,
  city_id         UUID
    CONSTRAINT "FK_MstUsers_CityId_MstCities_CityId"
      REFERENCES regional.mst_cities,
  province_id     UUID
    CONSTRAINT "FK_MstUsers_ProvinceId_MstProvinces_ProvinceId"
      REFERENCES regional.mst_provinces,
  country_id      UUID
    CONSTRAINT "FK_MstUsers_CountryId_MstCountries_CountryId"
      REFERENCES regional.mst_countries,
  created_by      UUID                 NOT NULL,
  created_at      TIMESTAMP            NOT NULL,
  last_updated_by UUID                 NOT NULL,
  last_updated_at TIMESTAMP            NOT NULL,
  approved_by     UUID,
  approved_at     TIMESTAMP,
  last_version_at TIMESTAMP            NOT NULL,
  lock_count      NUMERIC(1) DEFAULT 0,
  is_locked       SMALLINT   DEFAULT 0,
  is_deleted      SMALLINT   DEFAULT 0 NOT NULL
);

ALTER TABLE foundear.mst_users
  OWNER TO postgres;

