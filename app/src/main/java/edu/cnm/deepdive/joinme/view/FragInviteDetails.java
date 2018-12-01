package edu.cnm.deepdive.joinme.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import edu.cnm.deepdive.joinme.R;

public class FragInviteDetails extends Fragment {

  private TextView tInvitationDetailsTitle;
  private EditText eInviteDetailsTitle, inviteDetailsLocation, inviteDetailsDate,
      inviteDetailsTime, inviteDetailsDetails;
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
    inviteDetailsTime = view.findViewById(R.id.et_invitation_details_time);
    inviteDetailsDetails = view.findViewById(R.id.et_invitation_details_details);
    inviteDetailsAccept = view.findViewById(R.id.bt_invitation_details_accept);
    inviteDetailsAccept.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });
    inviteDetailsDecline = view.findViewById(R.id.bt_invitation_details_decline);
    inviteDetailsDecline.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });
    inviteDetailsSeeAttendees = view.findViewById(R.id.bt_invitation_details_see_attendees);
    inviteDetailsSeeAttendees.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });

  }


}
