CREATE TABLE IF NOT EXISTS foundear.mst_group_function_permissions
(
  permission_id UUID NOT NULL
    CONSTRAINT "PK_MstGroupFunctionPermissions"
      PRIMARY KEY,
  function_id   UUID NOT NULL
    CONSTRAINT "FK_MstGroupFunctionMapping_FunctionId_MstFunctions_FunctionId"
      REFERENCES foundear.mst_functions,
  group_id      UUID NOT NULL
    CONSTRAINT "FK_MstGroupFunctionMapping_GroupId_MstGroup_GroupId"
      REFERENCES foundear.mst_groups
);

ALTER TABLE foundear.mst_group_function_permissions
  OWNER TO postgres;

