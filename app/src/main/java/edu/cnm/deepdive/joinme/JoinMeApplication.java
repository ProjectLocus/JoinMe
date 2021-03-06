package edu.cnm.deepdive.joinme;


import android.app.Application;
import android.app.Person;
import com.facebook.stetho.Stetho;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import edu.cnm.deepdive.joinme.model.db.ClientDB;

/**
 * Main application class for Join Me
 */
public class JoinMeApplication extends Application {

  private static JoinMeApplication instance;

  private static GoogleSignInClient client;
  private static GoogleSignInAccount account;
  private static Person person;
  ClientDB clientDB;

  @Override
  public void onCreate() {
    super.onCreate();
    initDB();
    instance = this;
    Stetho.initializeWithDefaults(this);
    GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .requestId()
        // Include requestIdToken if we're using Google Sign-in for authenticating on a back-end server.
        .build();
    client = GoogleSignIn.getClient(this, options);
  }

  private void initDB() {
    clientDB = ClientDB.getInstance(this);
    clientDB.getPersonDao();
    clientDB.getInvitationDao();
  }


  /**
   * Gets instance.
   *
   * @return the instance
   */
  public static JoinMeApplication getInstance() {
    return instance;
  }

  /**
   * Gets client.
   *
   * @return the client
   */
  public GoogleSignInClient getClient() {
    return client;
  }

  /**
   * Sets client.
   *
   * @param client the client
   */
  public void setClient(GoogleSignInClient client) {
    this.client = client;
  }

  /**
   * Gets account.
   *
   * @return the account
   */
  public GoogleSignInAccount getAccount() {
    return account;
  }

  /**
   * Sets account.
   *
   * @param account the account
   */
  public void setAccount(GoogleSignInAccount account) {
    this.account = account;
  }

  /**
   * Gets person
   * @return
   */
  public static Person getPerson() {
    return person;
  }

  /**
   * Sets person
   * @param person
   */
  public static void setPerson(Person person) {
    JoinMeApplication.person = person;
  }
}
