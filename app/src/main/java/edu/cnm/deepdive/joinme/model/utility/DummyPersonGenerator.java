package edu.cnm.deepdive.joinme.model.utility;

import android.content.Context;
import edu.cnm.deepdive.joinme.R;
import edu.cnm.deepdive.joinme.model.entity.Person;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * The type Dummy person generator.
 */
public class DummyPersonGenerator {

  private static Random rng = new Random();

  /**
   * A static method that returns a list of Persons with fields filled with dummy values.  Exactly 1
   * person in the returned list will have the boolen flag isThisMe set to true.
   *
   * @param xDummies specify the number of dummy Persons that should be returned in the list
   * @param context the context the method is being called from
   * @return list
   */
  public static List<Person> getXDummyPersons(int xDummies, Context context) {
    List<Person> dummyList = new LinkedList<>();
    if (dummyList.size() > 0) {

      dummyList.get(0).setThisMe(true);
      dummyList.get(0).setDistanceToUser(0.0);
      // dummyList.get(0).setInvitesReceived(DummyInvitationGenerator.getXDummyInvitations(true, dummyList.get(0), context, 50));

    }
    return dummyList;
  }

  /**
   * A static method that returns a list of Persons with fields filled with dummy values.  None of
   * the Persons in the returned list will be flagged as true for being a device user.
   *
   * @param xDummies the number of Persons to create
   * @param context the context
   * @return the list
   */
  public static List<Person> getXDummyPersonsNoDeviceUser(int xDummies, Context context) {
    List<Person> dummyList = new LinkedList<>();
    for(int i = 0; i<xDummies;i++){
      dummyList.add(setUpPerson(false));
    }
    return dummyList;
  }



  private static Person setUpPerson(boolean isMe) {

    Person tempDummy = new Person();

    String dName[] = {"Gangsta", "Purple man", "The word", "Friend finder"};
    String fName[] = {"John", "Hero", "Tom", "Roger"};
    String lName[] = {"Smith", "Garcia", "Ray", "Johnson"};
    String email = "TestEmail@testEmail.com";
    String password = "password123";

    tempDummy.setDisplayName(dName[rng.nextInt(dName.length - 1)]);
    tempDummy.setFirstName(fName[rng.nextInt(fName.length - 1)]);
    tempDummy.setLastName(lName[rng.nextInt(lName.length - 1)]);
    tempDummy.setUserDescription("I am a person and my ID is: " + new UUID(10, 10));
    tempDummy.setUserEmail(email);
    tempDummy.setUserPassword(password);
    tempDummy.setPersonId(UUID.randomUUID());

    tempDummy.setThisMe(isMe);

    tempDummy.setUserBDay(new Date());

    tempDummy.setDistanceToUser(rng.nextDouble());
    tempDummy.setLatitude(rng.nextDouble());
    tempDummy.setLongitude(rng.nextDouble());
    //tempDummy.setUserImage(context.getResources().getDrawable(R.drawable.ic_assignment_ind_gray_24dp));
    return tempDummy;
  }
}
