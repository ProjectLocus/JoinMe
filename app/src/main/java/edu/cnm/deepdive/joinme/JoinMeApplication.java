package edu.cnm.deepdive.joinme;

import android.app.Application;
import com.facebook.stetho.Stetho;

public class JoinMeApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);
  }

}
