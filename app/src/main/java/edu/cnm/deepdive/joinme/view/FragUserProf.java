package edu.cnm.deepdive.joinme.view;

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
import edu.cnm.deepdive.joinme.R;

public class FragUserProf extends Fragment {

  private ImageView userProfPic;
  private TextView userDisplayName, userDescription;
  private FloatingActionButton userFA;

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
    userProfPic = view.findViewById(R.id.user_prof_pic);
    userDisplayName = view.findViewById(R.id.user_display_name);
    userDescription = view.findViewById(R.id.user_description);
    userFA = view.findViewById(R.id.user_fa);
    userFA.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });

  }
}
