CREATE TABLE IF NOT EXISTS foundear.mst_entity_files
(
  entity_file_id     UUID               NOT NULL
    CONSTRAINT "PK_MstEntityFiles"
      PRIMARY KEY,
  file_id            UUID               NOT NULL
    CONSTRAINT "FK_MstEntityFiles_FileId_MstFiles_FileId"
      REFERENCES foundear.mst_files,
  entity_id          UUID               NOT NULL,
  lookup_entity_type UUID               NOT NULL
    CONSTRAINT "FK_MstEntityFiles_LookupEntityType_MstLookupDetails_LookupDetai"
      REFERENCES foundear.mst_lookup_details,
  created_by         UUID               NOT NULL,
  created_at         TIMESTAMP          NOT NULL,
  approved_by        UUID,
  approved_at        TIMESTAMP,
  is_deleted         SMALLINT DEFAULT 0 NOT NULL
);

ALTER TABLE foundear.mst_entity_files
  OWNER TO postgres;

