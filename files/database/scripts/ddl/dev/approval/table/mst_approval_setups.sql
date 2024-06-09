CREATE TABLE IF NOT EXISTS approval.mst_approval_setups
(
  approval_setup_id UUID               NOT NULL
    CONSTRAINT "PK_MstApprovalSetups"
      PRIMARY KEY,
  setup_name        VARCHAR(30)        NOT NULL,
  created_by        UUID               NOT NULL,
  created_at        TIMESTAMP          NOT NULL,
  last_updated_by   UUID               NOT NULL,
  last_updated_at   TIMESTAMP          NOT NULL,
  last_approved_by  UUID,
  last_approved_at  TIMESTAMP,
  last_version_at   TIMESTAMP          NOT NULL,
  is_deleted        SMALLINT DEFAULT 0 NOT NULL
);

ALTER TABLE approval.mst_approval_setups
  OWNER TO postgres;

