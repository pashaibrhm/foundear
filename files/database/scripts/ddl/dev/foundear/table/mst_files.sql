CREATE TABLE IF NOT EXISTS foundear.mst_files
(
  file_id     UUID               NOT NULL
    CONSTRAINT "PK_MstFiles"
      PRIMARY KEY,
  file_name   VARCHAR(255)       NOT NULL,
  file_data   BYTEA              NOT NULL,
  created_by  UUID               NOT NULL,
  created_at  TIMESTAMP          NOT NULL,
  approved_by UUID,
  approved_at TIMESTAMP,
  is_deleted  SMALLINT DEFAULT 0 NOT NULL
);

ALTER TABLE foundear.mst_files
  OWNER TO postgres;

