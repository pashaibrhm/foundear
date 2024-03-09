CREATE TABLE IF NOT EXISTS approval.mst_approval_functions
(
  approval_function_id UUID               NOT NULL
    CONSTRAINT "PK_MstApprovalFunctions"
      PRIMARY KEY,
  approval_setup_id    UUID               NOT NULL
    CONSTRAINT "FK_MstApprovalFunctions_ApprovalSetupId_MstApprovalSetups_Appro"
      REFERENCES approval.mst_approval_setups,
  function_id          UUID               NOT NULL
    CONSTRAINT "FK_MstApprovalFunctions_FunctionId_MstFunctions_FunctionId"
      REFERENCES foundear.mst_functions,
  created_by           UUID               NOT NULL,
  created_at           TIMESTAMP          NOT NULL,
  last_updated_by      UUID               NOT NULL,
  last_updated_at      TIMESTAMP          NOT NULL,
  last_approved_by     UUID,
  last_approved_at     TIMESTAMP,
  last_version_at      TIMESTAMP          NOT NULL,
  is_deleted           SMALLINT DEFAULT 0 NOT NULL
);

ALTER TABLE approval.mst_approval_functions
  OWNER TO postgres;

