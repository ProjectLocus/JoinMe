package edu.cnm.deepdive.joinme.view;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import edu.cnm.deepdive.joinme.R;
import edu.cnm.deepdive.joinme.model.db.ClientDB;
import edu.cnm.deepdive.joinme.model.entity.Invitation;

public class FragInviteCreate extends Fragment {

  private static final String TAG = "FragInviteCreate";

  private TextView inviteCreateTitle0;
  private EditText inviteCreateTitle1;
  private EditText inviteCreateLocation;
  private EditText inviteCreateDate;
  private EditText inviteCreateTime;
  private Button doneButton;
  private long invitationId;

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
    String result = inviteCreateTitle1.getText().toString();
    inviteCreateLocation = view.findViewById(R.id.et_invitation_create_location);
    String result1 = inviteCreateLocation.getText().toString();
    inviteCreateDate = view.findViewById(R.id.et_invitation_create_date);
    String result2 = inviteCreateDate.getText().toString();
    inviteCreateTime = view.findViewById(R.id.et_invitation_create_time);
    String result3 = inviteCreateTime.getText().toString();
    doneButton = view.findViewById(R.id.bt_invitation_done);
    doneButton.setOnClickListener(v -> new QueryTask().execute(result, result1, result2, result3));
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

  private class QueryTask extends AsyncTask<String, Void, Long> {

    @Override
    protected Long doInBackground(String... strings) {
      Invitation invitation = ClientDB.getInstance(getContext()).getInvitationDao().selectAllTime(strings[0]);
      if (invitation == null) {
        invitation = new Invitation();
        invitation.setTitle(strings[0]);
        invitation.setLocation(strings[1]);
        invitation.setDate(strings[2]);
        invitation.setTime(strings[3]);
        return ClientDB.getInstance(getContext()).getInvitationDao().insert(invitation);
      }
      return invitation.getInvitationId();
    }

    @Override
    protected void onPostExecute(Long aLong) {
      new Invitation().setInvitationId(invitationId);
    }
  }
}
