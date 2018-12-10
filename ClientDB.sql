CREATE TABLE IF NOT EXISTS `${TABLE_NAME}` (`invitation_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `user_sender` INTEGER NOT NULL, `user_receiver` INTEGER NOT NULL, `date` TEXT NOT NULL, `description` TEXT NOT NULL, `title` TEXT NOT NULL, `location` TEXT NOT NULL, `wasDelivered` INTEGER NOT NULL, `willAttend` INTEGER NOT NULL, `degreesRemaining` INTEGER NOT NULL, FOREIGN KEY(`user_sender`) REFERENCES `people`(`person_id`) ON UPDATE NO ACTION ON DELETE NO ACTION ),
CREATE UNIQUE INDEX `index_invitations_invitation_id` ON `${TABLE_NAME}` (`invitation_id`)
CREATE UNIQUE INDEX `index_invitations_user_sender` ON `${TABLE_NAME}` (`user_sender`)
CREATE UNIQUE INDEX `index_invitations_user_receiver` ON `${TABLE_NAME}` (`user_receiver`)
CREATE TABLE IF NOT EXISTS `${TABLE_NAME}` (`latitude` REAL NOT NULL, `longitude` REAL NOT NULL, `person_id` INTEGER NOT NULL, `display_name` TEXT NOT NULL, `userImageLocation` TEXT, `google_user_id` TEXT, `user_description` TEXT, `isThisMe` INTEGER NOT NULL, `user_image` TEXT, PRIMARY KEY(`person_id`)),
CREATE UNIQUE INDEX `index_people_person_id` ON `${TABLE_NAME}` (`person_id`)
CREATE UNIQUE INDEX `index_people_display_name` ON `${TABLE_NAME}` (`display_name`)
CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT),
INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \b1e886c1d9c0fc24a0552f34a661d38d\)
