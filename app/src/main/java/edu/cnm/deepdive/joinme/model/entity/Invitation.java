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
  private long invitationId;

  @NonNull
  @ColumnInfo(name = "user_sender")
  private long userSender;

  @NonNull
  @ColumnInfo(name = "user_receiver")
  private long userReceiver;

  @NonNull
  @ColumnInfo(name = "date")
  private String date;

  @NonNull
  @ColumnInfo(name = "description")
  private String description;

//  @NonNull
//  @ColumnInfo(name = "created")
//  private String created;

  @NonNull
  @ColumnInfo(name = "title")
  private String title;

  @NonNull
  @ColumnInfo(name = "location")
  private String location;

//  @NonNull
//  @ColumnInfo(name = "time")
//  private String time;


  @ColumnInfo(name = "wasDelivered")
  private boolean wasDelivered;


  @ColumnInfo(name = "willAttend")
  private boolean willAttend;


  @ColumnInfo(name = "degreesRemaining")
  private long degreesRemaining;

  /**
   * Gets id.
   *
   * @return the id
   */
  public long getInvitationId() {
    return invitationId;
  }

  /**
   * Sets id.
   *
   * @param invitationId the invitationId
   */
  public void setInvitationId(long invitationId) {
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

//  /**
//   * Gets created.
//   *
//   * @return the created
//   */
//  @NonNull
//  public String getCreated() {
//    return created;
//  }
//
//  /**
//   * Sets created.
//   *
//   * @param created the created
//   */
//  public void setCreated(@NonNull String created) {
//    this.created = created;
//  }

  /**
   * Gets the id of the person creating the invtation.
   * @return
   */
  @NonNull
  public long getUserSender() {
    return userSender;
  }

  /**
   * Sets the id of the person receiving the invitation.
   * @param userSender
   */
  public void setUserSender(@NonNull long userSender) {
    this.userSender = userSender;
  }

  /**
   * Gets the id of the person receiving the invitation
   * @return
   */
  @NonNull
  public long getUserReceiver() {
    return userReceiver;
  }

  /**
   * Sets the id of the person receiving the invitation.
   * @param userReceiver
   */
  public void setUserReceiver(long userReceiver) {
    this.userReceiver = userReceiver;
  }

  /**
   * Gets title.
   * @return
   */
  @NonNull
  public String getTitle() {
    return title;
  }

  /**
   * Sets title
   * @param title
   */
  public void setTitle(@NonNull String title) {
    this.title = title;
  }

  /**
   * Gets location.
   * @return
   */
  @NonNull
  public String getLocation() {
    return location;
  }

  /**
   * Sets location
   * @param location
   */
  public void setLocation(@NonNull String location) {
    this.location = location;
  }

//  @NonNull
//  public String getTime() {
//    return time;
//  }
//
//  public void setTime(@NonNull String time) {
//    this.time = time;
//  }


  public boolean isWasDelivered() {
    return wasDelivered;
  }

  public void setWasDelivered(boolean wasDelivered) {
    this.wasDelivered = wasDelivered;
  }

  public boolean isWillAttend() {
    return willAttend;
  }

  public void setWillAttend(boolean willAttend) {
    this.willAttend = willAttend;
  }

  public long getDegreesRemaining() {
    return degreesRemaining;
  }

  public void setDegreesRemaining(long degreesRemaining) {
    this.degreesRemaining = degreesRemaining;
  }
}
