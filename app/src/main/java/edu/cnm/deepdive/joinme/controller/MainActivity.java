package edu.cnm.deepdive.joinme.controller;

import android.Manifest.permission;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.view.View;
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

public class MainActivity extends AppCompatActivity implements FragInvitationRVListener,
    FragUserProfListener, FragPeopleRVListener, FragInviteCreateListener,
    FragInviteDetailsListener {

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
  private static final int UPDATE_INTERVAL_MS = 30000;
  private static final int FASTEST_INTERVAL_MS = 25000;
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
  private boolean mLocationPermissionGranted = false;
  private FusedLocationProviderClient fusedLocationProviderClient;
  private LocationRequest mLocationRequest;
  private LocationCallback mLocationCallback;
  private Location mCurrentLocation;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initViews();
    initDataIntoViews();
    initData();
    initLoc();

//   getSupportFragmentManager().beginTransaction().replace(R.id.fl_main_frag_container,
//       new FragUserProf()).commit();
  }

  private void initLoc() {
    createLocationRequest();
    mLocationCallback = new LocationCallback() {
      @Override
      public void onLocationResult(LocationResult locationResult) {
        if (locationResult == null) {
          return;
        }
        mCurrentLocation = locationResult.getLastLocation();
        Toast.makeText(getBaseContext(), "Lat: " + mCurrentLocation.getLatitude() + "     Long: " + mCurrentLocation.getLongitude(), Toast.LENGTH_LONG).show();
//        setTokenDistances();
//        sortDBTokens();
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
    setPersonId(getIntent().getLongExtra(getString(R.string.person_id_key), 0));
    fragmentManager = getSupportFragmentManager();
    fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
    getLastKnownLocation();
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
   *  Callback from activity asking for user to enable GPS permission.  If permission is granted,
   *  then starts user location updates.
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
    toolbar.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        goToFragMainMenu();
      }
    });
  }

  private void initDB() {
    clientDB = ClientDB.getInstance(this);
    clientDB.getInvitationDao();
    clientDB.getPersonDao();
    new ClientDBTask().execute();
  }


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
      default:
        return super.onOptionsItemSelected(item);
    }
    return true;
  }

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

  public long getInvitationId() {
    return invitationId;
  }

  public void setInvitationId(long invitationId) {
    this.invitationId = invitationId;
  }

  public long getPersonId() {
    return personId;
  }

  public void setPersonId(long personId) {
    this.personId = personId;
  }

  public int getCalledInviteListType() {
    return calledInviteListType;
  }

  @Override
  public int getCalledPeopleListType() {
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
