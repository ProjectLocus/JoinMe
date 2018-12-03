package edu.cnm.deepdive.joinme.view;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.internal.SignInHubActivity;
import edu.cnm.deepdive.joinme.R;
import edu.cnm.deepdive.joinme.controller.MainActivity;
import edu.cnm.deepdive.joinme.model.db.ClientDB;
import edu.cnm.deepdive.joinme.model.entity.Person;

public class FragUserProf extends Fragment {

  private ImageView userProfPic;
  private TextView userDisplayName, userDescription;
  private FloatingActionButton userFA;

  private FragUserProfListener fragUserProfListener;

  public interface FragUserProfListener {

  }


  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_user_profile_ar, container);
    initView(view);
    return view;
  }

  private void initView(View view) {
    userProfPic = view.findViewById(R.id.iv_user_profile_prof_image);
    userDisplayName = view.findViewById(R.id.tv_user_profile_display_name);
    userDescription = view.findViewById(R.id.tv_user_profile_description);
    userFA = view.findViewById(R.id.fab_user_profile_next);
    userFA.setOnClickListener(v -> { });
    new QueryTask().execute();
  }

  private class QueryTask extends AsyncTask<Void, Void, Person> {

    @Override
    protected Person doInBackground(Void... voids) {
      Person person = ClientDB.getInstance(getContext()).getPersonDao().selectPerson(
          new Person().getPersonId());
      return person;
    }

    @Override
    protected void onPostExecute(Person person) {
      userDisplayName.setText(person.getDisplayName());
      userDescription.setText(person.getUserDescription());
      Glide.with(getContext()).load(person.getUserImage()).into(userProfPic);
    }
  }
}
