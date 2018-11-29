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

  

}
