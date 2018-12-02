package edu.cnm.deepdive.joinme.view;

import android.content.Intent;
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
  private JoinMeBackEndService joinMeBackEndService;
  private Gson gson;
  private Person person;


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
        JoinMeApplication.getInstance().setAccount(account);
        switchToMain();
      } catch (ApiException e) {
        //e.printStackTrace();
        Toast.makeText(this,"There was a error logging in", Toast.LENGTH_LONG).show();
      }
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
}
