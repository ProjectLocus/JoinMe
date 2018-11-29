package edu.cnm.deepdive.joinme.model.utility;

import android.content.Context;
import edu.cnm.deepdive.joinme.model.entity.Invitation;
import edu.cnm.deepdive.joinme.model.entity.Person;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
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
  public static List<Invitation> getXDummyInvitations(boolean isReceivedInvite, Person theUser, Context context, int xDummies){
    List<Invitation> dummyInvitations = new LinkedList<>();
    for (int i = 0; i < xDummies; i++) {
      Invitation tempInvitation = new Invitation();
      if(isReceivedInvite){
        tempInvitation.setUserSender(DummyPersonGenerator.getXDummyPersonsNoDeviceUser(1, context).get(0));
        tempInvitation.setUserReceiver(theUser);
        tempInvitation.setDescription("An invite To: you.  From: " + tempInvitation.getUserSender().toString());
      }else{
        tempInvitation.setUserSender(theUser);
        tempInvitation.setUserReceiver(DummyPersonGenerator.getXDummyPersonsNoDeviceUser(1, context).get(0));
        tempInvitation.setDescription("An invite To: " + tempInvitation.getUserReceiver().toString() + "From: you.");
      }
      tempInvitation.setCreated(new Date().toString());
      tempInvitation.setDate(new Date().toString());
      tempInvitation.setInvitationId(UUID.randomUUID());
      dummyInvitations.add(tempInvitation);
    }
    return dummyInvitations;
  }

}
