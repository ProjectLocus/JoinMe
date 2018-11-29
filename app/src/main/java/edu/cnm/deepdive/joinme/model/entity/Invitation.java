package edu.cnm.deepdive.joinme.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import java.util.Date;
import java.util.UUID;

/**
 * The type Invitation.
 */
@Entity(
    tableName = "invitations",
    indices = {@Index(value = {"invitation_id", "user_sender", "user_receiver"},
        unique = true)}
)
public class Invitation {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "invitation_id")
  private UUID id;

  @NonNull
  @ColumnInfo(name = "user_sender")
  private UUID userSender;

  @NonNull
  @ColumnInfo(name = "user_receiver")
  private UUID userReceiver;

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
  public UUID getId() {
    return id;
  }

  /**
   * Sets id.
   *
   * @param id the id
   */
  public void setId(UUID id) {
    this.id = id;
  }

  /**
   * Gets user sender.
   *
   * @return the user sender
   */
  @NonNull
  public UUID getUserSender() {
    return userSender;
  }

  /**
   * Sets user sender.
   *
   * @param userSender the user sender
   */
  public void setUserSender(@NonNull UUID userSender) {
    this.userSender = userSender;
  }

  /**
   * Gets user receiver.
   *
   * @return the user receiver
   */
  @NonNull
  public UUID getUserReceiver() {
    return userReceiver;
  }

  /**
   * Sets user receiver.
   *
   * @param userReceiver the user receiver
   */
  public void setUserReceiver(@NonNull UUID userReceiver) {
    this.userReceiver = userReceiver;
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
}
