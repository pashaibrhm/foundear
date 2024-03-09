CREATE TABLE IF NOT EXISTS foundear.mst_groups
(
  group_id        UUID               NOT NULL
    CONSTRAINT "PK_MstGroups"
      PRIMARY KEY,
  parent_id       UUID,
  org_id          UUID               NOT NULL
    CONSTRAINT "FK_MstGroups_OrgId_MstOrganizations_OrgId"
      REFERENCES foundear.mst_organizations,
  name            VARCHAR(30)        NOT NULL,
  created_by      UUID               NOT NULL,
  created_at      TIMESTAMP          NOT NULL,
  last_updated_by UUID               NOT NULL,
  last_updated_at TIMESTAMP          NOT NULL,
  approved_by     UUID,
  approved_at     TIMESTAMP,
  last_version_at TIMESTAMP          NOT NULL,
  is_deleted      SMALLINT DEFAULT 0 NOT NULL
);

ALTER TABLE foundear.mst_groups
  OWNER TO postgres;

