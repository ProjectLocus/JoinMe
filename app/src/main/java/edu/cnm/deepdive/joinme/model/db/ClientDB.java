package edu.cnm.deepdive.joinme.model.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.databinding.adapters.Converters;
import edu.cnm.deepdive.joinme.model.entity.Invitation;
import edu.cnm.deepdive.joinme.model.entity.Person;


/**
 * Class that defines the {@link Database}.
 */
@Database(
    entities = {Invitation.class, Person.class},
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters.class)
public abstract class ClientDB extends RoomDatabase {

  private static final String DB_NAME = "client_db";

  private static ClientDB instance = null;

}
