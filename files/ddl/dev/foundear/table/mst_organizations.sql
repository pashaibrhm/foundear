CREATE TABLE IF NOT EXISTS foundear.mst_organizations
(
  org_id          UUID               NOT NULL
    CONSTRAINT "PK_MstOrganizations"
      PRIMARY KEY,
  name            VARCHAR(20)        NOT NULL,
  email           VARCHAR(50),
  created_by      UUID               NOT NULL,
  created_at      TIMESTAMP          NOT NULL,
  last_updated_by UUID               NOT NULL,
  last_updated_at TIMESTAMP          NOT NULL,
  approved_by     UUID,
  approved_at     TIMESTAMP,
  last_version_at TIMESTAMP          NOT NULL,
  is_deleted      SMALLINT DEFAULT 0 NOT NULL
);

ALTER TABLE foundear.mst_organizations
  OWNER TO postgres;

