package edu.cnm.deepdive.joinme.model.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;
import edu.cnm.deepdive.joinme.model.dao.InvitationDao;
import edu.cnm.deepdive.joinme.model.dao.PersonDao;
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
public abstract class ClientDB extends RoomDatabase {

  private static final String DB_NAME = "client_db";

  private static ClientDB instance = null;

  /**
   * This method allows the rest of project to access the database for inserting, querying, updating
   * and deleting.
   * @param context
   * @return
   */
  public static synchronized ClientDB getInstance(Context context) {
    if (instance == null) {
      instance = Room.databaseBuilder(context,
          ClientDB.class, DB_NAME)
          .addCallback(new Callback(context.getApplicationContext()))
          .build();
    }
    return instance;
  }

  /**
   * Allows rest of project to forget the instance of he Client database.
   * @param context
   */
  public static synchronized void forgetInstance(Context context) {instance = null;}

  /**
   * Allows access into the Person Dao
   * @return
   */
  public abstract PersonDao getPersonDao();

  /**
   * Allows access into the Invitation Dao
   * @return
   */
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



}
