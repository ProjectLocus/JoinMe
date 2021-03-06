package edu.cnm.deepdive.joinme.view;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.internal.SignInHubActivity;
import edu.cnm.deepdive.joinme.R;
import edu.cnm.deepdive.joinme.controller.MainActivity;
import edu.cnm.deepdive.joinme.model.db.ClientDB;
import edu.cnm.deepdive.joinme.model.entity.Invitation;
import edu.cnm.deepdive.joinme.model.entity.Person;
import java.util.List;

/**
 * Class for creating an invitation. The class grabs the inputed data and sets it in the Client
 * database with both an invitation ID and person ID to then be sent out.
 */
public class FragInviteCreate extends Fragment {

  private static final String TAG = "FragInviteCreate";

  private TextView inviteCreateTitle0;
  private EditText inviteCreateTitle1;
  private EditText inviteCreateLocation;
  private EditText inviteCreateDate;
  private EditText inviteCreateDescription;
  private Button doneButton;
  private Context context;
  private View view;
  private RecyclerView recyclerView;
  private List<Person> persons;


  private FragInviteCreateListener fragInviteCreateListener;

  public interface FragInviteCreateListener {
    void sendAnInvitation(Invitation inviteToSend, long recipientId);
    MainActivity getParentActivity();
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.fragment_invite_create, container, false);
    initViews();
    initButton();
    initRecyclerView();
    return view;
  }

  private void initViews() {
    inviteCreateTitle0 = view.findViewById(R.id.tv_invite_create_title);
    inviteCreateTitle1 = view.findViewById(R.id.et_invitation_create_title);
   // inviteCreateTitle1.addTextChangedListener(textWatcher);
    inviteCreateLocation = view.findViewById(R.id.et_invitation_create_location);
   // inviteCreateLocation.addTextChangedListener(textWatcher1);
    inviteCreateDate = view.findViewById(R.id.et_invitation_create_date);
   // inviteCreateDate.addTextChangedListener(textWatcher2);
    inviteCreateDescription = view.findViewById(R.id.et_invitation_create_description);
    //inviteCreateDescription.addTextChangedListener(textWatcher3);
  }

  private void initRecyclerView() {
    recyclerView = view.findViewById(R.id.rv_invitation_create);
    persons = fragInviteCreateListener.getParentActivity().getPeopleAroundMeList();
    PeopleAdapter adapter = new PeopleAdapter(getActivity(), persons, getContext());
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    recyclerView.setAdapter(adapter);
    Glide.with(this);
  }

//  private TextWatcher textWatcher = new TextWatcher() {
//    @Override
//    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//    }
//
//    @Override
//    public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//    }
//
//    @Override
//    public void afterTextChanged(Editable s) {
//      String result = inviteCreateTitle1.getText().toString();
//      if (result.equals("")) {
//        Toast.makeText(context, "Create a title.", Toast.LENGTH_LONG).show();
//      }
//    }
//  };
//
//  private TextWatcher textWatcher1 = new TextWatcher() {
//    @Override
//    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//    }
//
//    @Override
//    public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//    }
//
//    @Override
//    public void afterTextChanged(Editable s) {
//      String result1 = inviteCreateLocation.getText().toString();
//      if (result1.equals("")) {
//        Toast.makeText(context, "Set a location", Toast.LENGTH_LONG).show();
//      }
//    }
//  };
//
//  private TextWatcher textWatcher2 = new TextWatcher() {
//    @Override
//    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//    }
//
//    @Override
//    public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//    }
//
//    @Override
//    public void afterTextChanged(Editable s) {
//      String result2 = inviteCreateDate.getText().toString();
//      if (result2.equals("")) {
//        Toast.makeText(context, "Define a date.", Toast.LENGTH_SHORT).show();
//      }
//    }
//  };
//
//  private TextWatcher textWatcher3 = new TextWatcher() {
//    @Override
//    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//    }
//
//    @Override
//    public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//    }
//
//    @Override
//    public void afterTextChanged(Editable s) {
//      String result3 = inviteCreateDescription.getText().toString();
//      if (result3.equals("")) {
//        Toast.makeText(context, "Define a description.", Toast.LENGTH_SHORT).show();
//      }
//    }
//  };

  private void initButton() {
    doneButton = view.findViewById(R.id.bt_invitation_done);
    doneButton.setOnClickListener(v -> {
      String result = inviteCreateTitle1.getText().toString();
      String result1 = inviteCreateLocation.getText().toString();
      String result2 = inviteCreateDate.getText().toString();
      String result3 = inviteCreateDescription.getText().toString();
      new QueryTask().execute(result, result1, result2, result3);
      FragmentManager fragmentManager = getFragmentManager();
      FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction()
          .addToBackStack(null);
      fragmentTransaction.replace(R.id.fl_main_frag_container, new FragMainMenu()).commit();
      //todo:  send the invitation to the server via: fragInviteCreateListener.sendAnInvitation(inviteToSend, recipientId);
    });
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

    /**
     * Gets an instance of the Client database to create a new Invitation.
     * @param strings
     * @return
     */
    @Override
    protected Long doInBackground(String... strings) {
      Invitation invitation = ClientDB.getInstance(getContext()).getInvitationDao()
          .selectAllDate(strings[0]);
      if (invitation == null) {
        invitation = new Invitation();
        invitation.setUserSenderId(((MainActivity) getActivity()).getPersonId());
        invitation.setTitle(strings[0]);
        invitation.setLocation(strings[1]);
        invitation.setDate(strings[2]);
        invitation.setDescription(strings[3]);
        return ClientDB.getInstance(getContext()).getInvitationDao().insert(invitation);
      }
      return invitation.getInvitationId();
    }

    /**
     * Sets the new Invitation with an invitation id.
     * @param aLong
     */
    @Override
    protected void onPostExecute(Long aLong) {
      ((MainActivity) getActivity()).setInvitationId(aLong);
    }
  }
}
