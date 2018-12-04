package edu.cnm.deepdive.joinme.view;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import edu.cnm.deepdive.joinme.R;
import edu.cnm.deepdive.joinme.controller.MainActivity;
import edu.cnm.deepdive.joinme.model.db.ClientDB;
import edu.cnm.deepdive.joinme.model.entity.Invitation;

public class FragInviteDetails extends Fragment {

  private TextView tInvitationDetailsTitle, eInviteDetailsTitle, inviteDetailsLocation,
      inviteDetailsDate, inviteDetailsDescription;

  private Button inviteDetailsAccept, inviteDetailsDecline, inviteDetailsSeeAttendees;

  private FragInviteDetailsListener fragInviteDetailsListener;

  public interface FragInviteDetailsListener {

  }


  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_invite_details, container, false);
    initViews(view);
    return view;
  }

  private void initViews(View view) {
    tInvitationDetailsTitle = view.findViewById(R.id.tv_invitation_details_title);
    eInviteDetailsTitle = view.findViewById(R.id.et_invitation_details_title);
    inviteDetailsLocation = view.findViewById(R.id.et_invitation_details_location);
    inviteDetailsDate = view.findViewById(R.id.et_invitation_details_date);
    inviteDetailsDescription = view.findViewById(R.id.et_invitation_details_description);
    new QueryTask().execute();
    inviteDetailsAccept = view.findViewById(R.id.bt_invitation_details_accept);
    inviteDetailsAccept.setOnClickListener(v -> { });
    inviteDetailsDecline = view.findViewById(R.id.bt_invitation_details_decline);
    inviteDetailsDecline.setOnClickListener(v -> { });
    inviteDetailsSeeAttendees = view.findViewById(R.id.bt_invitation_details_see_attendees);
    inviteDetailsSeeAttendees.setOnClickListener(v -> { });

  }

  private class QueryTask extends AsyncTask<Void, Void, Invitation> {

    @Override
    protected Invitation doInBackground(Void... voids) {
      Invitation invitation = ClientDB.getInstance(getContext()).getInvitationDao()
          .selectAllInvitationId(
              ((MainActivity) getActivity()).getInvitationId());
      return invitation;
    }

    @Override
    protected void onPostExecute(Invitation invitation) {
      eInviteDetailsTitle.setText(invitation.getTitle());
      inviteDetailsLocation.setText(invitation.getLocation());
      inviteDetailsDate.setText(invitation.getDate());
      inviteDetailsDescription.setText(invitation.getDescription());
    }
  }

}
