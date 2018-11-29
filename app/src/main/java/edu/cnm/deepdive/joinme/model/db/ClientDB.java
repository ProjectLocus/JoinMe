package edu.cnm.deepdive.joinme.model.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomDatabase.Callback;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.support.annotation.NonNull;
import edu.cnm.deepdive.joinme.model.dao.InvitationDao;
import edu.cnm.deepdive.joinme.model.dao.PersonDao;
import edu.cnm.deepdive.joinme.model.db.ClientDB.Converters;
import edu.cnm.deepdive.joinme.model.entity.Invitation;
import edu.cnm.deepdive.joinme.model.entity.Person;
import java.util.UUID;


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

  public static synchronized ClientDB getInstance(Context context) {
    if (instance == null) {
      instance = Room.databaseBuilder(context,
          ClientDB.class, DB_NAME)
          .addCallback(new Callback(context.getApplicationContext()))
          .build();
    }
    return instance;
  }

  public static synchronized void forgetInstance(Context context) {instance = null;}

  public abstract PersonDao getPersonDao();

  public abstract InvitationDao getInvitationDao();

  private static class Callback extends RoomDatabase.Callback {
    private Context context;

    Callback(Context context) {
      this.context = context;
    }

    @Override
    public void onCreate(@NonNull SupportSQLiteDatabase db) {
      super.onCreate(db);
    }

    @Override
    public void onOpen(@NonNull SupportSQLiteDatabase db) {
      super.onOpen(db);
    }
  }

  public static class Converters {

    @TypeConverter
    public String stringFromUUID(UUID id) {
      return id.toString();
    }

    @TypeConverter
    public UUID uuidFromString(String str) {
      return UUID.fromString(str);
    }

  }

}
