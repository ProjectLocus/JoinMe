package edu.cnm.deepdive.joinme.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import edu.cnm.deepdive.joinme.model.entity.Invitation;
import edu.cnm.deepdive.joinme.model.entity.Person;
import java.util.List;
import java.util.UUID;

@Dao
public interface InvitationDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  long insert(Invitation invitation);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  List<Long> insert(Invitation... invitations);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  List<Long> insert(List<Invitation> invitations);

  @Query("SELECT * FROM invitations ORDER BY invitation_id ASC")
  List<Invitation> selectAll();

  @Query("SELECT * FROM invitations WHERE invitation_id=:invitationId")
  List<Invitation> selectAllInvitationId(UUID invitationId);

  @Query("SELECT * FROM invitations WHERE date=:date")
  List<Invitation> selectAllDate(String date);

  @Query("SELECT * FROM invitations WHERE created=:created")
  List<Invitation> selectAllCreated(String created);

  @Query("SELECT * FROM invitations WHERE description=:description")
  List<Invitation> selectAllDescription(String description);

  @Query("SELECT * FROM invitations WHERE user_sender=:userSender")
  List<Invitation> getInvitatiionsForUserSender(UUID userSender);

  @Query("SELECT * FROM invitations WHERE user_receiver=:userReceiver")
  List<Invitation> getInvitationsForUserReceiver(UUID userReceiver);

  @Query("SELECT * FROM invitations WHERE title=:title")
  List<Invitation> getAllTitle(String title);

  @Query("SELECT * FROM invitations WHERE location=:location")
  List<Invitation> getAllLocation(String location);

  @Update(onConflict = OnConflictStrategy.REPLACE)
  int update(Invitation invitations);

  @Update(onConflict = OnConflictStrategy.REPLACE)
  int update(Invitation... invitations);

  @Update(onConflict = OnConflictStrategy.REPLACE)
  int update(List<Invitation> invitations);

  @Delete
  int delete(Invitation invitation);

  @Delete
  int delete(Invitation... invitations);

  @Delete
  int delete(List<Invitation> invitations);


}
