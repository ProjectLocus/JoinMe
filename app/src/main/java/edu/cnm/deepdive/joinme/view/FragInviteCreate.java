package edu.cnm.deepdive.joinme.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import edu.cnm.deepdive.joinme.R;

public class FragInviteCreate extends Fragment {

  private static final String TAG = "FragInviteCreate";

  private TextView inviteCreateTitle0;
  private EditText inviteCreateTitle1;
  private EditText inviteCreateLocation;
  private EditText inviteCreateDate;
  private EditText inviteCreateTime;

  private FragInviteCreateListener fragInviteCreateListener;

  public interface FragInviteCreateListener{

  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View theView = inflater.inflate(R.layout.fragment_invite_create, container, false);
    //do stuff
    initViews(theView);

    return theView;
  }

  private void initViews(View view) {
    inviteCreateTitle0 = view.findViewById(R.id.tv_invite_create_title);
    inviteCreateTitle1 = view.findViewById(R.id.et_invitation_create_title);
    inviteCreateLocation = view.findViewById(R.id.et_invitation_create_location);
    inviteCreateDate = view.findViewById(R.id.et_invitation_create_date);
    inviteCreateTime = view.findViewById(R.id.et_invitation_create_time);
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    try {
      fragInviteCreateListener = (FragInviteCreateListener) getActivity();
    } catch (ClassCastException e) {
      Log.e(TAG, "onAttach: ClassCastException" + e.getMessage());
    }

  }
}
