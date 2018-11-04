package edu.cnm.deepdive.joinme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

  private static final String TAG = "MainActivity";

  private Toolbar toolbar;
  private FrameLayout container;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initDB();
    initViews();
    initDataIntoViews();

  }

  private void initDataIntoViews() {
    setSupportActionBar(toolbar);
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
}
