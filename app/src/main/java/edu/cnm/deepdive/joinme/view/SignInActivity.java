package edu.cnm.deepdive.joinme.view;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;
import edu.cnm.deepdive.joinme.JoinMeApplication;
import edu.cnm.deepdive.joinme.R;
import edu.cnm.deepdive.joinme.controller.MainActivity;
import edu.cnm.deepdive.joinme.model.db.ClientDB;
import edu.cnm.deepdive.joinme.model.entity.Person;
import edu.cnm.deepdive.joinme.service.JoinMeBackEndService;

/**
 * The type Sign in activity.
 */
public class SignInActivity extends AppCompatActivity {
  private static final int REQUEST_CODE = 1010;
  /**
   * The Sign in.
   * add the
   */
  private SignInButton signIn;
  private long personId;
  private Context context;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sign_in);
    signIn = findViewById(R.id.sign_in);
    signIn.setOnClickListener((view) -> signIn());
  }

  @Override
  protected void onStart() {
    super.onStart();
    GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
    if(account != null){
      JoinMeApplication.getInstance().setAccount(account);
      switchToMain();
    }
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    if(requestCode == REQUEST_CODE){
      try {
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        GoogleSignInAccount account = task.getResult(ApiException.class);
        String name = account.getDisplayName();
        String email = account.getEmail();
        String givenName = account.getGivenName();
        String familyName = account.getFamilyName();
        String userImage = account.getPhotoUrl().toString();
        JoinMeApplication.getInstance().setAccount(account);
        new QueryTask().execute(name, email, givenName, familyName, userImage);
        //getLocation();
        switchToMain();
      } catch (ApiException e) {
        Toast.makeText(this,"There was an error logging in", Toast.LENGTH_LONG).show();
      }
    }
  }

//  private void getLocation() {
//    if (checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED &&
//       checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//      Toast.makeText(context, "Enable Location Permissions", Toast.LENGTH_LONG).show();
//    } else {
//      LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//      Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//      double longitude = location.getLongitude();
//      double latitude = location.getLatitude();
//      new LocationTask().execute(latitude, longitude);
//    }
//  }


  private void signIn(){
    Intent intent = JoinMeApplication.getInstance().getClient().getSignInIntent();
    startActivityForResult(intent, REQUEST_CODE);
  }

  private void switchToMain(){
    Intent intent = new Intent(this, MainActivity.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);
  }

  public long getPersonId() {
    return personId;
  }

  public void setPersonId(long personId) {
    this.personId = personId;
  }

  private class QueryTask extends AsyncTask<String, Void, Long> {


    /**
     * Creates an instance of the Client database, grabs a query from the Person Dao, and
     * inserts the email and user name data into the Person entity.
     * @param strings
     * @return
     */
    @Override
    protected Long doInBackground(String... strings) {
      Person person = ClientDB.getInstance(getApplicationContext()).getPersonDao().select(strings[0]);
      if (person==null) {
        person = new Person();
        person.setUserEmail(strings[0]);
        person.setDisplayName(strings[1]);
        person.setFirstName(strings[2]);
        person.setLastName(strings[3]);
        person.setUserImage(strings[4]);
        return ClientDB.getInstance(getApplicationContext()).getPersonDao().insert(person);
      }
      return person.getPersonId();
    }

    /**
     * Sets an ID for the user when the email and user name are successfully inserted.
     * @param aLong
     */
    @Override
    protected void onPostExecute(Long aLong) {
      setPersonId(aLong);
    }
  }

//  private class LocationTask extends AsyncTask<Double, Void, Long> {
//
//    @Override
//    protected Long doInBackground(Double... doubles) {
//      Person person = ClientDB.getInstance(getApplicationContext()).getPersonDao().selectLatitude(doubles[0]);
//      if (person == null) {
//        person = new Person();
//        person.setLatitude(doubles[0]);
//        person.setLongitude(doubles[1]);
//        return ClientDB.getInstance(getApplicationContext()).getPersonDao().insert(person);
//      }
//      return person.getPersonId();
//    }
//  }
}
