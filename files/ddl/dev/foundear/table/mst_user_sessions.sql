CREATE TABLE IF NOT EXISTS foundear.mst_user_sessions
(
  session_id UUID         NOT NULL
    PRIMARY KEY,
  user_id    UUID         NOT NULL
    CONSTRAINT "FK_MstUserSessions_UserId_MstUsers_UserId"
      REFERENCES foundear.mst_users,
  start_time TIMESTAMP    NOT NULL,
  end_time   TIMESTAMP,
  user_agent VARCHAR(255) NOT NULL,
  ip_address VARCHAR(255) NOT NULL
);

COMMENT ON COLUMN foundear.mst_user_sessions.user_agent IS 'Browser or Device used by User, can be retrieved from the information sent by client-side';

ALTER TABLE foundear.mst_user_sessions
  OWNER TO postgres;

