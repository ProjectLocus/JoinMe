package edu.cnm.deepdive.joinme.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import edu.cnm.deepdive.joinme.model.entity.Invitation;
import java.util.Date;
import java.util.List;

@Dao
public interface InvitationDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  long insert(Invitation invitation);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  List<Long> insert(Invitation... invitations);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  List<Long> insert(List<Invitation> invitations);

  @Query("SELECT * FROM invitation ORDER BY invitation_id ASC")
  List<Invitation> selectAll();

  @Query("SELECT * FROM invitation WHERE invitation_id=:invitationId")
  Invitation selectAllInvitationId(long invitationId);

  @Query("SELECT * FROM invitation WHERE user_sender_id=:userSenderId")
  Invitation selectAllUserSenderId(long userSenderId);

  @Query("SELECT * FROM invitation WHERE user_receiver_id=:userReceiverId")
  Invitation selectAllReceiverSenderId(long userReceiverId);

  @Query("SELECT * FROM invitation WHERE date=:date")
  Invitation selectAllDate(String date);

  @Query("SELECT * FROM invitation WHERE created=:created")
  Invitation selectAllCreated(Date created);

  @Query("SELECT * FROM invitation WHERE description=:description")
  Invitation selectAllDescription(String description);

  @Update(onConflict = OnConflictStrategy.REPLACE)
  int update(Invitation invitation);

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
