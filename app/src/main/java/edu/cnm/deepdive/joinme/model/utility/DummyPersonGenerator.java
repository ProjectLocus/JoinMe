package edu.cnm.deepdive.joinme.model.utility;

import android.content.Context;
import edu.cnm.deepdive.joinme.R;
import edu.cnm.deepdive.joinme.model.entity.Person;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class DummyPersonGenerator {

  private static Random rng = new Random();
  /**
   * A static method that returns a list of Persons with fields filled with dummy values.  Exactly 1
   * person in the returned list will have the boolen flag isThisMe set to true.
   * @param xDummies specify the number of dummy Persons that should be returned in the list
   * @param context the context the method is being called from
   * @return
   */
  public static List<Person> getXDummyPersons(int xDummies, Context context){
    List<Person> dummyList = new LinkedList<>();
    for (int i = 0; i < xDummies; i++) {
      Person tempDummy = new Person();
      tempDummy.setDisplayName(i + " display name");
      tempDummy.setDistanceToUser(rng.nextDouble());
      tempDummy.setFirstName(i + " first name");
      tempDummy.setLastName(i + " last name");
      tempDummy.setLatitude(rng.nextDouble());
      tempDummy.setLongitude(rng.nextDouble());
      tempDummy.setPersonId(UUID.randomUUID());
      tempDummy.setThisMe(false);
      tempDummy.setUserBDay(new Date());
      tempDummy.setUserDescription("I am a person and my ID is: " + tempDummy.getPersonId().toString());
      tempDummy.setUserEmail(i + "@email.com");
      tempDummy.setUserPassword("password");
      tempDummy.setUserImage(context.getResources().getDrawable(R.drawable.ic_assignment_ind_gray_24dp));
      dummyList.add(tempDummy);
    }
    if(dummyList.size()>0){
      dummyList.get(0).setThisMe(true);
      dummyList.get(0).setDistanceToUser(0.0);
    }
    return dummyList;
  }

}
