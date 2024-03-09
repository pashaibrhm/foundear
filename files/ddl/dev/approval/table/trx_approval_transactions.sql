CREATE TABLE IF NOT EXISTS approval.trx_approval_transactions
(
  trx_approval_id   UUID         NOT NULL
    CONSTRAINT "PK_TrxApprovalTransactions"
      PRIMARY KEY,
  approval_setup_id UUID         NOT NULL
    CONSTRAINT "FK_TrxApprovalTransactions_ApprovalSetupId_MstApprovalSetups_Ap"
      REFERENCES approval.mst_approval_setups,
  function_id       UUID         NOT NULL
    CONSTRAINT "FK_TrxApprovalTransactions_FunctionId_MstFunctions_FunctionId"
      REFERENCES foundear.mst_functions,
  trx_status        VARCHAR(10)  NOT NULL,
  serialized_data   VARCHAR(255) NOT NULL,
  created_by        UUID         NOT NULL,
  created_at        TIMESTAMP    NOT NULL,
  last_updated_by   UUID         NOT NULL,
  last_updated_at   TIMESTAMP    NOT NULL,
  last_approved_by  UUID,
  last_approved_at  TIMESTAMP,
  last_version_at   TIMESTAMP    NOT NULL
);

ALTER TABLE approval.trx_approval_transactions
  OWNER TO postgres;

