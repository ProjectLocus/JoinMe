package edu.cnm.deepdive.joinme.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;
import edu.cnm.deepdive.joinme.R;
import edu.cnm.deepdive.joinme.controller.MainActivity;
import edu.cnm.deepdive.joinme.model.entity.Invitation;
import edu.cnm.deepdive.joinme.model.entity.Person;
import edu.cnm.deepdive.joinme.model.utility.DummyInvitationGenerator;
import java.util.List;
import java.util.UUID;

/**
 * A recycler view fragment that shows a list of Invitation objects.
 */
public class FragInvitationRV extends Fragment {

  private static final String TAG = "FragInvitationRV";

  private FragInvitationRVListener fragInvitationRVListener;
  private TextView fragmentTitle;
  private RecyclerView recyclerView;
  private FloatingActionButton floatingActionButton;
  private List<Invitation> dummyInvite;
  private List<Invitation> invitationList;


  public interface FragInvitationRVListener{
    int getCalledInviteListType();
    List<Invitation> getInvitesForRV();
    MainActivity getParentActivity();
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View theView = inflater.inflate(R.layout.fragment_invitation_rv, container, false);
    initViews(theView);
    initData();
    initRecyclerview();
    return theView;
  }

  private void initRecyclerview() {
    InvitationAdapter adapter = new InvitationAdapter(getActivity(), invitationList, getContext());
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    recyclerView.setAdapter(adapter);




    //create adapter object .create linear layout manager recyclerview.setadapter
    //recyclerVIEW.setmanager(linear layout manager.
  }

  private void initData() {
//    dummyInvite = DummyInvitationGenerator.getXDummyInvitations(true,new Person(), getContext(),10);
    invitationList = fragInvitationRVListener.getInvitesForRV();

    //reference to list
    //ref = dummy invitationGenerator.get.xdummyinvit
  }




  private void initViews(View theView) {
    fragmentTitle = theView.findViewById(R.id.tv_frag_invitation_rv_title);
    if(fragInvitationRVListener.getParentActivity().isUseInviteListToMeForRV()){
      fragmentTitle.setText("Received Invitations");
      //todo: do whatever needs to be done to visually change things to indicate the list is received invitations
    }else{
      fragmentTitle.setText("Invitations You've Sent");
      //todo: do whatever needs to be done to visually change things to indicate the list is sent invitations
    }

    recyclerView = theView.findViewById(R.id.rv_frag_invitation_rv_invitations);
    //floatingActionButton = theView.findViewById(R.id.fab_frag_invitation_rv_add);
    floatingActionButton.setOnClickListener(v -> {
      goToFragInviteCreate();
    });
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

  private void goToFragInviteCreate() {
    FragmentManager fragmentManager = getFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().addToBackStack(null);
    fragmentTransaction.replace(R.id.fl_main_frag_container, new FragInviteCreate()).commit();
  }


  // TODO Add gson retrofit service object setup function here.
}
