package edu.cnm.deepdive.joinme.model.entity;

import android.arch.persistence.room.ColumnInfo;
 import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * The type Invitation.
 */
@Entity(
    tableName = "invitations",
    indices = {@Index(value = "invitation_id", unique = true),
        @Index(value = "user_sender", unique = true),
        @Index(value = "user_receiver", unique = true)},
    foreignKeys = {
        @ForeignKey(entity = Person.class, parentColumns = "person_id",
            childColumns = "user_sender"),
        @ForeignKey(entity = Person.class, parentColumns = "person_id",
            childColumns = "user_receiver")}
)
public class Invitation {

  @NonNull
  @PrimaryKey
  @ColumnInfo(name = "invitation_id")
  private int invitationId;

  @NonNull
  @ColumnInfo(name = "user_sender")
  private int userSender;

  @NonNull
  @ColumnInfo(name = "user_receiver")
  private int userReceiver;

  @NonNull
  @ColumnInfo(name = "date")
  private String date;

  @NonNull
  @ColumnInfo(name = "description")
  private String description;

  @NonNull
  @ColumnInfo(name = "created")
  private String created;

  @NonNull
  @ColumnInfo(name = "title")
  private String title;

  @NonNull
  @ColumnInfo(name = "location")
  private String location;

  /**
   * Gets id.
   *
   * @return the id
   */
  public int getInvitationId() {
    return invitationId;
  }

  /**
   * Sets id.
   *
   * @param invitationId the invitationId
   */
  public void setInvitationId(int invitationId) {
    this.invitationId = invitationId;
  }

  /**
   * Gets date.
   *
   * @return the date
   */
  @NonNull
  public String getDate() {
    return date;
  }

  /**
   * Sets date.
   *
   * @param date the date
   */
  public void setDate(@NonNull String date) {
    this.date = date;
  }

  /**
   * Gets description.
   *
   * @return the description
   */
  @NonNull
  public String getDescription() {
    return description;
  }

  /**
   * Sets description.
   *
   * @param description the description
   */
  public void setDescription(@NonNull String description) {
    this.description = description;
  }

  /**
   * Gets created.
   *
   * @return the created
   */
  @NonNull
  public String getCreated() {
    return created;
  }

  /**
   * Sets created.
   *
   * @param created the created
   */
  public void setCreated(@NonNull String created) {
    this.created = created;
  }

  @NonNull
  public int getUserSender() {
    return userSender;
  }

  public void setUserSender(@NonNull int userSender) {
    this.userSender = userSender;
  }

  @NonNull
  public int getUserReceiver() {
    return userReceiver;
  }

  public void setUserReceiver(@NonNull int userReceiver) {
    this.userReceiver = userReceiver;
  }

  @NonNull
  public String getTitle() {
    return title;
  }

  public void setTitle(@NonNull String title) {
    this.title = title;
  }

  @NonNull
  public String getLocation() {
    return location;
  }

  public void setLocation(@NonNull String location) {
    this.location = location;
  }
}
