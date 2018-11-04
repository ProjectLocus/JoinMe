package edu.cnm.deepdive.joinme.controller;

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
import edu.cnm.deepdive.joinme.view.FragInviteCreate;
import edu.cnm.deepdive.joinme.view.FragInviteCreate.FragInviteCreateListener;
import edu.cnm.deepdive.joinme.view.FragInviteIn;
import edu.cnm.deepdive.joinme.view.FragInviteIn.FragInviteInListener;
import edu.cnm.deepdive.joinme.view.FragInviteOut;
import edu.cnm.deepdive.joinme.view.FragInviteOut.FragInviteOutListener;

public class MainActivity extends AppCompatActivity implements FragInviteOutListener,
    FragInviteInListener, FragInviteCreateListener {

  private static final String TAG = "MainActivity";

  private Toolbar toolbar;
  private FrameLayout container;
  private FragmentManager fragmentManager;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initDB();
    initViews();
    initDataIntoViews();

  }

  private void initDataIntoViews() {
    fragmentManager = getSupportFragmentManager();
    setSupportActionBar(toolbar);
    goToFragInviteOut();
  }

  private void initViews() {
    toolbar = findViewById(R.id.tb_main_toolbar);
    container = findViewById(R.id.fl_main_frag_container);
    toolbar.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        //todo: implement fragment swap to home fragment
      }
    });
  }

  private void initDB() {
  }

  protected void swapFrags(Fragment fragIn){
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

  @Override
  public void goToFragInviteIn() {
    swapFrags(new FragInviteIn());
  }

  @Override
  public void goToFragInviteCreate() {
    swapFrags(new FragInviteCreate());
  }

  @Override
  public void goToFragInviteOut() {
    swapFrags(new FragInviteOut());
  }
}
