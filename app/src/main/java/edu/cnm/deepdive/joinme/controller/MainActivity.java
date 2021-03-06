package edu.cnm.deepdive.joinme.controller;

import android.Manifest.permission;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import edu.cnm.deepdive.joinme.JoinMeApplication;
import edu.cnm.deepdive.joinme.R;
import edu.cnm.deepdive.joinme.model.dao.PersonDao;
import edu.cnm.deepdive.joinme.model.db.ClientDB;
import edu.cnm.deepdive.joinme.model.entity.Invitation;
import edu.cnm.deepdive.joinme.model.entity.Person;
import edu.cnm.deepdive.joinme.service.JoinMeBackEndService;
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
import edu.cnm.deepdive.joinme.view.SignInActivity;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Main Activity class that defines how to switch fragments, has setters and getters for Invitation
 * ID and Person ID and build the Client Database.
 */
public class MainActivity extends AppCompatActivity
    implements FragInvitationRVListener, FragMainMenuListener, FragPeopleRVListener,
    FragInviteDetailsListener, FragInviteCreateListener
    //FragUserProfListener, FragPeopleRVListener, FragInviteCreateListener,
    //FragInviteDetailsListener
{

  private static final String TAG = "MainActivity";


  /**
   * The constant PERMISSIONS_REQUEST_ENABLE_GPS.
   */
  public static final int PERMISSIONS_REQUEST_ENABLE_GPS = 9002;
  /**
   * The constant PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION.
   */
  public static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 9003;
  /**
   * The constant ERROR_DIALOG_REQUEST.
   */
  public static final int ERROR_DIALOG_REQUEST = 9001;
  public static final String NOT_FIRST_TIME_USER_KEY = "not_first_time_user_key";
  public static final String MY_PREFERENCES_KEY = "edu.cnm.deepdive.joinme.shared_pref_key";
  private static final int UPDATE_INTERVAL_MS = 15000;
  private static final int FASTEST_INTERVAL_MS = 10000;
  private static boolean SHOULD_FILL_DB_W_TEST = false;
  private static boolean IS_FIRST_TIME_USER = true;
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
  private Person deviceUser;
  private boolean mLocationPermissionGranted = false;
  private FusedLocationProviderClient fusedLocationProviderClient;
  private LocationRequest mLocationRequest;
  private LocationCallback mLocationCallback;
  private Location mCurrentLocation;
  private Retrofit retrofit;
  private boolean beginUserUpdates = false;
  private SharedPreferences sharedPreferences;
  private SharedPreferences.Editor editor;
  private List<Person> peopleAroundMeList = new LinkedList<>();
  private List<Invitation> invitesToMe = new LinkedList<>();
  private List<Invitation> invitesSentByMe = new LinkedList<>();
  private boolean useInviteListToMeForRV = false;
  private int retries = 7;
  private int invitationRetries = 7;
  private int callbackInt = 0;
  private int fillDBwAPI =0;
  private int invitationCalls =0;
  private int peopleCalls = 0;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    initDB();
    initViews();
    initDataIntoViews();
    initData();
    initLoc();

  }

  private void initLoc() {
    createLocationRequest();
    mLocationCallback = new LocationCallback() {
      @Override
      public void onLocationResult(LocationResult locationResult) {
        Log.d(TAG, "callback onLocationResult: #" + callbackInt++);
        if (locationResult == null) {
          return;
        }
        mCurrentLocation = locationResult.getLastLocation();
        deviceUser.setLatitude(mCurrentLocation.getLatitude());
        deviceUser.setLongitude(mCurrentLocation.getLongitude());
        new UpdateDeviceUserTask().execute(deviceUser);
        if(beginUserUpdates){
          updateUsingAPI();
        }
      }
    };
  }

  /**
   * Creates a {@link LocationRequest} object that is stored in a field variable.
   */
  protected void createLocationRequest() {
    mLocationRequest = new LocationRequest();
    mLocationRequest.setInterval(UPDATE_INTERVAL_MS);
    mLocationRequest.setFastestInterval(FASTEST_INTERVAL_MS);
    mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
  }

  private void initData() {
    sharedPreferences = getSharedPreferences(MY_PREFERENCES_KEY, Context.MODE_PRIVATE);
    editor = sharedPreferences.edit();
    if(sharedPreferences.contains(NOT_FIRST_TIME_USER_KEY)){
      IS_FIRST_TIME_USER = false;
    }
    setPersonId(getIntent().getLongExtra(getString(R.string.person_id_key), 0));
    new QuerySinglePersonTask().execute(personId);
    fragmentManager = getSupportFragmentManager();
    fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
    getLastKnownLocation();
    initServices();
  }

  private void updateUsingAPI() {
    updatePeopleNearMe();
    updateInvitations();
    beginUserUpdates = true;
  }

  private void updateInvitations() {
//    Toast.makeText(getBaseContext(), "Updating Invitations", Toast.LENGTH_LONG).show();
    JoinMeBackEndService service = retrofit.create(JoinMeBackEndService.class);
    Call<List<Invitation>> call = service.getAllInvitationsPerPerson(deviceUser.getPersonId());
    call.enqueue(new Callback<List<Invitation>>() {
      @Override
      public void onResponse(Call<List<Invitation>> call, Response<List<Invitation>> response) {
        invitationRetries = 7;
        try {
          Log.d(TAG, "updateinvitation onResponse: #" + invitationCalls++);
          if(response.body().size()>0){
            Log.d(TAG, "onResponse: invitation is being added");
            new AddInvitesTask().execute(response.body().toArray(new Invitation[0]));
          }else{
            Log.d(TAG, "onResponse: no invitations in body");
          }
        } catch (NullPointerException e) {
          //by design, do nothing. API returned unexpected and unusable body.
          Log.d(TAG, "onResponse: response was a null pointer");
        }
      }

      @Override
      public void onFailure(Call<List<Invitation>> call, Throwable t) {
        if(invitationRetries>0){
          invitationRetries--;
          updateInvitations();
        }
        else{
          invitationRetries = 7;
          Toast.makeText(getBaseContext(), "Failed to update list of invitations, check internet connection." , Toast.LENGTH_LONG).show();
        }
      }
    });
    new UpdateReceivedInvitations().execute();
    new UpdateSentInvitations().execute();
  }


  private void updatePeopleNearMe(){
    JoinMeBackEndService service = retrofit.create(JoinMeBackEndService.class);
    Call<List<Person>> call = service.getAllPeopleNearPerson(deviceUser, deviceUser.getPersonId());
    call.enqueue(new Callback<List<Person>>() {
      @Override
      public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
        retries = 7;

        try {

          List<Person> tempListPersons = response.body();
          Person[] tempArrPersons = tempListPersons.toArray(new Person[0]);
//          Log.d(TAG, "update people onResponse: personList " + tempArrPersons.length);
          new ReplaceNonDeviceUsers().execute(tempArrPersons);
        } catch (NullPointerException e) {
          //by design, do nothing. Client has sent an empty/malformed body.
        }
      }

      @Override
      public void onFailure(Call<List<Person>> call, Throwable t) {
        if(retries>0){
          updatePeopleNearMe();
          retries--;
        }
        else{
          retries = 7;
          Toast.makeText(getBaseContext(), "Failed to update list of people nearby, check internet connection." , Toast.LENGTH_LONG).show();
        }
      }
    });
  }

  /**
   * Sends an invitation to a person with a certain recipient ID.
   * @param inviteToSend
   * @param recipientId
   */
  public void sendAnInvitation(Invitation inviteToSend, long recipientId){
    JoinMeBackEndService service = retrofit.create(JoinMeBackEndService.class);
    Call<Invitation> call = service.addInvitation(recipientId, inviteToSend);
    call.enqueue(new Callback<Invitation>() {
      @Override
      public void onResponse(Call<Invitation> call, Response<Invitation> response) {
        try{
          Toast.makeText(getBaseContext(), "Invitation sent.", Toast.LENGTH_SHORT).show();
          new AddInvitesTask().execute(response.body());
        }catch (NullPointerException e){
          //by design, do nothing.  Unknown error where server sent poorly formed body.
        }
      }

      @Override
      public void onFailure(Call<Invitation> call, Throwable t) {
        Toast.makeText(getBaseContext(), "Invitation could not be delivered to server.", Toast.LENGTH_SHORT).show();
      }
    });
  }


  private void fillDBwithAPI() {
    JoinMeBackEndService service = retrofit.create(JoinMeBackEndService.class);
    //if first time, create user on backend
    Call<Person> call = service.addPerson(deviceUser);
    call.enqueue(new Callback<Person>() {
      @Override
      public void onResponse(Call<Person> call, Response<Person> response) {
        retries = 7;
        try {

          Log.d(TAG, "fillDBwithAPI onResponse: #");

          Person deviceUserReplacement = new Person();
          deviceUserReplacement.setPersonId(response.body().getPersonId());
          deviceUserReplacement.setUserImage(deviceUser.getUserImage());
          deviceUserReplacement.setUserEmail(deviceUser.getUserEmail());
          deviceUserReplacement.setFirstName(deviceUser.getFirstName());
          deviceUserReplacement.setLastName(deviceUser.getLastName());
          deviceUserReplacement.setDisplayName(deviceUser.getDisplayName());
          deviceUserReplacement.setGoogleUserId(deviceUser.getGoogleUserId());
          deviceUserReplacement.setThisMe(true);

          new UpdateFirstTimeUserTask().execute(deviceUser, deviceUserReplacement);
          personId = deviceUserReplacement.getPersonId();

          editor.putBoolean(NOT_FIRST_TIME_USER_KEY, true);
          editor.commit();

          IS_FIRST_TIME_USER = false;

          new QuerySinglePersonTask().execute(personId);

        } catch (NullPointerException e) {
          if(sharedPreferences.contains(NOT_FIRST_TIME_USER_KEY)){
            Log.d(TAG, "onResponse: null pointer exception error");
            editor.remove(NOT_FIRST_TIME_USER_KEY);
            editor.apply();
          }
        }
      }

      @Override
      public void onFailure(Call<Person> call, Throwable t) {
        if(retries>0){
          fillDBwithAPI();
          retries--;
        }
        else{
          retries = 7;
          Toast.makeText(getBaseContext(), "Failed to add new user. Cannot update information. Restart app to try again." , Toast.LENGTH_LONG).show();
        }
      }
    });

//
//        Call<List<Token>> call = service.get(endPoint);
//        call.enqueue(new Callback<List<Token>>() {
//          @Override
//          public void onResponse(@NonNull Call<List<Token>> call,
//              @NonNull Response<List<Token>> response) {
//            if (!response.isSuccessful()) {
//              return;
//            }
//            List<Token> tokensFromApi = response.body();
//            if(tokensFromApi!=null){
//              for (Token token :
//                  tokensFromApi) {
//                token.setTokenType(getServiceTokenType(endPoint));
//                token = TokenPrepper.prep(Main2Activity.this, token);
//              }
//              Token[] tokenArr = tokensFromApi.toArray(new Token[0]);
//              new AddTask().execute(tokenArr);
//            }
//            resetRetries();
//          }
//
//          @Override
//          public void onFailure(@NonNull Call<List<Token>> call, @NonNull Throwable t) {
//            if(shouldStopRetrying()){
//              Toast.makeText(getBaseContext(), getString(R.string.could_not_load_resource) + endPoint, Toast.LENGTH_SHORT).show();
//              resetRetries();
//            }else{
//              fillDBwithAPI(endPoint);
//            }
//          }
//        });
  }

  private void initServices() {
    retrofit = new Builder()
        .baseUrl("http://joinme.us-east-2.elasticbeanstalk.com/rest/")
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }

  private void getLastKnownLocation() {
    if (ActivityCompat.checkSelfPermission(this, permission.ACCESS_FINE_LOCATION)
        != PackageManager.PERMISSION_GRANTED
        && ActivityCompat.checkSelfPermission(this, permission.ACCESS_COARSE_LOCATION)
        != PackageManager.PERMISSION_GRANTED) {
      return;
    }
    fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this,
        new OnSuccessListener<Location>() {
          @Override
          public void onSuccess(Location location) {
            if (location != null) {
              mCurrentLocation = location;
            }
          }
        });
  }

  /**
   * Initializes the {@link android.arch.persistence.room.Database}.
   */
  @Override
  protected void onStart() {
    super.onStart();
    initDB();
  }

  /**
   * Removes reference to the {@link android.arch.persistence.room.Database}.
   */
  @Override
  protected void onStop() {
    clientDB = null;
    ClientDB.forgetInstance(this);
    super.onStop();

  }

  /**
   * Stops location updates.
   */
  @Override
  protected void onPause() {
    super.onPause();
    stopLocationUpdates();
  }

  /**
   * Checks to make sure permission is granted for location information and GPS information. If
   * permissions are needed, begins the process to acquire permissions. If permissions are granted,
   * starts location updates.
   */
  @Override
  protected void onResume() {
    super.onResume();
    if (checkMapServices()) {
      if (mLocationPermissionGranted) {
        startLocationUpdates();
      } else {
        getLocationPermission();
      }
    }
  }

  private void startLocationUpdates() {
    if (ActivityCompat.checkSelfPermission(this, permission.ACCESS_FINE_LOCATION)
        != PackageManager.PERMISSION_GRANTED
        && ActivityCompat.checkSelfPermission(this, permission.ACCESS_COARSE_LOCATION)
        != PackageManager.PERMISSION_GRANTED) {
      return;
    }
    fusedLocationProviderClient.requestLocationUpdates(mLocationRequest,
        mLocationCallback,
        null /* Looper */);
  }

  private void getLocationPermission() {
    /*
     * Request location permission, so that we can get the location of the
     * device. The result of the permission request is handled by a callback,
     * onRequestPermissionsResult.
     */
    if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
        android.Manifest.permission.ACCESS_FINE_LOCATION)
        == PackageManager.PERMISSION_GRANTED) {
      mLocationPermissionGranted = true;
      startLocationUpdates();
    } else {
      ActivityCompat.requestPermissions(this,
          new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
          PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
    }
  }

  private boolean checkMapServices() {
    if (isServicesOK()) {
      return isMapsEnabled();
    }
    return false;
  }

  private void buildAlertMessageNoGps() {//prompts user with dialog to enable gps
    final AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setMessage(R.string.requires_gps)
        .setCancelable(false)
        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
          public void onClick(@SuppressWarnings("unused") final DialogInterface dialog,
              @SuppressWarnings("unused") final int id) {
            Intent enableGpsIntent = new Intent(
                android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivityForResult(enableGpsIntent, PERMISSIONS_REQUEST_ENABLE_GPS);
          }
        });
    final AlertDialog alert = builder.create();
    alert.show();
  }

  /**
   * Determines whether GPS is enabled on the device.
   *
   * @return boolean true if GPS is enabled on the device.
   */
  public boolean isMapsEnabled() {//determines whether gps is enabled on the device
    final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

    if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
      buildAlertMessageNoGps();
      return false;
    }
    return true;
  }

  /**
   * Determines whether Google Play Services can be used on the device.
   *
   * @return boolean true if the user is clear to make map requests.
   */
  public boolean isServicesOK() {//this process determines whether google play services can be used on device
    Log.d(TAG, getString(R.string.check_google_svc_version));

    int available = GoogleApiAvailability.getInstance()
        .isGooglePlayServicesAvailable(MainActivity.this);

    if (available == ConnectionResult.SUCCESS) {
      //everything is fine and the user can make map requests
      Log.d(TAG, getString(R.string.google_play_svcs_is_working));
      return true;
    } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
      //an error occured but we can resolve it
      Log.d(TAG, getString(R.string.google_play_svcs_error_can_fix));
      Dialog dialog = GoogleApiAvailability.getInstance()
          .getErrorDialog(MainActivity.this, available, ERROR_DIALOG_REQUEST);
      dialog.show();
    } else {
      Toast.makeText(this, getString(R.string.cannot_make_map_requests), Toast.LENGTH_SHORT).show();
    }
    return false;
  }

  /**
   * Handles the result from a permission request for user location.
   *
   * @param requestCode used to ensure it was a request that can be handled by this method.
   * @param permissions used by super.
   * @param grantResults representation of user grants of permission.
   */
  @Override
  public void onRequestPermissionsResult(int requestCode,
      @NonNull String permissions[],
      @NonNull int[] grantResults) {
    mLocationPermissionGranted = false;
    switch (requestCode) {
      case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
        // If request is cancelled, the result arrays are empty.
        if (grantResults.length > 0
            && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
          mLocationPermissionGranted = true;
        }
      }
    }
  }

  /**
   * Callback from activity asking for user to enable GPS permission.  If permission is granted,
   * then starts user location updates.
   *
   * @param requestCode Used to ensure that this method can process the data.
   * @param resultCode Used by the call to super.
   * @param data Used by the call to super.
   */
  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    switch (requestCode) {
      case PERMISSIONS_REQUEST_ENABLE_GPS: {
        if (mLocationPermissionGranted) {
          startLocationUpdates();
        } else {
          getLocationPermission();//asking for permission to use location services
        }
      }
    }
  }

  private void stopLocationUpdates() {
    fusedLocationProviderClient.removeLocationUpdates(mLocationCallback);
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
    clientDB.getInvitationDao();
    clientDB.getPersonDao();
    new ClientDBTask().execute();
  }

  /**
   * Main fragment switcher from the Main Screen.
   */
  public static void switchFragment(Fragment fragment, boolean useStack, String variant,
      FragmentManager manager) {
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
      case R.id.home:
        goToFragMainMenu();
        break;
      default:
        return super.onOptionsItemSelected(item);
    }
    return true;
  }

  /**
   * Method that automatically switches to the Main Menu Frag after sign in.
   */
  public void goToFragMainMenu() {
    if (fragMainMenu == null) {
      fragMainMenu = new FragMainMenu();
    }
    switchFragment(fragMainMenu, false, "", fragmentManager);
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
   */
  public long getInvitationId() {
    return invitationId;
  }

  /**
   * Allows rest of project to set an Invitation ID.
   */
  public void setInvitationId(long invitationId) {
    this.invitationId = invitationId;
  }

  /**
   * Gives rest of project access to the current Person ID.
   */
  public long getPersonId() {
    return personId;
  }

  /**
   * Allows rest of project to set an Invitation ID.
   */
  public void setPersonId(long personId) {
    this.personId = personId;
  }

  /**
   * Gives rest of project access to a list of invites.
   */
  public int getCalledInviteListType() {
    return calledInviteListType;
  }

  @Override
  public List<Invitation> getInvitesForRV() {
    if(useInviteListToMeForRV){
      return invitesToMe;
    }else{
      return invitesSentByMe;
    }
  }

  /**
   * Gives rest of project access to a list of people.
   */
  public int getCalledPeopleListType() {
    return calledPeopleListType;
  }

  @Override
  public MainActivity getParentActivity() {
    return this;
  }

  @Override
  public ClientDB getClientDB() {
    return clientDB;
  }


  private class ClientDBTask extends AsyncTask<Void, Void, Void> {

    /**
     * Creates an instance of the Client database to build.
     * @param voids
     * @return
     */
    @Override
    protected Void doInBackground(Void... voids) {
      PersonDao personDao = clientDB.getPersonDao();
      personDao.selectAll();
      return null;
    }
  }

  private class QuerySinglePersonTask extends AsyncTask<Long, Void, Person> {

    /**
     * Creates an instance of the Client database, grabs a query from the Person Dao, and inserts
     * the email and user name data into the Person entity.
     */
    @Override
    protected Person doInBackground(Long... ids) {
      Person theDeviceUser = clientDB.getPersonDao().selectPerson(ids[0]);

      return theDeviceUser;
    }
    /**
     * Sets an ID for the user when the email and user name are successfully inserted.
     * @param user
     */
    @Override
    protected void onPostExecute(Person user) {
      deviceUser = user;
      if(IS_FIRST_TIME_USER){
        fillDBwithAPI();
      }
      updateUsingAPI();
    }
  }

  private class UpdateFirstTimeUserTask extends AsyncTask<Person, Void, Void> {

    /**
     *
     * @param people
     * @return
     */
    @Override
    protected Void doInBackground(Person... people) {
      clientDB.getPersonDao().delete(people[0]);
      clientDB.getPersonDao().insert(people[1]);
      return null;
    }

  }

  private class UpdateDeviceUserTask extends AsyncTask<Person, Void, Void> {

    /**
     * Updates
     * @param people
     * @return
     */
    @Override
    protected Void doInBackground(Person... people) {
      clientDB.getPersonDao().update(people[0]);
      return null;
    }

  }

  private class ReplaceNonDeviceUsers extends AsyncTask<Person, Void, List<Person>> {

    @Override
    protected List<Person> doInBackground(Person... people) {
      List<Person> tempPeople = clientDB.getPersonDao().selectAll();
      List<Person> peopleToRemove = new LinkedList<>();
      for (int i = 0; i < tempPeople.size(); i++) {
        if(tempPeople.get(i).getGoogleUserId()==null){
          peopleToRemove.add(tempPeople.get(i));
        }
        else if(!tempPeople.get(i).getGoogleUserId().equals(deviceUser.getGoogleUserId())){
          peopleToRemove.add(tempPeople.get(i));
        }
      }
      clientDB.getPersonDao().deleteList(peopleToRemove);
      List<Person> peopleToAdd = new LinkedList<>();
      peopleToAdd.addAll(Arrays.asList(people));
      clientDB.getPersonDao().insert(peopleToAdd);
      return peopleToAdd;
    }

    @Override
    protected void onPostExecute(List<Person> peopleToAdd) {
      peopleAroundMeList = new LinkedList<>();
      peopleAroundMeList = peopleToAdd;
    }
  }

  private class AddInvitesTask extends AsyncTask<Invitation, Void, Void> {

    @Override
    protected Void doInBackground(Invitation... invitations) {
      clientDB.getInvitationDao().insert(Arrays.asList(invitations));
      return null;
    }

  }

  private class UpdateSentInvitations extends AsyncTask<Void, Void, List<Invitation>> {

    @Override
    protected List<Invitation> doInBackground(Void... voids) {
      return clientDB.getInvitationDao().getInvitatiionsForUserSender(deviceUser.getPersonId());
    }

    @Override
    protected void onPostExecute(List<Invitation> sentInvites) {
      invitesSentByMe = new LinkedList<>();
      invitesSentByMe = sentInvites;
    }
  }

  private class UpdateReceivedInvitations extends AsyncTask<Void, Void, List<Invitation>> {

    @Override
    protected List<Invitation> doInBackground(Void... voids) {
      return clientDB.getInvitationDao().getInvitationsForUserReceiver(deviceUser.getPersonId());
    }

    @Override
    protected void onPostExecute(List<Invitation> receivedInvites) {
      invitesToMe = new LinkedList<>();
      invitesToMe = receivedInvites;
    }
  }

  /**
   * Returns a list of invitations that were sent by the current person.
   * @return
   */
  public List<Invitation> getInvitesSentByMe() {
    return invitesSentByMe;
  }

  /**
   * Returns a list of invitations that were sent to the current user.
   * @return
   */
  public List<Invitation> getInvitesToMe() {
    return invitesToMe;
  }

  /**
   * Returns a list of people that are around the current person.
   * @return
   */
  public List<Person> getPeopleAroundMeList() {
    return peopleAroundMeList;
  }

  /**
   * Allows a fragment to build a recycler view based on whether the invitation was sent by the current person,
   * or received by you.
   * @return
   */
  public boolean isUseInviteListToMeForRV() {
    return useInviteListToMeForRV;
  }

  /**
   * Allows rest of project to discern whever an invitation was sent by the current person, or received
   * by the current person.
   * @param useInviteListToMeForRV
   */
  public void setUseInviteListToMeForRV(boolean useInviteListToMeForRV) {
    this.useInviteListToMeForRV = useInviteListToMeForRV;
  }


}
