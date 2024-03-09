CREATE TABLE IF NOT EXISTS foundear.mst_functions
(
  function_id      UUID               NOT NULL
    CONSTRAINT "PK_MstFunctions"
      PRIMARY KEY,
  parent_id        UUID               NOT NULL,
  menu_id          UUID
    CONSTRAINT "FK_MstFunctions_MenuId_MstMenus_MenuId"
      REFERENCES foundear.mst_menus,
  function_name    VARCHAR(50)        NOT NULL,
  created_by       UUID               NOT NULL,
  created_at       TIMESTAMP          NOT NULL,
  last_updated_by  UUID               NOT NULL,
  last_updated_at  TIMESTAMP          NOT NULL,
  last_approved_by UUID,
  last_approved_at TIMESTAMP,
  last_version_at  TIMESTAMP          NOT NULL,
  is_active        SMALLINT DEFAULT 1 NOT NULL,
  is_deleted       SMALLINT DEFAULT 0 NOT NULL
);

ALTER TABLE foundear.mst_functions
  OWNER TO postgres;

