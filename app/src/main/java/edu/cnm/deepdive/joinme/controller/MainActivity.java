package edu.cnm.deepdive.joinme.controller;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import edu.cnm.deepdive.joinme.JoinMeApplication;
import edu.cnm.deepdive.joinme.R;
import edu.cnm.deepdive.joinme.model.dao.PersonDao;
import edu.cnm.deepdive.joinme.model.db.ClientDB;
import edu.cnm.deepdive.joinme.view.FragInvitationRV;
import edu.cnm.deepdive.joinme.view.FragInvitationRV.FragInvitationRVListener;
import edu.cnm.deepdive.joinme.view.FragInviteCreate;
import edu.cnm.deepdive.joinme.view.FragInviteCreate.FragInviteCreateListener;
import edu.cnm.deepdive.joinme.view.FragInviteDetails;
import edu.cnm.deepdive.joinme.view.FragInviteDetails.FragInviteDetailsListener;
import edu.cnm.deepdive.joinme.view.FragMainMenu;
import edu.cnm.deepdive.joinme.view.FragPeopleRV;
import edu.cnm.deepdive.joinme.view.FragPeopleRV.FragPeopleRVListener;
import edu.cnm.deepdive.joinme.view.FragUserProf;
import edu.cnm.deepdive.joinme.view.FragUserProf.FragUserProfListener;
import edu.cnm.deepdive.joinme.view.SignInActivity;

/**
 * Main Activity class that defines how to switch fragments, has setters and getters for Invitation
 * ID and Person ID and build the Client Database.
 */
public class MainActivity extends AppCompatActivity
    //implements FragInvitationRVListener,
    //FragUserProfListener, FragPeopleRVListener, FragInviteCreateListener,
    //FragInviteDetailsListener
    {

  private static final String TAG = "MainActivity";

  private Toolbar toolbar;
  private FrameLayout container;
  private FragmentManager fragmentManager;
  private FragMainMenu fragMainMenu;
  private FragInvitationRV fragInvitationRV;
  private FragUserProf fragUserProf;
  private FragPeopleRV fragPeopleRV;
  private FragInviteDetails fragInviteDetails;
  private FragInviteCreate fragInviteCreate;
  private int calledInviteListType;
  private int calledPeopleListType;
  private ClientDB clientDB;
  private long invitationId;
  private long personId;



  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initDB();
    initViews();
    initDataIntoViews();
    setPersonId(getIntent().getLongExtra(getString(R.string.person_id_key), 0));
  }

  private void initDataIntoViews() {
    fragmentManager = getSupportFragmentManager();
    setSupportActionBar(toolbar);
    goToFragMainMenu();
  }

  private void initViews() {
    toolbar = findViewById(R.id.tb_main_toolbar);
    container = findViewById(R.id.fl_main_frag_container);
    toolbar.setOnClickListener(v -> goToFragMainMenu());
  }

  private void initDB() {
    clientDB = ClientDB.getInstance(this);
    new ClientDBTask().execute();
  }

  /**
   * Main fragment switcher from the Main Screen.
   * @param fragment
   * @param useStack
   * @param variant
   * @param manager
   */
  public static void switchFragment(Fragment fragment, boolean useStack, String variant, FragmentManager manager) {
    String tag = fragment.getClass().getSimpleName() + ((variant != null) ? variant : "");
    try {
      if (manager.findFragmentByTag(tag) != null) {
        manager.popBackStackImmediate(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
      }
    } catch (IllegalStateException e) {
      //do nothing, by design
    }
    FragmentTransaction transaction = manager.beginTransaction();
    transaction.replace(R.id.fl_main_frag_container, fragment, tag);
      if (useStack) {
        transaction.addToBackStack(tag);
      }
    transaction.commit();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.toolbar_menu, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.emergency_button:
        //todo: wired this up and swap to the emergency fragment
        break;
      case R.id.sign_out:
        signOut();
        break;
      default:
        return super.onOptionsItemSelected(item);
    }
    return true;
  }

  /**
   * Method that automatically switches to the Main Menu Frag after sign in.
   */
  public void goToFragMainMenu(){
    if(fragMainMenu==null){
      fragMainMenu = new FragMainMenu();
    }
   switchFragment(fragMainMenu,false,"",fragmentManager);
  }

//  public void goToFragInvitationRv(int inviteListType){
//    if(fragInvitationRV==null){
//      fragInvitationRV = new FragInvitationRV();
//    }
//    calledInviteListType = inviteListType;
//    switchFragment(fragInvitationRV, true, "");
//  }
//
//  public void goToFragUserProf() {
//    if(fragUserProf==null) {
//      fragUserProf = new FragUserProf();
//    }
//    swapFrags(fragUserProf);
//  }
//
//  public void goToFragPeopleRv(int peopleListType){
//    if(fragPeopleRV==null){
//      fragPeopleRV = new FragPeopleRV();
//    }
//    calledPeopleListType = peopleListType;
//    swapFrags(fragPeopleRV);
//  }
//
//  public void goToFragInviteDetails(){
//    if(fragInviteDetails==null) {
//      fragInviteDetails = new FragInviteDetails();
//    }
//    swapFrags(fragInviteDetails);
//  }

//  public void goToFragInviteCreate() {
//    if(fragInviteCreate==null) {
//      fragInviteCreate = new FragInviteCreate();
//    }
//    swapFrags(fragInviteCreate);
//  }

  private void signOut() {
    JoinMeApplication join = JoinMeApplication.getInstance();
    join.getClient().signOut().addOnCompleteListener(this, (task) -> {
      join.setAccount(null);
      Intent intent = new Intent(MainActivity.this, SignInActivity.class);
      intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
      startActivity(intent);
    });
  }

  /**
   * Gives rest of project access to the Invitation ID.
   * @return
   */
  public long getInvitationId() {
    return invitationId;
  }

  /**
   * Allows rest of project to set an Invitation ID.
   * @param invitationId
   */
  public void setInvitationId(long invitationId) {
    this.invitationId = invitationId;
  }

  /**
   * Gives rest of project access to the current Person ID.
   * @return
   */
  public long getPersonId() {
    return personId;
  }

  /**
   * Allows rest of project to set an Invitation ID.
   * @param personId
   */
  public void setPersonId(long personId) {
    this.personId = personId;
  }

  /**
   * Gives rest of project access to a list of invites.
   * @return
   */
  public int getCalledInviteListType() {
    return calledInviteListType;
  }

  /**
   * Gives rest of project access to a lit of people.
   * @return
   */
  public int getCalledPeopleListType(){
    return calledPeopleListType;
  }


  private class ClientDBTask extends AsyncTask<Void, Void, Void> {

    @Override
    protected Void doInBackground(Void... voids) {
      PersonDao personDao = clientDB.getPersonDao();
      personDao.selectAll();
      return null;
    }
  }
}
