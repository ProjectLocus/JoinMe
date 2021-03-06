package edu.cnm.deepdive.joinme.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import edu.cnm.deepdive.joinme.model.entity.Person;
import java.util.List;
import java.util.UUID;

/**
 * The Person Dao which defines how the project inserts, queries, updates and deletes a Person object.
 */
@Dao
public interface PersonDao {

  @Insert (onConflict = OnConflictStrategy.REPLACE)
  long insert(Person person);

  @Insert (onConflict = OnConflictStrategy.REPLACE)
  void insert(List<Person> persons);

  @Query("SELECT * FROM people WHERE isThisMe=:isThisMe")
  List<Person> select(boolean isThisMe);

  @Query("SELECT * FROM people WHERE person_id=:personId")
  List<Person> select(long personId);

  @Query("SELECT * FROM people WHERE person_id=:personId")
  Person selectPerson(long personId);

  @Query("SELECT * FROM people WHERE display_name=:displayName")
  Person select(String displayName);

  @Query("SELECT * FROM people")
  List<Person> selectAll();

  @Query("SELECT * FROM people WHERE latitude=:latitude")
  Person selectLatitude(double latitude);

  @Query("SELECT * FROM people WHERE google_user_id=:googleUserId")
  Person selectGoogleUserId(String googleUserId);


  @Update(onConflict = OnConflictStrategy.REPLACE)
  int update(Person person);

  @Update(onConflict = OnConflictStrategy.REPLACE)
  int update(Person... persons);

  @Update(onConflict = OnConflictStrategy.REPLACE)
  int update(List<Person> persons);

  @Delete
  int delete(Person personId);

  @Delete
  int deleteList(List<Person> persons);

  @Query("DELETE FROM people")
  int nuke();
}
