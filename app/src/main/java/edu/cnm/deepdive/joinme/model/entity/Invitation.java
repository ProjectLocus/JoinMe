package edu.cnm.deepdive.joinme.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import java.util.Date;

@Entity(
    tableName = "invitation",
    indices = {@Index(value = {"invitation_id", "user_sender_id", "user_receiver_id"},
        unique = true)}
)
public class Invitation {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "invitation_id")
  private long id;

  @NonNull
  @ColumnInfo(name = "user_sender_id")
  private long userSenderId;

  @NonNull
  @ColumnInfo(name = "user_receiver_id")
  private long userReceiverId;

  @NonNull
  @ColumnInfo(name = "date")
  private String date;

  @NonNull
  @ColumnInfo(name = "description")
  private String description;

  @NonNull
  @ColumnInfo(name = "created")
  private Date created;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getUserSenderId() {
    return userSenderId;
  }

  public void setUserSenderId(long userSenderId) {
    this.userSenderId = userSenderId;
  }

  public long getUserReceiverId() {
    return userReceiverId;
  }

  public void setUserReceiverId(long userReceiverId) {
    this.userReceiverId = userReceiverId;
  }

  @NonNull
  public String getDate() {
    return date;
  }

  public void setDate(@NonNull String date) {
    this.date = date;
  }

  @NonNull
  public String getDescription() {
    return description;
  }

  public void setDescription(@NonNull String description) {
    this.description = description;
  }

  @NonNull
  public Date getCreated() {
    return created;
  }

  public void setCreated(@NonNull Date created) {
    this.created = created;
  }
}
