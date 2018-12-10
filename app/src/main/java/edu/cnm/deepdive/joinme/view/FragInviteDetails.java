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
import android.widget.TextView;
import edu.cnm.deepdive.joinme.R;
import edu.cnm.deepdive.joinme.controller.MainActivity;
import edu.cnm.deepdive.joinme.model.db.ClientDB;
import edu.cnm.deepdive.joinme.model.entity.Invitation;

/**
 * Class that allows a certain person view an Invitation Object. It takes the invitation ID and the
 * userSender ID to show the appropriate details.
 */
public class FragInviteDetails extends Fragment {

  private static final String TAG = "FragInviteDetails";

  private TextView tInvitationDetailsTitle, eInviteDetailsTitle, inviteDetailsLocation,
      inviteDetailsDate, inviteDetailsDescription;

  private Button inviteDetailsAccept, inviteDetailsDecline, inviteDetailsSeeAttendees;
  private View view;
  private long invitationId;
  private Invitation theInvite;
  private ClientDB clientDB;

  private FragInviteDetailsListener fragInviteDetailsListener;

  public interface FragInviteDetailsListener {
    ClientDB getClientDB();
  }


  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.fragment_invite_details, container, false);
    initViews();
    clientDB = fragInviteDetailsListener.getClientDB();
    new QueryTask().execute();
    return view;
  }

  private void initViews() {
    tInvitationDetailsTitle = view.findViewById(R.id.tv_invitation_details_title);
    eInviteDetailsTitle = view.findViewById(R.id.et_invitation_details_title);
    inviteDetailsLocation = view.findViewById(R.id.et_invitation_details_location);
    inviteDetailsDate = view.findViewById(R.id.et_invitation_details_date);
    inviteDetailsDescription = view.findViewById(R.id.et_invitation_details_description);
    inviteDetailsAccept = view.findViewById(R.id.bt_invitation_details_accept);
    inviteDetailsAccept.setOnClickListener(v -> {
      theInvite.setWillAttend(true);
      new UpdateInviteTask().execute(theInvite);
    });
    inviteDetailsDecline = view.findViewById(R.id.bt_invitation_details_decline);
    inviteDetailsDecline.setOnClickListener(v -> {
      new DeleteInviteTask().execute(theInvite);
    });
    inviteDetailsSeeAttendees = view.findViewById(R.id.bt_invitation_details_see_attendees);
    inviteDetailsSeeAttendees.setOnClickListener(v -> { });

  }

  private class QueryTask extends AsyncTask<Void, Void, Invitation> {

    @Override
    protected Invitation doInBackground(Void... voids) {
      Invitation invitation = ClientDB.getInstance(getContext()).getInvitationDao()
          .selectAllInvitationId(((MainActivity) getActivity()).getInvitationId());
      return invitation;
    }

    @Override
    protected void onPostExecute(Invitation invitation) {
      if (invitation != null) {
        theInvite = invitation;
        eInviteDetailsTitle.setText(invitation.getTitle());
        inviteDetailsLocation.setText(invitation.getLocation());
        inviteDetailsDate.setText(invitation.getDate());
        inviteDetailsDescription.setText(invitation.getDescription());
        invitationId = invitation.getInvitationId();
      } else {
        eInviteDetailsTitle.setText("Let's have coffee");
        inviteDetailsLocation.setText("Starbucks");
        inviteDetailsDate.setText("Today at Noon");
        inviteDetailsDescription.setText("Come have coffee with me.");
      }
    }
  }

  private class DeleteInviteTask extends AsyncTask<Invitation, Void, Void> {

    @Override
    protected Void doInBackground(Invitation... invitations) {
      if(invitations[0]!=null){
        clientDB.getInvitationDao().delete(invitations[0]);
      }
      return null;
    }
  }

  private class UpdateInviteTask extends AsyncTask<Invitation, Void, Void> {

    @Override
    protected Void doInBackground(Invitation... invitations) {
      if(invitations[0]!=null){
        clientDB.getInvitationDao().update(invitations[0]);
      }
      return null;
    }
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    try {
      fragInviteDetailsListener = (FragInviteDetailsListener) getActivity();
    } catch (ClassCastException e) {
      Log.e(TAG, "onAttach: ClassCastException" + e.getMessage());
    }
  }

}
