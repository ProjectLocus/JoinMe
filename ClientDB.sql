CREATE TABLE IF NOT EXISTS `invitations` (
  `invitation_id`       INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  `user_sender_id`      INTEGER NOT NULL,
  `user_receiver_id`    INTEGER NOT NULL,
  `date`                TEXT NOT NULL,
  `description`         TEXT NOT NULL,
  `title`               TEXT NOT NULL,
  `location`            TEXT NOT NULL,
  `wasDelivered`        INTEGER NOT NULL,
  `willAttend`          INTEGER NOT NULL,
  `degreesRemaining`    INTEGER NOT NULL
);
CREATE UNIQUE INDEX `index_invitations_invitation_id`
                  ON `invitations` (`invitation_id`);
CREATE UNIQUE INDEX `index_invitations_user_sender_id`
                  ON `invitations` (`user_sender_id`)
CREATE UNIQUE INDEX `index_invitations_user_receiver_id`
                  ON `invitations` (`user_receiver_id`)

CREATE TABLE IF NOT EXISTS `people` (
  `latitude`            REAL NOT NULL,
  `longitude`           REAL NOT NULL,
  `person_id`           INTEGER NOT NULL,
  `display_name`        TEXT NOT NULL,
  `userImageLocation`   TEXT,
  `google_user_id`      TEXT,
  `user_description`    TEXT,
  `isThisMe`            INTEGER NOT NULL,
  `user_image`          TEXT,
                        PRIMARY KEY(`person_id`)
);
CREATE UNIQUE INDEX `index_people_person_id`
                  ON `people` (`person_id`)
CREATE UNIQUE INDEX `index_people_display_name`
                  ON `people` (`display_name`);

CREATE TABLE IF NOT EXISTS room_master_table (
  id                    INTEGER PRIMARY KEY,
  identity_hash         TEXT),
                        INSERT OR REPLACE INTO room_master_table (id,identity_hash)
                        VALUES(42, "0d6bb583c9f9c38d53244ca3f75107a9")