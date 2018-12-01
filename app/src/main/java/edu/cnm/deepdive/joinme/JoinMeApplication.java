package edu.cnm.deepdive.joinme;

import android.app.Application;
import android.app.Person;
import com.facebook.stetho.Stetho;

public class JoinMeApplication extends Application {

  private static Person person;
  private JoinMeApplication instance;

  @Override
  public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);
    instance = this;
  }

  public static Person getPerson() {
    return person;
  }
}
