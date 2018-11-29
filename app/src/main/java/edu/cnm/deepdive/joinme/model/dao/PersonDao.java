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

@Dao
public interface PersonDao {

  @Insert (onConflict = OnConflictStrategy.REPLACE)
  long insert(Person person);

  @Insert (onConflict = OnConflictStrategy.REPLACE)
  void insert(List<Person> persons);

  @Query("SELECT * FROM person WHERE isThisMe=:isThisMe")
  List<Person> select(boolean isThisMe);

  @Query("SELECT * FROM person WHERE person_id=:personId")
  List<Person> select(UUID personId);

  @Query("SELECT * FROM person WHERE display_name=:displayName")
  List<Person> select(String displayName);

  @Query("SELECT * FROM person")
  List<Person> selectAll();

  @Update(onConflict = OnConflictStrategy.REPLACE)
  int update(Person person);

  @Update(onConflict = OnConflictStrategy.REPLACE)
  int update(Person... persons);

  @Update(onConflict = OnConflictStrategy.REPLACE)
  int update(List<Person> persons);

  @Delete
  int delete(UUID personId);

  @Query("DELETE FROM person")
  int nuke();
}