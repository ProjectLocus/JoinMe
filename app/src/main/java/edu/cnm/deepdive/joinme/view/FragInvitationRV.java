package edu.cnm.deepdive.joinme.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;
import edu.cnm.deepdive.joinme.R;
import edu.cnm.deepdive.joinme.model.entity.Invitation;
import edu.cnm.deepdive.joinme.model.utility.DummyInvitationGenerator;
import java.util.List;
import java.util.UUID;

public class FragInvitationRV extends Fragment {

  private static final String TAG = "FragInvitationRV";

  private FragInvitationRVListener fragInvitationRVListener;
  private TextView fragmentTitle;
  private RecyclerView recyclerView;
  private FloatingActionButton floatingActionButton;
  private List<Invitation> dummyInvite;


  public interface FragInvitationRVListener{
    int getCalledInviteListType();
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View theView = inflater.inflate(R.layout.fragment_invitation_rv, container, false);
    initViews(theView);
    //initData();
    initRecyclerview();
    return theView;
  }

  private void initRecyclerview() {
    InvitationAdapter adapter = new InvitationAdapter(getActivity(),dummyInvite);
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    recyclerView.setAdapter(adapter);




    //create adapter object .create linear layout manager recyclerview.setadapter
    //recyclerVIEW.setmanager(linear layout manager.
  }

//  private void initData() {
//    dummyInvite = DummyInvitationGenerator.getXDummyInvitations(true, new UUID(5,5), getContext(),1);
//
//
//    reference to list
//    ref = dummy invitationGenerator.get.xdummyinvit
//  }




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
