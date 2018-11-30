package edu.cnm.deepdive.joinme.model.entity;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * The type Person.
 */
@Entity(
    tableName = "people",
    indices = {
        @Index(value= "person_id", unique = true),
        @Index(value = "display_name", unique = true)}
)
public class Person {

  //**************** INFO ON OTHERS THAT COMES FROM SERVER ***************************************//

  /**
   * This is a person's last known latitude
   */
  private double latitude;

  /**
   * This is a person's last known longitude
   */
  private double longitude;

  /**
   * This is a person's unique ID
   */
  @NonNull
  @PrimaryKey
  @ColumnInfo(name = "person_id")
  private UUID personId;

  /**
   * This is the display name a person has chosen
   */
  @NonNull
  @ColumnInfo(name = "display_name")
  private String displayName;

  /**
   * This is a string representation of the get function for the user's image
   */
  private String userImageLocation;

  /**
   * This is the user's description
   */
  private String userDescription;

  //**************** INFO THAT IS NOT DISTRIBUTED TO OTHER USERS ********************************//

  /**
   * This is a flag to keep track of which Person object refers to the user of the device
   */
  private boolean isThisMe;

  /**
   * This is the user's first name
   */
  @Ignore
  private String firstName;

  /**
   * This is the user's last name
   */
  @Ignore
  private String lastName;

  /**
   * This is the user's birth date
   */
  @Ignore
  private Date userBDay;

  /**
   * This is the user's email
   */
  @Ignore
  private String userEmail;

  /**
   * This is the user's password
   */
  @Ignore
  private String userPassword;

//  /**
//   * This is the list of the invitations the user has sent to others
//   */
//  private List<Invitation> invitesSent;
//
//  /**
//   * This is the list of the invitations the user has received from others
//   */
//  private List<Invitation> invitesReceived;

//  /**
//   * This is the list of invitations the user has created
//   */
//  private List<Invitation> invitesCreated;

  //**************** INFO THAT IS CREATED USING DATA FROM SERVER ********************************//

  /**
   * This is the distance between the user of the device and the location of the person associated
   * with this particular Person object
   */
  @Ignore
  private double distanceToUser;

  /**
   * This is the Person's user image
   */
  private int userImage;


  //***************** GETTERS AND SETTERS *******************************************************//


  /**
   * Gets latitude.
   *
   * @return the latitude
   */
  public double getLatitude() {
    return latitude;
  }

  /**
   * Sets latitude.
   *
   * @param latitude the latitude
   */
  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  /**
   * Gets longitude.
   *
   * @return the longitude
   */
  public double getLongitude() {
    return longitude;
  }

  /**
   * Sets longitude.
   *
   * @param longitude the longitude
   */
  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  /**
   * Gets person id.
   *
   * @return the person id
   */
  @NonNull
  public UUID getPersonId() {
    return personId;
  }

  /**
   * Sets person id.
   *
   * @param personId the person id
   */
  public void setPersonId(@NonNull UUID personId) {
    this.personId = personId;
  }

  /**
   * Gets display name.
   *
   * @return the display name
   */
  @NonNull
  public String getDisplayName() {
    return displayName;
  }

  /**
   * Sets display name.
   *
   * @param displayName the display name
   */
  public void setDisplayName(@NonNull String displayName) {
    this.displayName = displayName;
  }

  /**
   * Gets user image location.
   *
   * @return the user image location
   */
  public String getUserImageLocation() {
    return userImageLocation;
  }

  /**
   * Sets user image location.
   *
   * @param userImageLocation the user image location
   */
  public void setUserImageLocation(String userImageLocation) {
    this.userImageLocation = userImageLocation;
  }

  /**
   * Gets user description.
   *
   * @return the user description
   */
  public String getUserDescription() {
    return userDescription;
  }

  /**
   * Sets user description.
   *
   * @param userDescription the user description
   */
  public void setUserDescription(String userDescription) {
    this.userDescription = userDescription;
  }

  /**
   * Is this me boolean.
   *
   * @return the boolean
   */
  public boolean isThisMe() {
    return isThisMe;
  }

  /**
   * Sets this me.
   *
   * @param thisMe the this me
   */
  public void setThisMe(boolean thisMe) {
    isThisMe = thisMe;
  }

  /**
   * Gets first name.
   *
   * @return the first name
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Sets first name.
   *
   * @param firstName the first name
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * Gets last name.
   *
   * @return the last name
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Sets last name.
   *
   * @param lastName the last name
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * Gets user b day.
   *
   * @return the user b day
   */
  public Date getUserBDay() {
    return userBDay;
  }

  /**
   * Sets user b day.
   *
   * @param userBDay the user b day
   */
  public void setUserBDay(Date userBDay) {
    this.userBDay = userBDay;
  }

  /**
   * Gets user email.
   *
   * @return the user email
   */
  public String getUserEmail() {
    return userEmail;
  }

  /**
   * Sets user email.
   *
   * @param userEmail the user email
   */
  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }

  /**
   * Gets user password.
   *
   * @return the user password
   */
  public String getUserPassword() {
    return userPassword;
  }

  /**
   * Sets user password.
   *
   * @param userPassword the user password
   */
  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }

//  /**
//   * Gets invites sent.
//   *
//   * @return the invites sent
//   */
//  public List<Invitation> getInvitesSent() {
//    return invitesSent;
//  }
//
//  /**
//   * Sets invites sent.
//   *
//   * @param invitesSent the invites sent
//   */
//  public void setInvitesSent(List<Invitation> invitesSent) {
//    this.invitesSent = invitesSent;
//  }
//
//  /**
//   * Gets invites received.
//   *
//   * @return the invites received
//   */
//  public List<Invitation> getInvitesReceived() {
//    return invitesReceived;
//  }
//
//  /**
//   * Sets invites received.
//   *
//   * @param invitesReceived the invites received
//   */
//  public void setInvitesReceived(
//      List<Invitation> invitesReceived) {
//    this.invitesReceived = invitesReceived;
//  }
//
//  /**
//   * Gets invites created.
//   *
//   * @return the invites created
//   */
//  public List<Invitation> getInvitesCreated() {
//    return invitesCreated;
//  }
//
//  /**
//   * Sets invites created.
//   *
//   * @param invitesCreated the invites created
//   */
//  public void setInvitesCreated(
//      List<Invitation> invitesCreated) {
//    this.invitesCreated = invitesCreated;
//  }

  /**
   * Gets distance to user.
   *
   * @return the distance to user
   */
  public double getDistanceToUser() {
    return distanceToUser;
  }

  /**
   * Sets distance to user.
   *
   * @param distanceToUser the distance to user
   */
  public void setDistanceToUser(double distanceToUser) {
    this.distanceToUser = distanceToUser;
  }

  /**
   * Gets user image.
   *
   * @return the user image
   */
  public int getUserImage() {
    return userImage;
  }

  /**
   * Sets user image.
   *
   * @param userImage the user image
   */
  public void setUserImage(int userImage) {
    this.userImage = userImage;
  }
}
