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
        @Index(value = "user_sender_id", unique = true),
        @Index(value = "user_receiver_id", unique = true)}
//    foreignKeys = {
//        @ForeignKey(entity = Person.class, parentColumns = "person_id",
//            childColumns = "user_sender")}
)
public class Invitation {

  /**
   * This is the invitation's unique id
   */
  @NonNull
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "invitation_id")
  private long invitationId;

  /**
   * This is the sender's id.
   */
  @NonNull
  @ColumnInfo(name = "user_sender_id")
  private long userSenderId;

  /**
   * This is the receiver's id.
   */
  @NonNull
  @ColumnInfo(name = "user_receiver_id")
  private long userReceiverId;

  /**
   * This is the invitations inputed date.
   */
  @NonNull
  @ColumnInfo(name = "date")
  private String date;

  /**
   * This is the invitation inputed description.
   */
  @NonNull
  @ColumnInfo(name = "description")
  private String description;

//  @NonNull
//  @ColumnInfo(name = "created")
//  private String created;

  /**
   * This is the invitations inputed title.
   */
  @NonNull
  @ColumnInfo(name = "title")
  private String title;

  /**
   * This is the invitations inputed location.
   */
  @NonNull
  @ColumnInfo(name = "location")
  private String location;

  /**
   * This is the invitation's inputed time.
   */
  @NonNull
  @ColumnInfo(name = "time")
  private String time;

  /**
   * Tells true or false if the invitation was delivered.
   */
  @ColumnInfo(name = "wasDelivered")
  private boolean wasDelivered;

  /**
   * Tells if the receiver accepted the invitation.
   */
  @ColumnInfo(name = "willAttend")
  private boolean willAttend;


  /**
   * A number of times an invitation can be re-shared.
   */
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
  public long getUserSenderId() {
    return userSenderId;
  }

  /**
   * Sets the id of the person receiving the invitation.
   * @param userSenderId
   */
  public void setUserSenderId(@NonNull long userSenderId) {
    this.userSenderId = userSenderId;
  }

  /**
   * Gets the id of the person receiving the invitation
   * @return
   */
  @NonNull
  public long getUserReceiverId() {
    return userReceiverId;
  }

  /**
   * Sets the id of the person receiving the invitation.
   * @param userReceiverId
   */
  public void setUserReceiverId(long userReceiverId) {
    this.userReceiverId = userReceiverId;
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


  /**
   * If was delivered.
   * @return
   */
  public boolean isWasDelivered() {
    return wasDelivered;
  }

  /**
   * Sets if was delivered.
   * @param wasDelivered
   */
  public void setWasDelivered(boolean wasDelivered) {
    this.wasDelivered = wasDelivered;
  }

  /**
   * If will attend.
   * @return
   */
  public boolean isWillAttend() {
    return willAttend;
  }

  /**
   * Sets if will attend.
   * @param willAttend
   */
  public void setWillAttend(boolean willAttend) {
    this.willAttend = willAttend;
  }

  /**
   * Gets the degrees remaining.
   * @return
   */
  public long getDegreesRemaining() {
    return degreesRemaining;
  }

  /**
   * Sets how many times an invitation can be reshared.
   * @param degreesRemaining
   */
  public void setDegreesRemaining(long degreesRemaining) {
    this.degreesRemaining = degreesRemaining;
  }
}
