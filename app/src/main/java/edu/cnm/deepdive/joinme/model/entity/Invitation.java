package edu.cnm.deepdive.joinme.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import java.util.UUID;

/**
 * The type Invitation.
 */
@Entity(
    tableName = "invitations",
    indices = {@Index(value = {"invitation_id", "user_sender", "user_receiver"},
        unique = true)},
    foreignKeys = {@ForeignKey(entity = Person.class, parentColumns = "person_id",
    childColumns = {"user_sender", "user_receiver"}
    )}
)
public class Invitation {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "invitation_id")
  private UUID invitationId;

  @NonNull
  @ColumnInfo(name = "user_sender")
  private Person userSender;

  @NonNull
  @ColumnInfo(name = "user_receiver")
  private Person userReceiver;

  @NonNull
  @ColumnInfo(name = "date")
  private String date;

  @NonNull
  @ColumnInfo(name = "description")
  private String description;

  @NonNull
  @ColumnInfo(name = "created")
  private String created;

  /**
   * Gets id.
   *
   * @return the id
   */
  public UUID getInvitationId() {
    return invitationId;
  }

  /**
   * Sets id.
   *
   * @param invitationId the invitationId
   */
  public void setInvitationId(UUID invitationId) {
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
  public Person getUserSender() {
    return userSender;
  }

  public void setUserSender(@NonNull Person userSender) {
    this.userSender = userSender;
  }

  @NonNull
  public Person getUserReceiver() {
    return userReceiver;
  }

  public void setUserReceiver(@NonNull Person userReceiver) {
    this.userReceiver = userReceiver;
  }
}
