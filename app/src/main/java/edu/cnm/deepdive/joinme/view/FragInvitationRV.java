package edu.cnm.deepdive.joinme.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import edu.cnm.deepdive.joinme.R;

public class FragInvitationRV extends Fragment {

  private static final String TAG = "FragInvitationRV";

  private FragInvitationRVListener fragInvitationRVListener;
  private TextView fragmentTitle;
  private RecyclerView recyclerView;
  private FloatingActionButton floatingActionButton;

  public interface FragInvitationRVListener{
    int getCalledInviteListType();
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View theView = inflater.inflate(R.layout.fragment_invitation_rv, container, false);
    initViews(theView);
    initData();
    return theView;
  }

  private void initData() {
  }

  private void initViews(View theView) {
    fragmentTitle = theView.findViewById(R.id.tv_frag_invitation_rv_title);
    recyclerView = theView.findViewById(R.id.rv_frag_invitation_rv_invitations);
    floatingActionButton = theView.findViewById(R.id.fab_frag_invitation_rv_add);
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    try {
      fragInvitationRVListener = (FragInvitationRVListener) getActivity();
    } catch (ClassCastException e) {
      Log.e(TAG, "onAttach: ClassCastException" + e.getMessage());
    }

  }
}
