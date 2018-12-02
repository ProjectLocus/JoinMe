package edu.cnm.deepdive.joinme.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;
import edu.cnm.deepdive.joinme.JoinMeApplication;
import edu.cnm.deepdive.joinme.R;
import edu.cnm.deepdive.joinme.controller.MainActivity;

/**
 * The type Sign in activity.
 */
public class SignInActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
  private static final int REQUEST_CODE = 1010;
  /**
   * The Sign in.
   * add the
   */
  SignInButton signIn;
  static GoogleSignInAccount account;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sign_in);
    signIn = findViewById(R.id.sign_in);
    signIn.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        signIn();
      }
    });
  }

  @Override
  protected void onStart() {
    GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
    if(account != null){
      JoinMeApplication.getInstance().setAccount(account);
    }
    super.onStart();
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if(requestCode == REQUEST_CODE){
      try {
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        account = task.getResult(ApiException.class);
        JoinMeApplication.getInstance().setAccount(account);
      } catch (ApiException e) {
        //e.printStackTrace();
      }
      switchToMain();
    }
  }


  private void signIn(){
    Intent intent = JoinMeApplication.getInstance().getClient().getSignInIntent();
    startActivityForResult(intent, REQUEST_CODE);
  }

  private void switchToMain(){
    Intent intent = new Intent(this, MainActivity.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);
  }

  @Override
  public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

  }
}
