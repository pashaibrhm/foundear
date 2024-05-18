CREATE TABLE IF NOT EXISTS foundear.mst_lookup_headers
(
  header_id        UUID NOT NULL
    CONSTRAINT "PK_MstLookupHeaders"
      PRIMARY KEY,
  name             VARCHAR(30),
  value            VARCHAR(20),
  description      VARCHAR(100),
  created_at       TIMESTAMP,
  created_by       UUID,
  last_updated_at  TIMESTAMP,
  last_updated_by  UUID,
  last_approved_at TIMESTAMP,
  last_approved_by UUID,
  last_version_at  TIMESTAMP,
  is_deleted       SMALLINT DEFAULT 0
);

ALTER TABLE foundear.mst_lookup_headers
  OWNER TO postgres;

