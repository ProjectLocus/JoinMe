package edu.cnm.deepdive.joinme.model.utility;

 import android.content.Context;
import edu.cnm.deepdive.joinme.model.entity.Invitation;
import edu.cnm.deepdive.joinme.model.entity.Person;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
 import java.util.Random;
 import java.util.UUID;

/**
 * The type Dummy invitation generator.
 */
public class DummyInvitationGenerator {

/**
* Get list of x dummy invitations.
*
* @param isReceivedInvite if the invitation is being received by the device user
* @param theUser the device user
* @param context the context
* @param xDummies the number of dummy invitations to create
* @return the list
*/

  private static Random rng = new Random();


  public static List<Invitation> getXDummyInvitations(boolean isReceivedInvite, Person theUser, Context context, int xDummies){

   List<Invitation> dummyInvitations = new LinkedList<>();


    for (int i = 0; i < xDummies; i++) {
     Invitation tempInvitation = new Invitation();
     if(isReceivedInvite){
       tempInvitation.setTitle("Received Invitation");
       tempInvitation.setDescription("An invite to you. From: " + theUser.getFirstName() + " " + theUser.getLastName());
      }else{
       tempInvitation.setTitle("Sent Invitation");
       tempInvitation.setDescription("An invite to: " + theUser.getFirstName() + " " + theUser.getLastName());
      }
      //tempInvitation.setCreated(new Date().toString());
      tempInvitation.setDate(new Date().toString());
      tempInvitation.setInvitationId(rng.nextInt()+1);
      dummyInvitations.add(tempInvitation);
    }
    return dummyInvitations;
  }

}
