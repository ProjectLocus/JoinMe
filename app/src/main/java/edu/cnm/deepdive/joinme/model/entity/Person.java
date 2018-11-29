package edu.cnm.deepdive.joinme.model.entity;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
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
  @PrimaryKey(autoGenerate = true)
  private UUID personId;

  /**
   * This is the display name a person has chosen
   */
  @NonNull
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

  /**
   * This is the list of the invitations the user has sent to others
   */
  private List<Invitation> invitesSent;

  /**
   * This is the list of the invitations the user has received from others
   */
  private List<Invitation> invitesReceived;

  /**
   * This is the list of invitations the user has created
   */
  private List<Invitation> invitesCreated;

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
  private Drawable userImage;


  //***************** GETTERS AND SETTERS *******************************************************//


  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  @NonNull
  public UUID getPersonId() {
    return personId;
  }

  public void setPersonId(@NonNull UUID personId) {
    this.personId = personId;
  }

  @NonNull
  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(@NonNull String displayName) {
    this.displayName = displayName;
  }

  public String getUserImageLocation() {
    return userImageLocation;
  }

  public void setUserImageLocation(String userImageLocation) {
    this.userImageLocation = userImageLocation;
  }

  public String getUserDescription() {
    return userDescription;
  }

  public void setUserDescription(String userDescription) {
    this.userDescription = userDescription;
  }

  public boolean isThisMe() {
    return isThisMe;
  }

  public void setThisMe(boolean thisMe) {
    isThisMe = thisMe;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Date getUserBDay() {
    return userBDay;
  }

  public void setUserBDay(Date userBDay) {
    this.userBDay = userBDay;
  }

  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }

  public String getUserPassword() {
    return userPassword;
  }

  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }

  public List<Invitation> getInvitesSent() {
    return invitesSent;
  }

  public void setInvitesSent(List<Invitation> invitesSent) {
    this.invitesSent = invitesSent;
  }

  public List<Invitation> getInvitesReceived() {
    return invitesReceived;
  }

  public void setInvitesReceived(
      List<Invitation> invitesReceived) {
    this.invitesReceived = invitesReceived;
  }

  public List<Invitation> getInvitesCreated() {
    return invitesCreated;
  }

  public void setInvitesCreated(
      List<Invitation> invitesCreated) {
    this.invitesCreated = invitesCreated;
  }

  public double getDistanceToUser() {
    return distanceToUser;
  }

  public void setDistanceToUser(double distanceToUser) {
    this.distanceToUser = distanceToUser;
  }

  public Drawable getUserImage() {
    return userImage;
  }

  public void setUserImage(Drawable userImage) {
    this.userImage = userImage;
  }
}
