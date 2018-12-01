package edu.cnm.deepdive.joinme.controller;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
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
import edu.cnm.deepdive.joinme.view.FragMainMenu.FragMainMenuListener;
import edu.cnm.deepdive.joinme.view.FragPeopleRV;
import edu.cnm.deepdive.joinme.view.FragPeopleRV.FragPeopleRVListener;
import edu.cnm.deepdive.joinme.view.FragUserProf;
import edu.cnm.deepdive.joinme.view.FragUserProf.FragUserProfListener;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FragInvitationRVListener,
    FragMainMenuListener, FragUserProfListener, FragPeopleRVListener, FragInviteCreateListener,
    FragInviteDetailsListener {

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
  private Context context;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
//    initDB();
//    initViews();
//    initDataIntoViews();
   // getSupportFragmentManager().beginTransaction().replace(R.id.fl_main_frag_container,
   //     new FragPeopleRV()).commit();
  }

  private void initDataIntoViews() {
    fragmentManager = getSupportFragmentManager();
    setSupportActionBar(toolbar);
    goToFragMainMenu();
  }

  private void initViews() {
    toolbar = findViewById(R.id.tb_main_toolbar);
    container = findViewById(R.id.fl_main_frag_container);
    toolbar.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        goToFragMainMenu();
      }
    });
  }

  private void initDB() {
    clientDB = ClientDB.getInstance(this);
    new ClientDBTask().execute();
  }

  public void swapFrags(Fragment fragIn){
    if(fragIn==null){
      Log.d(TAG, "swapFrags: null fragment");
      return;
    }
    fragmentManager.beginTransaction()
        .replace(container.getId(), fragIn)
        .commit();
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
      default:
        return super.onOptionsItemSelected(item);
    }
    return true;
  }

  public void goToFragMainMenu(){
    if(fragMainMenu==null){
      fragMainMenu = new FragMainMenu();
    }
    swapFrags(fragMainMenu);
  }

  public void goToFragInvitationRv(int inviteListType){
    if(fragInvitationRV==null){
      fragInvitationRV = new FragInvitationRV();
    }
    calledInviteListType = inviteListType;
    swapFrags(fragInvitationRV);
  }

  public void goToFragUserProf() {
    if(fragUserProf==null) {
      fragUserProf = new FragUserProf();
    }
    swapFrags(fragUserProf);
  }

  public void goToFragPeopleRv(int peopleListType){
    if(fragPeopleRV==null){
      fragPeopleRV = new FragPeopleRV();
    }
    calledPeopleListType = peopleListType;
    swapFrags(fragPeopleRV);
  }

  public void goToFragInviteDetails(){
    if(fragInviteDetails==null) {
      fragInviteDetails = new FragInviteDetails();
    }
    swapFrags(fragInviteDetails);
  }

  public int getCalledInviteListType() {
    return calledInviteListType;
  }

  @Override
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
