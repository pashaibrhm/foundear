CREATE TABLE IF NOT EXISTS foundear.mst_failed_login_attempts
(
  attempt_id  UUID NOT NULL
    CONSTRAINT "PK_MstFailedLoginAttempts"
      PRIMARY KEY,
  user_id     UUID
    CONSTRAINT "FK_MstUserLoginAttempts_UserId_MstUsers_UserId"
      REFERENCES foundear.mst_users,
  attempt_at  TIMESTAMP,
  log_message VARCHAR(255),
  ip_address  VARCHAR(255)
);

ALTER TABLE foundear.mst_failed_login_attempts
  OWNER TO postgres;

